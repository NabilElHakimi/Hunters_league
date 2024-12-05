package org.rabie.hunters_league.web.vm.response;

import lombok.Getter;
import lombok.Setter;
import org.rabie.hunters_league.domain.enums.Difficulty;
import org.rabie.hunters_league.domain.enums.SpeciesType;

import java.util.UUID;

@Getter
@Setter
public class SpecieResponseVm {
    private UUID id;
    private String name;
    private SpeciesType category;
    private Double minimumWeight;
    private Difficulty difficulty;
    private Integer points;
}
