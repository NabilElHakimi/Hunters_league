package me.elhakimi.hunters_league.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.elhakimi.hunters_league.domains.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private UUID id;

    private String username;

    private Role role;

    private String firstName;

    private String lastName;

    private String cin;

    @NotBlank
    @NotNull
    @Email
    private String email;

    private String nationality;

    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

}
