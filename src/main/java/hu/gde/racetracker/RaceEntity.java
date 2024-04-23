package hu.gde.racetracker;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long raceId;

    @ManyToMany
    @JoinTable(
            name = "race_runner",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "runner_id")
    )
    private Set<RunnerEntity> raceRunners;

    private String raceName;

    private Long length;

    private LocalTime avrgTime;

    public LocalTime getAvrgTime(){return avrgTime;}

    public Long getRaceId(){return raceId;}

    public void setRaceId(Long raceId){this.raceId = raceId;}

    public Long getLength(){return length;}

    public void setLength(Long length){this.length = length;}

    public String getRaceName(){return raceName;}

    public void setRaceName(String raceName){this.raceName = raceName;}

    public Set<RunnerEntity> getRaceRunners(){return raceRunners;}

    public void setRaceRunners(Set<RunnerEntity> raceRunners){this.raceRunners = raceRunners;}

    public void addRunner(RunnerEntity runner) {
        this.raceRunners.add(runner);
    }

    public RaceEntity(){
        this.raceRunners = new HashSet<>();
    }

    private LocalTime avgFinishTime;

    public LocalTime getAvgFinishTime() {
        return avgFinishTime;
    }

    public void setAvgFinishTime(LocalTime avgFinishTime) {
        this.avgFinishTime = avgFinishTime;
    }

    @PostConstruct
    public void calculateAvgFinishTime() {
        if (this.raceRunners.isEmpty()) {
            this.avgFinishTime = null;
            return;
        }

        long totalTimeInMinutes = 0;
        for (RunnerEntity runner : this.raceRunners) {
            totalTimeInMinutes += runner.getFinishTimeInMinutes();
        }

        int numRunners = this.raceRunners.size();

        long averageMinutes = totalTimeInMinutes / numRunners;

        this.avgFinishTime = LocalTime.of((int) averageMinutes, 0);
    }

}
