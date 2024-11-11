package me.elhakimi.hunters_league.repositories;

import me.elhakimi.hunters_league.domains.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition , UUID> {

}
