package org.rabie.hunters_league.web.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.rabie.hunters_league.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponseVm {
    private UUID id;
    private String username;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String cin;
    private String nationality;
    private LocalDateTime licenseExpirationDate;
    private LocalDateTime joinDate;
}
