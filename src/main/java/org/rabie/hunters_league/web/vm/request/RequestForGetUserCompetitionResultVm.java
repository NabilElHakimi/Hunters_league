package org.rabie.hunters_league.web.vm.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RequestForGetUserCompetitionResultVm {
    private UUID userId;
    private UUID competitionId;
}
