package me.elhakimi.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import me.elhakimi.hunters_league.domain.Competition;
import me.elhakimi.hunters_league.web.vm.request.CreateCompetitionVm;
import me.elhakimi.hunters_league.web.vm.response.CompetitionResponseVm;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    Competition toCompetition(CreateCompetitionVm createCompetitionVm);
    CompetitionResponseVm toCompetitionResponseVm(Competition competition);


}
