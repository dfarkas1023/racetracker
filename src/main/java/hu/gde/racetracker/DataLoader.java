package hu.gde.racetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        long finishTimeInSeconds1 = TimeUnit.SECONDS.toMinutes(5880L);
        RunnerEntity runner1 = new RunnerEntity();
        runner1.setRunnerName("Jim");
        runner1.setRunnerAge(33L);
        runner1.setFinishTime(finishTimeInSeconds1);
        runner1.setRunnerGender(Gender.MALE);
        runnerRepository.save(runner1);

        long finishTimeInSeconds2 = TimeUnit.SECONDS.toMinutes(6600L);
        RunnerEntity runner2 = new RunnerEntity();
        runner2.setRunnerName("Alma");
        runner2.setRunnerAge(19L);
        runner2.setFinishTime(finishTimeInSeconds2);
        runner2.setRunnerGender(Gender.FEMALE);
        runnerRepository.save(runner2);

        long finishTimeInSeconds3 = TimeUnit.SECONDS.toMinutes(7920L);
        RunnerEntity runner3 = new RunnerEntity();
        runner3.setRunnerName("Riley");
        runner3.setRunnerAge(20L);
        runner3.setFinishTime(finishTimeInSeconds3);
        runner3.setRunnerGender(Gender.OTHER);
        runnerRepository.save(runner3);

        long finishTimeInSeconds4 = TimeUnit.SECONDS.toMinutes(6060L);
        RunnerEntity runner4 = new RunnerEntity();
        runner4.setRunnerName("Pam");
        runner4.setRunnerAge(13L);
        runner4.setFinishTime(finishTimeInSeconds4);
        runner4.setRunnerGender(Gender.FEMALE);
        runnerRepository.save(runner4);

        RaceEntity race1 = new RaceEntity();
        race1.setRaceName("Charity Run - Save The Trees");
        race1.setRaceId(2344556L);
        race1.setLength(5L);
        race1.calculateAvgFinishTime();

        RaceEntity race2 = new RaceEntity();
        race2.setRaceName("Oxford Half");
        race2.setRaceId(234445L);
        race2.setLength(1L);
        race2.calculateAvgFinishTime();


        List<RunnerEntity> runners = runnerRepository.findAll();

        for (RunnerEntity runner : runners) {
            race1.addRunner(runner1);
            race1.addRunner(runner2);
            race1.addRunner(runner3);
            race2.addRunner(runner1);
            race2.addRunner(runner2);
            race2.addRunner(runner3);
        }

        raceRepository.save(race1);
        raceRepository.save(race2);

    }
}
