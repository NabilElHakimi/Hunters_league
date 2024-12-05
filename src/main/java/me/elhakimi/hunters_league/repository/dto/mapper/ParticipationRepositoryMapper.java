package me.elhakimi.hunters_league.repository.dto.mapper;

import org.mapstruct.Mapper;
import me.elhakimi.hunters_league.domain.Participation;
import me.elhakimi.hunters_league.repository.dto.ParticipationDTO;

@Mapper(componentModel = "spring")
public interface ParticipationRepositoryMapper {
    Participation toParticipation(ParticipationDTO participationDTO);
}
