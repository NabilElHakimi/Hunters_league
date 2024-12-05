package org.rabie.hunters_league.web.api;

import jakarta.validation.Valid;
import org.rabie.hunters_league.domain.AppUser;
import org.rabie.hunters_league.domain.Competition;
import org.rabie.hunters_league.domain.Participation;
import org.rabie.hunters_league.exceptions.HuntException;
import org.rabie.hunters_league.service.CompetitionService;
import org.rabie.hunters_league.service.HuntService;
import org.rabie.hunters_league.service.ParticipationService;
import org.rabie.hunters_league.service.UserService;
import org.rabie.hunters_league.web.vm.mapper.HuntMapper;
import org.rabie.hunters_league.web.vm.mapper.ParticipationMapper;
import org.rabie.hunters_league.web.vm.mapper.UserMapper;
import org.rabie.hunters_league.web.vm.request.CreateParticipationVm;
import org.rabie.hunters_league.web.vm.response.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/participation")
public class ParticipationController {
    private final ParticipationService participationService;
    private final ParticipationMapper participationMapper;
    private final CompetitionService competitionService;
    private final HuntService huntService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final HuntMapper huntMapper;

    public ParticipationController(ParticipationService participationService, ParticipationMapper participationMapper, CompetitionService competitionService,
                                    UserService userService, HuntService huntService, UserMapper userMapper,HuntMapper huntMapper)
    {
        this.participationService = participationService;
        this.participationMapper = participationMapper;
        this.competitionService = competitionService;
        this.userService = userService;
        this.huntService = huntService;
        this.userMapper = userMapper;
        this.huntMapper = huntMapper;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('MEMBER')")
    public Page<ParticipationResponseVm> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        return participationService.getAll(page, size).map(participationMapper::toParticipationResponseVm);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<ParticipationResponseVm> createParticipation(@Valid @RequestBody CreateParticipationVm createParticipationVm) {
        AppUser appUser = userService.getById(createParticipationVm.getUserId());
        Competition competition = competitionService.getById(createParticipationVm.getCompetitionId());
        Participation participation = new Participation();
        participation.setAppUser(appUser);
        participation.setCompetition(competition);
        participation.setScore(createParticipationVm.getScore());
        Participation savedParticipation = participationService.save(participation);
        return ResponseEntity.ok(participationMapper.toParticipationResponseVm(savedParticipation));
    }

    @GetMapping("/calculateScore/{id}")
    @PreAuthorize("hasRole('JURY')")
    public ResponseEntity<ParticipationResponseVm> calculateScore(@PathVariable UUID id) {
        Participation participation = participationService.getById(id);
        participationService.calculateScore(participation);
        //participationService.calculateScoresForAllParticipation();
        return ResponseEntity.ok(participationMapper.toParticipationResponseVm(participation));
    }


    @GetMapping("/getMyResult/{userId}")
    @PreAuthorize("hasRole('MEMBER')")
    public UserResultsResponseVm deleteCompetition(@PathVariable UUID userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Participation> participation = participationService.findByUserId(userId, page, size);
        if(!participation.isEmpty()) {
            UserResultsResponseVm userResultsResponseVm = new UserResultsResponseVm();
            userResultsResponseVm.setUser(userMapper.toUserResponseVm(participation.get(0).getAppUser()));
            List<CompetitionResults> competitionResultsList = new ArrayList<>();
            participation.forEach(part -> {
                    CompetitionResults competitionResults = new CompetitionResults();
                List<HuntResponseWithoutParticipationVm> huntResponseVmList = new ArrayList<>();
                part.getHunts().forEach(hunt -> {
                    huntResponseVmList.add(huntMapper.toHuntResponseWithoutParticipationVm(hunt));
                });
                competitionResults.setId(part.getCompetition().getId());
                competitionResults.setCode(part.getCompetition().getCode());
                competitionResults.setDate(part.getCompetition().getDate());
                competitionResults.setLocation(part.getCompetition().getLocation());
                competitionResults.setScore(part.getScore());
                competitionResults.setListHunt(huntResponseVmList);
                competitionResultsList.add(competitionResults);
            });
            userResultsResponseVm.setCompetitions(competitionResultsList);
            return userResultsResponseVm;
        }else throw new HuntException("no part");

    }

    @GetMapping("/getMyHistoric/{userId}")
    @PreAuthorize("hasRole('MEMBER')")
    public UserHistoricResponseVm getMyHistoric(@PathVariable UUID userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Participation> participationList = participationService.findByUserId(userId,page,size);
        UserHistoricResponseVm userHVM = new UserHistoricResponseVm();
        if(participationList.isEmpty()) throw new HuntException("No part");
        userHVM.setUser(userMapper.toUserResponseVm(participationList.get(0).getAppUser()));
        List<PastCompetitionsResponseVm> pastCompetitionsResponseVmList = new ArrayList<>();
        participationList.forEach(part ->{
            PastCompetitionsResponseVm pastCompetitionsResponseVm = new PastCompetitionsResponseVm();
            pastCompetitionsResponseVm.setId(part.getCompetition().getId());
            pastCompetitionsResponseVm.setCode(part.getCompetition().getCode());
            pastCompetitionsResponseVm.setSpeciesType(part.getCompetition().getSpeciesType());
            pastCompetitionsResponseVm.setRank(participationService.getUserRank(part.getCompetition().getId(),part.getAppUser().getId()));
            pastCompetitionsResponseVm.setScore(part.getScore());
            pastCompetitionsResponseVmList.add(pastCompetitionsResponseVm);
        });
        userHVM.setPastCompetitions(pastCompetitionsResponseVmList);
        return userHVM;
    }
    @GetMapping("/getTop3")
    @PreAuthorize("hasRole('MEMBER')")
    public List<UserResultsResponseVm> getTop3() {
        List<Participation> participation = participationService.getTop3ParticipationOrderByScoreDesc();
        return participation.stream().map(participationMapper::toUserResultsResponseVm).toList();
    }



}
