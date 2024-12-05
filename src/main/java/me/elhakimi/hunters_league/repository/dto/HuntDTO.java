package me.elhakimi.hunters_league.repository.dto;


import lombok.Getter;
import lombok.Setter;
import me.elhakimi.hunters_league.domain.enums.Difficulty;
import me.elhakimi.hunters_league.domain.enums.SpeciesType;

@Getter
@Setter
public class HuntDTO {
    private Double weight;
    private SpeciesType speciesType;
    private Difficulty difficulty;
    private Integer points;

    public HuntDTO(Double weight, SpeciesType speciesType, Difficulty difficulty, Integer points) {
        this.weight = weight;
        this.speciesType = speciesType;
        this.difficulty = difficulty;
        this.points = points;
    }
}
