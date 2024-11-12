package me.elhakimi.hunters_league.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.elhakimi.hunters_league.domain.enums.Category;

import java.time.LocalDateTime;

public class CompetitionDTO {
    private String code;

    @NotBlank
    @NotNull
    private String location;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category speciesType;

    @NotNull
    private Integer minParticipants;
    @NotNull
    private Integer maxParticipants;

    @NotNull
    private Boolean openRegistration;

}
