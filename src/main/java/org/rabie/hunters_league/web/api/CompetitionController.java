package org.rabie.hunters_league.web.api;

import jakarta.validation.Valid;
import org.rabie.hunters_league.domain.Competition;
import org.rabie.hunters_league.service.CompetitionService;
import org.rabie.hunters_league.service.ParticipationService;
import org.rabie.hunters_league.web.vm.mapper.CompetitionMapper;
import org.rabie.hunters_league.web.vm.request.CreateCompetitionVm;
import org.rabie.hunters_league.web.vm.response.CompetitionResponseVm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;
    private final ParticipationService participationService;

    public CompetitionController(CompetitionService competitionService, CompetitionMapper competitionMapper, ParticipationService participationService) {
        this.competitionService = competitionService;
        this.competitionMapper = competitionMapper;
        this.participationService = participationService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompetitionResponseVm> createCompetition(@Valid @RequestBody CreateCompetitionVm createCompetitionVm) {
        Competition competition = competitionMapper.toCompetition(createCompetitionVm);
        Competition savedCompetition = competitionService.save(competition);
        return ResponseEntity.ok(competitionMapper.toCompetitionResponseVm(savedCompetition));
    }

    @GetMapping("/details")
    @PreAuthorize("hasRole('MEMBER')")
    public Page<CompetitionResponseVm> getCompetition(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size ){
        Page<Competition> competitionList = competitionService.findAll(page, size);
        return competitionList.map(competitionMapper::toCompetitionResponseVm);
    }
    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('MEMBER')")
    public List<CompetitionResponseVm> getCompetitionById(@PathVariable UUID id){
        Competition competition = competitionService.getById(id);
        CompetitionResponseVm competitionResponseVm = competitionMapper.toCompetitionResponseVm(competition);
        return List.of(competitionResponseVm);
    }

}
