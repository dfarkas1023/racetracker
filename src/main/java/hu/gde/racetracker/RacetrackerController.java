package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/getRaces")
    public List<RaceEntity> getRaces(){return raceRepository.findAll();}
    @PostMapping("/addRunner")
    public RunnerEntity addRunner(@RequestBody RunnerEntity runner) {return runnerRepository.save(runner);}


    @GetMapping("/getRaceRunners/{ID}")
    public List<RunnerEntity> getRaceRunners(@PathVariable Long id){
        RaceEntity runner = raceRepository.findById(id).orElseThrow(() -> new RuntimeException("Runner not found!"));
        return new ArrayList<>(runner.getRaceRunners());
    }
}
