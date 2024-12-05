package org.rabie.hunters_league.service;

import lombok.NonNull;
import org.rabie.hunters_league.domain.Hunt;
import org.rabie.hunters_league.domain.Participation;
import org.rabie.hunters_league.domain.Species;
import org.rabie.hunters_league.exceptions.CompetitionException;
import org.rabie.hunters_league.exceptions.LicenceUserExpiredException;
import org.rabie.hunters_league.exceptions.UserNotExistException;
import org.rabie.hunters_league.repository.ParticipationRepository;
import org.rabie.hunters_league.repository.dto.mapper.ParticipationRepositoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final HuntService huntService;
    private final ParticipationRepositoryMapper participationRepositoryMapper;

    public ParticipationService(ParticipationRepository participationRepository, HuntService huntService,ParticipationRepositoryMapper participationRepositoryMapper) {
        this.participationRepository = participationRepository;
        this.huntService = huntService;
        this.participationRepositoryMapper = participationRepositoryMapper;
    }

    public Page<Participation> getAll(int page, int size) {
        return participationRepository.findAll(PageRequest.of(page, size));
    }
    public Participation getById(UUID id) {
        return participationRepository.findById(id).orElse
                (new Participation());
    }
    public Participation save(@NonNull Participation participation) {
        Participation searchParticipation = participationRepository.findByAppUserAndCompetition(participation.getAppUser(), participation.getCompetition());
        if(searchParticipation != null)
            throw new CompetitionException("User already registered in this competition");
        if(!participation.getAppUser().getLicenseExpirationDate().isAfter(LocalDateTime.now()))
            throw new LicenceUserExpiredException("User license has expired");
        if (participation.getAppUser() == null)
            throw new UserNotExistException("User does not exist");
        if(!participation.getCompetition().getOpenRegistration())
            throw new CompetitionException("Competition is closed for registration");
        if(participation.getCompetition().getDate().isBefore(LocalDateTime.now()))
            throw new CompetitionException("Competition has ended");
        if(participation.getCompetition() == null)
            throw new CompetitionException("Competition does not exist");
        return participationRepository.save(participation);
    }

    public List<Participation> findByUserId(UUID userId, int page, int size) {
        return participationRepository.findByAppUserId(userId,PageRequest.of(page, size));
    }

    public List<Participation> getTop3ParticipationOrderByScoreDesc() {
        return participationRepository.getTop3ParticipationOrderByScoreDesc(PageRequest.of(0,3));
    }
    public Integer getUserRank(UUID competitionId, UUID userId) {
        return participationRepository.getAppUserRank(competitionId, userId);
    }

    public double calculateScore(Participation participation) {
        List<Hunt> hunts = participation.getHunts();
        AtomicReference<Double> score = new AtomicReference<>(0.);
        hunts.forEach(hunt -> {
            Species species = hunt.getSpecies();
            Integer pointsAssociated = species.getPoints();
            Double weight = hunt.getWeight();
            int factorOfSpecies = species.getCategory().getValue();
            int factorOfDifficulty = species.getDifficulty().getValue();
            score.updateAndGet(v -> v + pointsAssociated + (weight * factorOfSpecies) * factorOfDifficulty);
        });
        participation.setScore(score.get());
        participationRepository.save(participation);
        return score.get();
    }


    public void calculateScoresForAllParticipation() {
        int batchSize = 5000;
        long totalParticipations = participationRepository.count();

        for (long offset = 0; offset < totalParticipations; offset += batchSize) {
            List<Participation> participations = participationRepository.findAllWithLimit(offset, batchSize);

            for (Participation participation : participations) {
                Double score = calculateScore(participation);
                participation.setScore(score);
            }

            participationRepository.saveAll(participations);
        }
    }




}
