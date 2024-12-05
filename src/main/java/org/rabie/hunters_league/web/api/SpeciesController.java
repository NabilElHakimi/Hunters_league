package org.rabie.hunters_league.web.api;

import jakarta.validation.Valid;
import org.rabie.hunters_league.domain.Species;
import org.rabie.hunters_league.service.HuntService;
import org.rabie.hunters_league.service.SpeciesService;
import org.rabie.hunters_league.web.vm.mapper.SpeciesMapper;
import org.rabie.hunters_league.web.vm.request.CreateSpeciesVm;
import org.rabie.hunters_league.web.vm.response.SpecieResponseVm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final HuntService huntService;
    private final SpeciesMapper speciesMapper;
    public SpeciesController(SpeciesService speciesService, SpeciesMapper speciesMapper, HuntService huntService) {
        this.speciesService = speciesService;
        this.speciesMapper = speciesMapper;
        this.huntService = huntService;
    }

    @GetMapping("/list")
    public Page<SpecieResponseVm> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        return speciesService.getAll(page, size).map(speciesMapper::toListSpeciesVm);
    }

    @PostMapping("/create")
    public ResponseEntity<SpecieResponseVm> create(@Valid @RequestBody CreateSpeciesVm createSpeciesVm) {
        Species species = speciesService.save(speciesMapper.toSpeciesFromCreate(createSpeciesVm));
        return ResponseEntity.ok(speciesMapper.toListSpeciesVm(species));
    }

    @PutMapping("/update")
    public ResponseEntity<SpecieResponseVm> update(@Valid @RequestBody SpecieResponseVm specieUpdateVm) {
        Species species = speciesService.save(speciesMapper.toSpecies(specieUpdateVm));
        return ResponseEntity.ok(speciesMapper.toListSpeciesVm(species));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SpecieResponseVm> delete(@PathVariable UUID id) {
        Species species = speciesService.getById(id);
        huntService.deleteBySpeciesId(species);
        speciesService.delete(species);
        return ResponseEntity.ok(speciesMapper.toListSpeciesVm(species));
    }

}
