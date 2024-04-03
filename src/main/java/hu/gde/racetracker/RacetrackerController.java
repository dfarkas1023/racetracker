package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RacetrackerController {

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private RaceRepository raceRepository;

    @GetMapping("/getRunners")
    public List<RunnerEntity> getRunners(){return runnerRepository.findAll();}

    @PostMapping("/addRunner")
    public RunnerEntity addRunner(@RequestBody RunnerEntity runner) {return runnerRepository.save(runner);}


}
