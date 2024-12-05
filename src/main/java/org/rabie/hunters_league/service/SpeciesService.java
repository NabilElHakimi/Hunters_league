package org.rabie.hunters_league.service;

import org.rabie.hunters_league.domain.Species;
import org.rabie.hunters_league.exceptions.SpeciesException;
import org.rabie.hunters_league.repository.SpeciesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Page<Species> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return speciesRepository.findAll(pageRequest);
    }

    public Species save(Species species) {
        if(species == null)
            throw new SpeciesException("Species is null");
        Species searchSpecies = speciesRepository.findByName(species.getName());
        if(searchSpecies != null)
            throw new SpeciesException("Species with name " + species.getName() + " already exists");
        return speciesRepository.save(species);
    }

    public void delete(Species species) {
        if (species == null)
            throw new SpeciesException("Species is null");
        speciesRepository.deleteById(species.getId());
    }
    public Species getById(UUID id) {
        return speciesRepository.findById(id).orElse
                (new Species());
    }



}
