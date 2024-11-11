package me.elhakimi.hunters_league.service;

import me.elhakimi.hunters_league.domain.Specie;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SpecieService {
    Page<Specie> getAllSpecies(int page, int size);
    Specie save(Specie specie);
    Specie update(Specie specie);
    void delete(Specie specie);
    Specie getSpecieById(UUID id);
}