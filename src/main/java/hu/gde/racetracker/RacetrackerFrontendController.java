package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
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
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @GetMapping("/runners")
    public String getRunners(Model model) {
        List<RunnerEntity> runners = runnerRepository.findAll();
        runners.sort(Comparator.comparing(RunnerEntity::getFinishTime));
        model.addAttribute("runners", runners);
        return "runners";
    }

    @PostMapping("/addRunner")
    public String addRunner(@RequestParam("name") String name,
                            @RequestParam("age") Long age,
                            @RequestParam("gender") Gender gender
    ) {
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
    public String newRace(@RequestParam("name") String name, @RequestParam("length") Long length, @RequestParam(value = "runners[]", required = false) List<Long> selectedRunnerIds) {
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
        Collections.sort(allRunners, Comparator.comparing(RunnerEntity::getFinishTime));

        List<RunnerEntity> availableRunners = new ArrayList<>();
        model.addAttribute("availableRunners", availableRunners);

        if (race.getRaceRunners().isEmpty()) {
            model.addAttribute("averageTimeMessage", "Average Time: Not Available (No Runners Added Yet)");
        }


        List<RunnerEntity> raceRunners = new ArrayList<>(race.getRaceRunners());
        model.addAttribute("runners", raceRunners);
        List<RunnerEntity> sortedRaceRunners = new ArrayList<>(raceRunners);
        sortedRaceRunners.sort(Comparator.comparing(RunnerEntity::getFinishTime));
        model.addAttribute("sortedRunners", sortedRaceRunners);

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


    @PostMapping("/races/{raceId}/addRunnerAndCreate")
    public String addRunnerAndCreateToRace(@PathVariable Long raceId,
                                           @RequestParam("name") String name,
                                           @RequestParam("age") Long age,
                                           @RequestParam("gender") Gender gender) {

        RaceEntity race = raceRepository.findById(raceId).orElseThrow(() -> new RuntimeException("Race not found!"));

        RunnerEntity runner = new RunnerEntity();
        runner.setRunnerName(name);
        runner.setRunnerAge(age);
        runner.setRunnerGender(gender);

        long randomMinutes = (long) (Math.random() * (200 - 50)) + 40;
        runner.setFinishTime(randomMinutes);

        ResultEntity result = new ResultEntity(runner, randomMinutes);
        runnerRepository.save(runner);
        resultRepository.save(result);

        race.addRunner(runner);
        raceRepository.save(race);

        return "redirect:/raceDetails/" + raceId;
    }

    @GetMapping("/races")
    public String getRaces(Model model) {
        System.out.println("Fetching races...");
        List<RaceEntity> races = raceRepository.findAll();
        for (RaceEntity race : races) {
            Long raceId = race.getRaceId();
            long averageTimeForRace = getAverageTimeFromApi(raceId);
            race.setAvrgTime(LocalTime.ofSecondOfDay(averageTimeForRace * 60));
        }
        model.addAttribute("races", races);
        return "races";
    }



    private long getAverageTimeFromApi(Long raceId) {
        String url = String.format("http://localhost:8082/api/getAverageTime/%d", raceId);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Long> responseEntity = restTemplate.getForEntity(url, Long.class);
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        } else {
            System.err.println("Error fetching average time from API!");
            return 0;
        }


    }
}