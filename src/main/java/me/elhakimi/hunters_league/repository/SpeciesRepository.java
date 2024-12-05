package me.elhakimi.hunters_league.repository;

import me.elhakimi.hunters_league.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {
    Species findByName(String name);
}
