package me.elhakimi.hunters_league.services;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Competition;
import me.elhakimi.hunters_league.repositories.CompetitionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository ;

    public Competition save(Competition competition){

        competition.setCode(competition.getLocation()
                +"-"
                +competition.getDate().getYear()
                +"-"
                +competition.getDate().getMonth().getValue()
                +"-"
                + String.format("%02d", competition.getDate().getDayOfMonth()));

        return competitionRepository.save(competition);

    }

    public Competition update(Competition competition){
        return competitionRepository.save(competition);
    }

    public Competition delete(Competition competition){
         competitionRepository.delete(competition);
            return competition;
    }

    public Competition getCompetitionById(String id){
        return competitionRepository.findById(UUID.fromString(id)).orElse(null);

    }

    public Page<Competition> getAllCompetitions(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

}
