package me.elhakimi.hunters_league.domains;


import jakarta.persistence.*;
import lombok.*;
import me.elhakimi.hunters_league.domains.enums.Role;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;

    private String lastName;

    private String cin;

    private String email;

    private String nationality;

    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

/*
    @OneToMany(mappedBy = "user")
    private List<Participation> participations;
*/

}