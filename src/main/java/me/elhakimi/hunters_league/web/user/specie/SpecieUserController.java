package me.elhakimi.hunters_league.web.user.specie;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.Specie;
import me.elhakimi.hunters_league.services.SpecieService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/species/")
@AllArgsConstructor
public class SpecieUserController {

    private  final SpecieService specieService;

    @GetMapping("/{page}")
    public ResponseEntity<Page<Specie>> getAllSpecies(@PathVariable int page ,
                                                      @RequestParam(defaultValue = "20") int size) {
        Page<Specie> species = specieService.getAllSpecies(page, size);
        return new ResponseEntity<>(species, HttpStatus.OK);
    }

}

