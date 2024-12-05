package org.rabie.hunters_league.web.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PastCompetitionsResponseVm {
    private UUID id;
    private String code;
    private SpeciesType speciesType;
    private Integer rank;
    private Double score;
}
