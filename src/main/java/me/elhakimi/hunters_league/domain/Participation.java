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
public class Participation{

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Competition competition;

    /*@OneToMany(mappedBy = "participation")
    private List<Hunt> hunts;*/

    private Double score;

}
