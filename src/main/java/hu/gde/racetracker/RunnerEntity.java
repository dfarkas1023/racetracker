package hu.gde.racetracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RunnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long runnerId;

    private String runnerName;

    private Long runnerAge;

    private Long time;

    public Long getRunnerId(){return runnerId;}

    public void setRunnerId(Long runnerId) {this.runnerId = runnerId;}

    public String getRunnerName(){return runnerName;}

    public void setRunnerName(String runnerName){this.runnerName = runnerName;}

    public Long getRunnerAge(){return runnerAge;}

    public void setRunnerAge(Long runnerAge){this.runnerAge = runnerAge;}

    public Long getTime() {return time;}

    public void setTime(Long time){this.time = time;}

    private Gender runnerGender; // Change the type to Gender

    // Getters and setters for runnerId, runnerName, and runnerAge

    public Gender getRunnerGender() {
        return runnerGender;
    }

    public void setRunnerGender(Gender runnerGender) {
        this.runnerGender = runnerGender;
    }
}

enum Gender {
    MALE,
    FEMALE,
    OTHER
}
