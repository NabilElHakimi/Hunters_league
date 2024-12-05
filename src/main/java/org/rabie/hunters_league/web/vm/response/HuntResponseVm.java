package org.rabie.hunters_league.web.vm.response;

import lombok.Getter;
import lombok.Setter;
import org.rabie.hunters_league.domain.Species;

import java.util.UUID;

@Getter
@Setter
public class HuntResponseVm {
    private UUID id;
    private Species species;
    private Double weight;
    private ParticipationResponseVm participation;
}
