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

        long finishTimeInSeconds5 = TimeUnit.SECONDS.toMinutes(8000L);
        RunnerEntity runner5 = new RunnerEntity();
        runner5.setRunnerName("Larry");
        runner5.setRunnerAge(28L);
        runner5.setFinishTime(finishTimeInSeconds5);
        runner5.setRunnerGender(Gender.MALE);
        runnerRepository.save(runner5);

        long finishTimeInSeconds6 = TimeUnit.SECONDS.toMinutes(9000L);
        RunnerEntity runner6 = new RunnerEntity();
        runner6.setRunnerName("Erin");
        runner6.setRunnerAge(24L);
        runner6.setFinishTime(finishTimeInSeconds6);
        runner6.setRunnerGender(Gender.FEMALE);
        runnerRepository.save(runner6);

        long finishTimeInSeconds7 = TimeUnit.SECONDS.toMinutes(8600L);
        RunnerEntity runner7 = new RunnerEntity();
        runner7.setRunnerName("Dwight");
        runner7.setRunnerAge(36L);
        runner7.setFinishTime(finishTimeInSeconds7);
        runner7.setRunnerGender(Gender.MALE);
        runnerRepository.save(runner7);

        long finishTimeInSeconds8 = TimeUnit.SECONDS.toMinutes(9600L);
        RunnerEntity runner8 = new RunnerEntity();
        runner8.setRunnerName("Michael");
        runner8.setRunnerAge(51L);
        runner8.setFinishTime(finishTimeInSeconds8);
        runner8.setRunnerGender(Gender.MALE);
        runnerRepository.save(runner8);

        long finishTimeInSeconds9 = TimeUnit.SECONDS.toMinutes(5400L);
        RunnerEntity runner9 = new RunnerEntity();
        runner9.setRunnerName("Kelly");
        runner9.setRunnerAge(25L);
        runner9.setFinishTime(finishTimeInSeconds9);
        runner9.setRunnerGender(Gender.FEMALE);
        runnerRepository.save(runner9);

        long finishTimeInSeconds10 = TimeUnit.SECONDS.toMinutes(4700L);
        RunnerEntity runner10 = new RunnerEntity();
        runner10.setRunnerName("Lee");
        runner10.setRunnerAge(18L);
        runner10.setFinishTime(finishTimeInSeconds10);
        runner10.setRunnerGender(Gender.OTHER);
        runnerRepository.save(runner10);



        RaceEntity race1 = new RaceEntity();
        race1.setRaceName("Charity Run - Save The Trees");
        race1.setRaceId(2344556L);
        race1.setLength(5L);


        RaceEntity race2 = new RaceEntity();
        race2.setRaceName("Oxford Half");
        race2.setRaceId(234445L);
        race2.setLength(1L);



        List<RunnerEntity> runners = runnerRepository.findAll();

        for (RunnerEntity runner : runners) {
            race1.addRunner(runner10);
            race1.addRunner(runner2);
            race1.addRunner(runner5);
            race2.addRunner(runner1);
            race2.addRunner(runner8);
            race2.addRunner(runner4);
        }

        raceRepository.save(race1);
        raceRepository.save(race2);

    }
}
