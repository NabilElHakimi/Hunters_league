package me.elhakimi.hunters_league.web.member;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domain.Competition;
import me.elhakimi.hunters_league.dto.CompetitionDTO;
import me.elhakimi.hunters_league.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/participation")
@AllArgsConstructor
public class ParticipationController {

    private final CompetitionService competition;

    @GetMapping
    public ResponseEntity<Page<CompetitionDTO>> getAllCompetitionsDetails(Pageable pageable) {
        return ResponseEntity.ok(competition.getCompetitionsDetails(pageable));
    }

}
