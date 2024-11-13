package me.elhakimi.hunters_league.web.member;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domain.Participation;
import me.elhakimi.hunters_league.dto.CompetitionDTO;
import me.elhakimi.hunters_league.service.impl.ParticipationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> save(@RequestBody @Valid Participation participation) {
        participationService.save(participation);
        return ResponseEntity.ok("Save successfully");
    }

    @GetMapping("/update-scores")
    public ResponseEntity<String> updateScores() {
        participationService.updateParticipationScores();
        return ResponseEntity.ok("All scores have been updated");
    }

}

