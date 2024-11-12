package me.elhakimi.hunters_league.repository;

import me.elhakimi.hunters_league.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}
