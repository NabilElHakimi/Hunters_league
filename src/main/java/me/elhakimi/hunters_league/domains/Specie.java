package me.elhakimi.hunters_league.domains;

import jakarta.persistence.*;
import lombok.*;
import me.elhakimi.hunters_league.domains.enums.Category;
import me.elhakimi.hunters_league.domains.enums.Difficulty;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "\"species\"")
public class Specie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private double minimumWeight;

    private String name;

    private Integer points;

}
