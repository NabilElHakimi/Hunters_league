package org.rabie.hunters_league.repository.dto.mapper;

import org.mapstruct.Mapper;
import org.rabie.hunters_league.domain.Participation;
import org.rabie.hunters_league.repository.dto.ParticipationDTO;

@Mapper(componentModel = "spring")
public interface ParticipationRepositoryMapper {
    Participation toParticipation(ParticipationDTO participationDTO);
}
