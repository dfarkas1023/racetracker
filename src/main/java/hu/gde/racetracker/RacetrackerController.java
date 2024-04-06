package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/getRunners")
    public List<RunnerEntity> getRunners(){return runnerRepository.findAll();}

    @GetMapping("/getRaces")
    public List<RaceEntity> getRaces(){return raceRepository.findAll();}
    @PostMapping("/addRunner")
    public RunnerEntity addRunner(@RequestBody RunnerEntity runner) {return runnerRepository.save(runner);}

    @PostMapping("/addResult")
    public ResultEntity addResult(@RequestBody ResultEntity result) {return  resultRepository.save(result);}

    @PostMapping("/newRace")
    public RaceEntity newRace(@RequestBody RaceEntity race) {return raceRepository.save(race);}

    @GetMapping("/raceDetails/{id}")
    public ResponseEntity<RaceEntity> getRaceDetails(@PathVariable Long id){
        RaceEntity race = raceRepository.findById(id).orElseThrow(() -> new RuntimeException("Race not found!"));
        return ResponseEntity.ok(race);
    }

    @GetMapping("/getRaceRunners/{id}")
    public List<RunnerEntity> getRaceRunners(@PathVariable Long id){
        RaceEntity runner = raceRepository.findById(id).orElseThrow(() -> new RuntimeException("Runner not found!"));
        return new ArrayList<>(runner.getRaceRunners());
    }
}
