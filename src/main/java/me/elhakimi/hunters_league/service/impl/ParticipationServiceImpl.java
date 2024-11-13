package me.elhakimi.hunters_league.service.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domain.Participation;
import me.elhakimi.hunters_league.dto.CompetitionDTO;
import me.elhakimi.hunters_league.repository.ParticipationRepository;
import me.elhakimi.hunters_league.service.CompetitionService;
import me.elhakimi.hunters_league.service.ParticipationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParticipationServiceImpl {

    private final ParticipationRepository participationRepository;
    private final CompetitionService competitionService;

    public Participation save(Participation participation) {
        return participationRepository.save(participation);
    }

    public Page<CompetitionDTO> getCompetitionsDetails(Pageable pageable) {
        return competitionService.getCompetitionsDetails(pageable);
    }

    @Transactional
    public void updateParticipationScores() {
        participationRepository.updateParticipationScores();
    }

}
