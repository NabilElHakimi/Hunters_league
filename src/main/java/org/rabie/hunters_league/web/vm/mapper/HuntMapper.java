package org.rabie.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import org.rabie.hunters_league.domain.Hunt;
import org.rabie.hunters_league.web.vm.request.CreateHuntVm;
import org.rabie.hunters_league.web.vm.response.HuntResponseVm;
import org.rabie.hunters_league.web.vm.response.HuntResponseWithoutParticipationVm;

@Mapper(componentModel = "spring")
public interface HuntMapper {
    Hunt toHunt(CreateHuntVm createHuntVm);
    HuntResponseVm toHuntResponseVm(Hunt hunt);
    HuntResponseWithoutParticipationVm toHuntResponseWithoutParticipationVm(Hunt hunt);

}
