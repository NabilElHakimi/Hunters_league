package me.elhakimi.hunters_league.web.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.elhakimi.hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CompetitionResponseVm {
    private UUID id;
    private String code;
    private String location;
    private LocalDateTime date;
    private SpeciesType speciesType;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Boolean openRegistration;
}
