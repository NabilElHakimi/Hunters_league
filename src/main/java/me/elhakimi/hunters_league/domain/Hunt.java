package me.elhakimi.hunters_league.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hunt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Specie species;

    private Double weight;

    @ManyToOne
    private Participation participation;

}
