package me.elhakimi.hunters_league.dto.mappers;

import me.elhakimi.hunters_league.domain.Competition;
import me.elhakimi.hunters_league.dto.CompetitionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CompetitionMapper {
    Competition fromDto(CompetitionDTO dto);

    CompetitionDTO toDto(Competition cmp);
}
