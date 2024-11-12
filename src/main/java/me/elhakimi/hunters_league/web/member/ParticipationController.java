package me.elhakimi.hunters_league.web.member;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domain.Competition;
import me.elhakimi.hunters_league.domain.Participation;
import me.elhakimi.hunters_league.dto.CompetitionDTO;
import me.elhakimi.hunters_league.service.CompetitionService;
import me.elhakimi.hunters_league.service.ParticipationService;
import me.elhakimi.hunters_league.service.impl.ParticipationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/participation")
@AllArgsConstructor
public class ParticipationController {

   private final ParticipationServiceImpl participationService;

    @GetMapping
    public ResponseEntity<Page<CompetitionDTO>> getAllCompetitionsDetails(Pageable pageable) {
        return ResponseEntity.ok(participationService.getCompetitionsDetails(pageable));
    }

    @PostMapping
    public ResponseEntity<Participation> save(Participation participation) {
        return ResponseEntity.ok(participationService.save(participation));
    }

}

