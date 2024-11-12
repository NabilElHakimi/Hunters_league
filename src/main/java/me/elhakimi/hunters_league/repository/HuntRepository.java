package me.elhakimi.hunters_league.repository;

import me.elhakimi.hunters_league.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {

    List<Hunt> findBySpeciesId(UUID speciesId);

}
