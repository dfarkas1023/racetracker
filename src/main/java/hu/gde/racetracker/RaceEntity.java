package hu.gde.racetracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long raceId;

    private String raceName;

    private Long length;

    public Long getRaceId(){return raceId;}

    public void setRaceId(Long raceId){this.raceId = raceId;}

    public Long getLength(){return length;}

    public void setLength(Long length){this.length = length;}

    public String getRaceName(){return raceName;}

    public void setRaceName(String raceName){this.raceName = raceName;}


}
