package me.elhakimi.hunters_league.service.impl;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domain.Specie;
import me.elhakimi.hunters_league.repository.SpecieRepository;
import me.elhakimi.hunters_league.service.SpecieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SpecieServiceImpl implements SpecieService {
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

    public Specie getSpecieById(UUID id) {

        return specieRepository.findById(id).orElse(null);
    }

}
