package me.elhakimi.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import me.elhakimi.hunters_league.domain.Participation;
import me.elhakimi.hunters_league.web.vm.request.RequestForGetUserCompetitionResultVm;
import me.elhakimi.hunters_league.web.vm.response.ParticipationResponseVm;
import me.elhakimi.hunters_league.web.vm.response.UserResultsResponseVm;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationResponseVm toParticipationResponseVm(Participation participation);

    Participation toCompetitionFromUserResultsVm(RequestForGetUserCompetitionResultVm requestForGetUserCompetitionResultVm);
    UserResultsResponseVm toUserResultsResponseVm(Participation participation);
}
