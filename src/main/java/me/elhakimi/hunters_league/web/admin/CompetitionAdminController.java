package me.elhakimi.hunters_league.web.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Competition;
import me.elhakimi.hunters_league.services.CompetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/competition/")
@AllArgsConstructor
public class CompetitionAdminController {

    private  final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<Object> getCompetition(@RequestBody @Valid Competition competition) {
        return ResponseEntity.ok(competitionService.save(competition));
    }

}
