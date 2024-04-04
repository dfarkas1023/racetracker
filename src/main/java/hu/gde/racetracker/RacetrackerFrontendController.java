package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RacetrackerFrontendController {

    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private RunnerRepository runnerRepository;

    @GetMapping("/runners")
    public String getRunners(Model model){
        List<RunnerEntity> runners = runnerRepository.findAll();
        model.addAttribute("runners", runners);
        return "runners";
    }

    @PostMapping("/addRunner")
    public String addRunner(@RequestParam("name") String name,@RequestParam("age") Long age,@RequestParam("gender") Gender gender){
        RunnerEntity runner = new RunnerEntity();
        runner.setRunnerName(name);
        runner.setRunnerAge(age);
        runner.setRunnerGender(gender);
        runnerRepository.save(runner);
        return "redirect:/runners";
    }

    @PostMapping("/newRace")
    public String newRace(@RequestParam("name") String name,@RequestParam("length") Long length){
        RaceEntity race = new RaceEntity();
        race.setRaceName(name);
        race.setLength(length);
        raceRepository.save(race);
        return "redirect:/races";
    }

    @GetMapping("/races")
    public String getRaces(Model model){
        List<RaceEntity> races = raceRepository.findAll();
        model.addAttribute("races",races);
        return "races";
    }

}
