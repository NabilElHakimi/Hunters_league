package org.rabie.hunters_league.web.vm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.rabie.hunters_league.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserUpdateVm {
    @NotNull
    private UUID id;
    @NotBlank @NonNull
    private String username;
    @NotBlank @NonNull
    private String email;
    @NotNull
    private Role role;
    @NotBlank @NonNull
    private String firstName;
    @NotBlank @NonNull
    private String lastName;
    @NotBlank @NonNull
    private String cin;
    @NotBlank @NonNull
    private String nationality;
    @NotNull
    private LocalDateTime licenseExpirationDate;
}
