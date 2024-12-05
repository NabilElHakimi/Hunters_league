package org.rabie.hunters_league.web.vm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.rabie.hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateCompetitionVm {
    @NotBlank @NonNull
    @Pattern(regexp = "^[A-Za-z]+_[0-9]{4}-[0-9]{2}-[0-9]{2}$")
    private String code;
    @NonNull @NotBlank
    private String location;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private SpeciesType speciesType;
    @NotNull
    private Integer minParticipants;
    @NotNull
    private Integer maxParticipants;
    @NotNull
    private Boolean openRegistration;
}
