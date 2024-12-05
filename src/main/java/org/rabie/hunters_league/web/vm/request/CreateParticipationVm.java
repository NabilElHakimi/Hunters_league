package org.rabie.hunters_league.web.vm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class CreateParticipationVm {
    @NonNull
    private UUID userId;
    @NonNull
    private UUID competitionId;
    private Double score;
}
