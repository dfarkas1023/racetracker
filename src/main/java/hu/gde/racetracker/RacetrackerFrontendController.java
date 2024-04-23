package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class RacetrackerFrontendController {

    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private RunnerRepository runnerRepository;
    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/runners")
    public String getRunners(Model model){
        List<RunnerEntity> runners = runnerRepository.findAll();
        runners.sort(Comparator.comparing(RunnerEntity::getRunnerId));
        model.addAttribute("runners", runners);
        return "runners";
    }

    @PostMapping("/addRunner")
    public String addRunner(@RequestParam("name") String name,
                            @RequestParam("age") Long age,
                            @RequestParam("gender") Gender gender
                            ){
        RunnerEntity runner = new RunnerEntity();
        runner.setRunnerName(name);
        runner.setRunnerAge(age);
        runner.setRunnerGender(gender);

        long randomMinutes = (long) (Math.random() * (200 - 50)) + 40;
        runner.setFinishTime(randomMinutes);

        ResultEntity result = new ResultEntity(runner, randomMinutes);
        runnerRepository.save(runner);
        resultRepository.save(result);
        return "redirect:/runners";
    }

    @PostMapping("/newRace")
    public String newRace(@RequestParam("name") String name,@RequestParam("length") Long length,@RequestParam(value = "runners[]", required = false) List<Long> selectedRunnerIds){
        RaceEntity race = new RaceEntity();
        race.setRaceName(name);
        race.setLength(length);

        if (selectedRunnerIds != null) {
            for (Long runnerId : selectedRunnerIds) {
                RunnerEntity runner = runnerRepository.findById(runnerId).orElseThrow(() -> new RuntimeException("Runner not found!"));
                race.addRunner(runner);
            }
        }

        raceRepository.save(race);
        return "redirect:/races";
    }


    @GetMapping("/raceDetails/{id}")
    public String getRaceDetails(@PathVariable Long id, Model model) {
        RaceEntity race = raceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Race not found!"));
        model.addAttribute("race", race);

        List<RunnerEntity> allRunners = runnerRepository.findAll();
        Collections.sort(allRunners, Comparator.comparing(RunnerEntity::getRunnerId));



        List<RunnerEntity> availableRunners = new ArrayList<>();
        model.addAttribute("availableRunners", availableRunners);

        for (RunnerEntity runner : allRunners) {
            boolean alreadyInRace = false;
            for (RunnerEntity raceRunner : race.getRaceRunners()) {
                if (raceRunner.getRunnerId().equals(runner.getRunnerId())) {
                    alreadyInRace = true;
                    break;
                }
            }
            if (!alreadyInRace) {
                availableRunners.add(runner);
            }
        }
        model.addAttribute("runners", race.getRaceRunners());
        model.addAttribute("selectedRunnerId", 0L);

        race.calculateAvgFinishTime();
        return "race_details";
    }

    @PostMapping("/races/{raceId}/addRunner")
    public String addRunnerToRace(@PathVariable Long raceId, @RequestParam Long selectedRunnerId) {
        RaceEntity race = raceRepository.findById(raceId).orElseThrow(() -> new RuntimeException("Race not found!"));
        RunnerEntity runner = runnerRepository.findById(selectedRunnerId).orElseThrow(() -> new RuntimeException("Runner not found!"));

        race.addRunner(runner);
        raceRepository.save(race);

        return "redirect:/raceDetails/" + raceId;
    }

    @GetMapping("/races")
    public String getRaces(Model model){
        System.out.println("Fetching races...");
        List<RaceEntity> races = raceRepository.findAll();
        for (RaceEntity race : races) {
            race.calculateAvgFinishTime();
        }
        model.addAttribute("races",races);
        return "races";
        }


}
