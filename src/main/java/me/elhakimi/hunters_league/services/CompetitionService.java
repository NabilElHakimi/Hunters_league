package me.elhakimi.hunters_league.services;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Competition;
import me.elhakimi.hunters_league.repositories.CompetitionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository ;

    public Competition save(Competition competition){




        competition.setCode(competition.getLocation()+"-"
                +competition.getDate().getYear()
                +"-"
                +competition.getDate().getMonth().getValue()
                +"-"
                + String.format("%02d", competition.getDate().getDayOfMonth()));

        return competitionRepository.save(competition);

    }


}
