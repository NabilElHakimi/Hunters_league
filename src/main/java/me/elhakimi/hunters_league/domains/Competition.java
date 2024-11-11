package me.elhakimi.hunters_league.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.elhakimi.hunters_league.domains.enums.Category;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"competition\"")

public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code;

    @NotBlank
    @NotNull
    private String location;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category speciesType;

    @NotNull
    private Integer minParticipants;
    @NotNull
    private Integer maxParticipants;

    @NotNull
    private Boolean openRegistration;

    /*@OneToMany(mappedBy = "competition")
    private List<Participation> participations;
}*/


}