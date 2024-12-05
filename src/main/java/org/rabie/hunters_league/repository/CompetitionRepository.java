package org.rabie.hunters_league.repository;

import org.rabie.hunters_league.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.rabie.hunters_league.domain.Competition;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    Competition findTopByOrderByDateDesc();

    @Query(value = "SELECT * FROM competition LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Competition> findAllWithLimit(@Param("offset") long offset, @Param("limit") int limit);

}
