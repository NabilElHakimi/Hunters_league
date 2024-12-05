package org.rabie.hunters_league.web.vm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.rabie.hunters_league.domain.enums.Difficulty;
import org.rabie.hunters_league.domain.enums.SpeciesType;

@Getter
@Setter
public class CreateSpeciesVm {
    @NotBlank @NonNull
    private String name;
    @NotNull
    private SpeciesType category;
    @NotNull
    private Double minimumWeight;
    @NotNull
    private Difficulty difficulty;
    @NotNull
    private Integer points;
}
