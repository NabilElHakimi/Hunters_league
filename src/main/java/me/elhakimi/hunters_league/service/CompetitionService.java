package me.elhakimi.hunters_league.service;

import me.elhakimi.hunters_league.domain.Competition;
import me.elhakimi.hunters_league.exceptions.CompetitionException;
import me.elhakimi.hunters_league.repository.CompetitionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition getById(UUID id) {
        return competitionRepository.findById(id).orElse
                (new Competition());
    }

    public Competition save(Competition competition) {
//        Competition lastCompetition = competitionRepository.findTopByOrderByDateDesc();
        competition.setCode(competition.getLocation() + "_" + competition.getDate().getYear() + "-" + competition.getDate().getMonthValue() + "-" + competition.getDate().getDayOfMonth());
        competition.setOpenRegistration(true);
//        if(lastCompetition!=null && lastCompetition.getDate().isAfter(competition.getDate().minusDays(7)))
//            throw new CompetitionException("You can just create a competition every week");
        return competitionRepository.save(competition);
    }

    public Competition update(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Page<Competition> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("date")));
        return competitionRepository.findAll(pageRequest);
    }


    @Scheduled(fixedRate = 86400000)
    public void closeRegistrationsAutomatic(){
        int batchSize = 1000;
        int total = (int) competitionRepository.count();
        for (long offset = 0; offset < total; offset += batchSize){
            List<Competition> competitionList = competitionRepository.findAllWithLimit(offset,batchSize);
            competitionList.forEach(competition -> {
                LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
                if(competition.getDate().isBefore(tomorrow)){
                    competition.setOpenRegistration(false);
                    competitionRepository.save(competition);
                }
            });
        }
    }



}
