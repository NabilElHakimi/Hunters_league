package me.elhakimi.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import me.elhakimi.hunters_league.domain.Hunt;
import me.elhakimi.hunters_league.web.vm.request.CreateHuntVm;
import me.elhakimi.hunters_league.web.vm.response.HuntResponseVm;
import me.elhakimi.hunters_league.web.vm.response.HuntResponseWithoutParticipationVm;

@Mapper(componentModel = "spring")
public interface HuntMapper {
    Hunt toHunt(CreateHuntVm createHuntVm);
    HuntResponseVm toHuntResponseVm(Hunt hunt);
    HuntResponseWithoutParticipationVm toHuntResponseWithoutParticipationVm(Hunt hunt);

}
