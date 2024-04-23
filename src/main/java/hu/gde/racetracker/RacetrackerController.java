package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


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
        race.calculateAvgFinishTime();
        return ResponseEntity.ok(race);
    }

    @PostMapping("/races/{raceId}/addRunner")
    public ResponseEntity<RaceEntity> addRunnerToRace(@PathVariable Long raceId, @RequestParam Long selectedRunnerId) {
        RaceEntity race = raceRepository.findById(raceId).orElseThrow(() -> new RuntimeException("Race not found!"));
        RunnerEntity runner = runnerRepository.findById(selectedRunnerId).orElseThrow(() -> new RuntimeException("Runner not found!"));

        race.getRaceRunners().add(runner);
        race.calculateAvgFinishTime();
        raceRepository.save(race);

        return ResponseEntity.ok(race);
    }

    @GetMapping("/getRaceRunners/{id}")
    public List<ResultEntity> getRaceRunners(@PathVariable Long id) {
        RaceEntity race = raceRepository.findById(id).orElseThrow(() -> new RuntimeException("Race not found!"));
        Set<RunnerEntity> runners = race.getRaceRunners();

        List<ResultEntity> resultList = new ArrayList<>(runners.size());
        for (RunnerEntity runner : runners) {
            long finishTimeInMinutes = runner.getFinishTimeInMinutes();
            System.out.println("Runner " + runner.getRunnerName() + " - Finish Time in Minutes: " + finishTimeInMinutes);

            resultList.add(new ResultEntity(runner, finishTimeInMinutes));
            resultList.get(resultList.size() - 1).setRunnerName(runner.getRunnerName());
        }

        resultList.sort(Comparator.comparing(ResultEntity::getFinishTime).reversed());

        return resultList;
    }
}
