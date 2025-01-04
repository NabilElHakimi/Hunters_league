package me.elhakimi.hunters_league.web.vm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class CreateParticipationVm {

//    private UUID id;
//    @NonNull
//    private UUID userId;
    @NonNull
    private UUID competitionId;
    private Double score;

}
