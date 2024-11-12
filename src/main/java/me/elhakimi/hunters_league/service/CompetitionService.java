package me.elhakimi.hunters_league.service;

import me.elhakimi.hunters_league.domain.Competition;
import me.elhakimi.hunters_league.dto.CompetitionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionService {

    Competition save(Competition competition);
    Competition update(Competition competition);
    Competition delete(Competition competition);
    Competition getCompetitionById(String id);
    Page<Competition> getAllCompetitions(Pageable pageable);
    Page<CompetitionDTO> getCompetitionsDetails(Pageable pageable);

}