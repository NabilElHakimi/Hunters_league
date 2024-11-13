package me.elhakimi.hunters_league.repository;

import me.elhakimi.hunters_league.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

    @Modifying
    @Query(value = "SELECT update_participation_scores()", nativeQuery = true)
    void updateParticipationScores();
}
