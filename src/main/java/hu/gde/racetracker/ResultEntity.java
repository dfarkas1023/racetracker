package hu.gde.racetracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;


}
