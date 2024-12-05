package org.rabie.hunters_league.web.vm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.rabie.hunters_league.domain.enums.Role;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreatNewUserVm {
    @NotBlank @NonNull
    private String username;
    @NotBlank @NonNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
    private String password;
    @NotBlank @NonNull
    private String firstName;
    @NotBlank @NonNull
    private String lastName;
    @NotBlank @NonNull
    private String cin;
    private Role role;
    @NotBlank @NonNull
    private String email;
    @NotBlank @NonNull
    private String nationality;
    @NotNull
    private LocalDateTime licenseExpirationDate;
    @NotNull
    private LocalDateTime joinDate;
}