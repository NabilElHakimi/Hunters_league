package org.rabie.hunters_league.web.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionResults {
    private UUID id;
    private String code;
    private String location;
    private LocalDateTime date;
    private Double score;
    private List<HuntResponseWithoutParticipationVm> listHunt;

}
