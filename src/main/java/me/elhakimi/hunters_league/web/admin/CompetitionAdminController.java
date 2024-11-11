package me.elhakimi.hunters_league.web.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Competition;
import me.elhakimi.hunters_league.services.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PutMapping
    public ResponseEntity<Object> updateCompetition(@RequestBody @Valid Competition competition) {
        return ResponseEntity.ok(competitionService.update(competition));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCompetition(@RequestBody @Valid Competition competition) {
        return ResponseEntity.ok(competitionService.delete(competition));
    }


    @GetMapping("{id}")
    public ResponseEntity<Object> getCompetitionById(@PathVariable String id) {
        return ResponseEntity.ok(competitionService.getCompetitionById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Competition>> getAllCompetitions(Pageable pageable) {
        return ResponseEntity.ok(competitionService.getAllCompetitions(pageable));
    }


}
