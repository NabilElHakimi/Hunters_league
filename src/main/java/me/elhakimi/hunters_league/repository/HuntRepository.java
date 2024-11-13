package me.elhakimi.hunters_league.repository;

import jakarta.transaction.Transactional;
import me.elhakimi.hunters_league.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {

    List<Hunt> findBySpeciesId(UUID speciesId);

    @Modifying
    @Transactional
    @Query(value = "CALL delete_hunts_by_species_id(:speciesId)", nativeQuery = true)
    void deleteBySpeciesIdUsingProcedure(@Param("speciesId") UUID speciesId);

}
