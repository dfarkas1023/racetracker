package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ResultRepository resultRepository;


    @Override
    public void run(String... args) throws Exception {
        RunnerEntity runner1 = new RunnerEntity();
        runner1.setRunnerName("Jim");
        runner1.setRunnerAge(33L);
        runner1.setFinishTime(98L);
        runner1.setRunnerGender(Gender.MALE);
        runnerRepository.save(runner1);

        RunnerEntity runner2 = new RunnerEntity();
        runner2.setRunnerName("Alma");
        runner2.setRunnerAge(19L);
        runner2.setFinishTime(110L);
        runner2.setRunnerGender(Gender.FEMALE);
        runnerRepository.save(runner2);

        RunnerEntity runner3 = new RunnerEntity();
        runner3.setRunnerName("Riley");
        runner3.setRunnerAge(20L);
        runner3.setFinishTime(132L);
        runner3.setRunnerGender(Gender.OTHER);
        runnerRepository.save(runner3);

        RaceEntity race1 = new RaceEntity();
        race1.setRaceName("Charity Run - Save The Trees");
        race1.setRaceId(23445455L);
        race1.setLength(5L);
        raceRepository.save(race1);

        RaceEntity race2 = new RaceEntity();
        race2.setRaceName("Windmill Academy Junior Competition");
        race2.setRaceId(23445455L);
        race2.setLength(1L);
        raceRepository.save(race2);

    }
}
