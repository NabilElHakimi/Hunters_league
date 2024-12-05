package org.rabie.hunters_league.web.vm.response;

import lombok.Getter;
import lombok.Setter;
import org.rabie.hunters_league.domain.Species;

import java.util.UUID;

@Getter
@Setter
public class HuntResponseWithoutParticipationVm {
    private UUID id;
    private Species species;
    private Double weight;
}
