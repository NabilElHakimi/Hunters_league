package org.rabie.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import org.rabie.hunters_league.domain.Participation;
import org.rabie.hunters_league.web.vm.request.RequestForGetUserCompetitionResultVm;
import org.rabie.hunters_league.web.vm.response.ParticipationResponseVm;
import org.rabie.hunters_league.web.vm.response.UserResultsResponseVm;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationResponseVm toParticipationResponseVm(Participation participation);

    Participation toCompetitionFromUserResultsVm(RequestForGetUserCompetitionResultVm requestForGetUserCompetitionResultVm);
    UserResultsResponseVm toUserResultsResponseVm(Participation participation);
}
