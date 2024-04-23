package hu.gde.racetracker;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.io.Serializable;

public interface RaceRepository extends JpaRepository<RaceEntity,Long>{
}