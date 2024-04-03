package hu.gde.racetracker;

import jakarta.persistence.*;

import java.time.LocalTime;
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
}
