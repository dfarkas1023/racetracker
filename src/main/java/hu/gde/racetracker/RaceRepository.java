package hu.gde.racetracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<RaceEntity,Long>{
}