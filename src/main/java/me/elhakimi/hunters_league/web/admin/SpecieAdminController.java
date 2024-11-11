package me.elhakimi.hunters_league.web.admin;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Specie;
import me.elhakimi.hunters_league.services.SpecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/admin/species/")
@AllArgsConstructor
public class SpecieAdminController {

    private  final SpecieService specieService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody  Specie specie) {
        Specie savedSpecie = specieService.save(specie);
        if (savedSpecie != null) {
            return new ResponseEntity<>(savedSpecie, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody  Specie specie) {
        Specie savedSpecie = specieService.update(specie);
        if (savedSpecie != null) {
            return new ResponseEntity<>(savedSpecie, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Specie specie) {
        try {
            specieService.delete(specie);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Specie could not be deleted",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSpecieById(@PathVariable UUID id) {

        Specie specie = specieService.getSpecieById(id);
        if (specie != null) {
            return new ResponseEntity<>(specie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Specie not found", HttpStatus.NOT_FOUND);
        }
    }

}
