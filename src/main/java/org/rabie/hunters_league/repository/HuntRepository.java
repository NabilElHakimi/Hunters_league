package org.rabie.hunters_league.repository;

import org.rabie.hunters_league.domain.Hunt;
import org.rabie.hunters_league.domain.Species;
import org.rabie.hunters_league.repository.dto.HuntDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HuntRepository extends JpaRepository<Hunt, UUID> {
    List<Hunt> findBySpecies(Species species);

    @Modifying
    @Query(value = "DELETE FROM hunt WHERE spId IN (SELECT spId FROM hunt WHERE species_id = :speciesId LIMIT :batchSize)", nativeQuery = true)
    int deleteBySpeciesIdBatch(@Param("speciesId") UUID speciesId, @Param("batchSize") int batchSize);


    @Query(value = "SELECT * FROM hunt WHERE participation_id = :participationId LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Hunt> findByParticipationIdWithLimit(
            @Param("participationId") UUID participationId,
            @Param("offset") long offset,
            @Param("limit") int limit
    );


    @Query(value = "SELECT COUNT(*) FROM hunt WHERE participation_id = :participationId", nativeQuery = true)
    long countByParticipationId(@Param("participationId") UUID participationId);

@Query("SELECT new org.rabie.hunters_league.repository.dto.HuntDTO(h.weight, s.category,s.difficulty,s.points)" +
        " FROM Hunt h INNER JOIN Species s ON h.species.id = s.id " +
        " WHERE h.participation.id=:participationId ")
List<HuntDTO> getHuntByParticipationId(@Param("participationId") UUID participationId);
}
