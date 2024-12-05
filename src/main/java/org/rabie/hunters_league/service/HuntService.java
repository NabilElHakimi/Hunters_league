package org.rabie.hunters_league.service;

import org.rabie.hunters_league.domain.Hunt;
import org.rabie.hunters_league.domain.Participation;
import org.rabie.hunters_league.domain.Species;
import org.rabie.hunters_league.exceptions.HuntException;
import org.rabie.hunters_league.repository.HuntRepository;
import org.rabie.hunters_league.repository.dto.HuntDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HuntService {
    private final HuntRepository huntRepository;
    public HuntService(HuntRepository huntRepository) {
        this.huntRepository = huntRepository;
    }

    public List<Hunt> getByParticipation(Participation participation, int offset, int limit) {
        if (participation == null)
            throw new HuntException("Participation is null");
        return huntRepository.findByParticipationIdWithLimit(participation.getId(), offset, limit);

    }
    public long countByParticipation(Participation participation) {
        if (participation == null)
            throw new HuntException("Participation is null");
        return huntRepository.countByParticipationId(participation.getId());
    }

    @Transactional
    public void deleteBySpeciesId(Species species) {
        if (species == null)
            throw new HuntException("Hunt is null");
        int batchSize = 10000;
        int deletedCount;
        do {
            deletedCount = huntRepository.deleteBySpeciesIdBatch(species.getId(), batchSize);
        } while (deletedCount > 0);
    }

    public Hunt createHunt(Hunt hunt) {
        if (hunt == null)
            throw new HuntException("Hunt is null");
        if (hunt.getSpecies() == null)
            throw new HuntException("Species is null");
        if (hunt.getParticipation() == null)
            throw new HuntException("Participation is null");
        if(hunt.getWeight() < hunt.getSpecies().getMinimumWeight())
            throw new HuntException("Weight is less than minimum weight");
        return huntRepository.save(hunt);
    }

    public List<HuntDTO> getAllHuntDTOByParticipationId(UUID participationId){
        return huntRepository.getHuntByParticipationId(participationId);
    }

}
