package org.rabie.hunters_league.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParticipationDTO {
    private UUID participationId;
    private Double score;

    public ParticipationDTO(UUID participationId, Double score) {
        this.participationId = participationId;
        this.score = score;
    }
}

