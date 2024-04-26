package hu.gde.racetracker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;


@Entity
public class RunnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long runnerId;

    @OneToOne(mappedBy = "runner", cascade = CascadeType.ALL)
    private ResultEntity result;

    @ManyToMany(mappedBy = "raceRunners")
    @JsonIgnore
    private Set<RaceEntity> raceRunners;
    private String runnerName;

    private Long runnerAge;

    private Long finishTime;


    public Long getRunnerId(){return runnerId;}

    public void setRunnerId(Long runnerId) {this.runnerId = runnerId;}

    public String getRunnerName(){return runnerName;}

    public void setRunnerName(String runnerName){this.runnerName = runnerName;}

    public Long getRunnerAge(){return runnerAge;}

    public void setRunnerAge(Long runnerAge){this.runnerAge = runnerAge;}

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    private Gender runnerGender;

    public Gender getRunnerGender() {
        return runnerGender;
    }

    public void setRunnerGender(Gender runnerGender) {
        this.runnerGender = runnerGender;
    }

    public Set<RaceEntity> getRaceRunners(){return raceRunners;}

    public void setRaceRunners(Set<RaceEntity> raceRunners){this.raceRunners = raceRunners;}

    public void setRunner(RunnerEntity runner) {
    }
}

enum Gender {
    MALE,
    FEMALE,
    OTHER
}
