package hu.gde.racetracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<RunnerEntity,Long>{
}