package hu.gde.racetracker;

import jakarta.persistence.*;

@Entity
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;


    @OneToOne
    @JoinColumn(name = "runner_id")
    private RunnerEntity runner;

    private Long finishTime;

    private String runnerName;

    public ResultEntity() {

    }

    public ResultEntity(RunnerEntity runner, Long finishTime) {
        this.runner = runner;
        this.finishTime = finishTime;
    }

    // Getters and setters
    public RunnerEntity getRunner() {
        return runner;
    }

    public void setRunner(RunnerEntity runner) {
        this.runner = runner;
    }


    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

}
