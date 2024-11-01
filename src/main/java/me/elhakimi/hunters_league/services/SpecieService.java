package me.elhakimi.hunters_league.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Specie;
import me.elhakimi.hunters_league.repositories.SpecieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SpecieService {
    private final SpecieRepository specieRepository;

    public Page<Specie> getAllSpecies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specieRepository.findAll(pageable);
    }

    public Specie  save(Specie specie) {
        return specieRepository.save(specie);
    }

    public Specie  update(Specie specie) {
        return specieRepository.save(specie);
    }

    public void delete(Specie specie) {
        specieRepository.delete(specie);
    }

}
