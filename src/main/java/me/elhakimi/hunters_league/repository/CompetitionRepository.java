package me.elhakimi.hunters_league.repository;

import me.elhakimi.hunters_league.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition , UUID> {

}
