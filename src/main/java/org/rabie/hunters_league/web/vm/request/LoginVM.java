package org.rabie.hunters_league.web.vm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LoginVM {

    @NotBlank @NonNull
    private String username;


    @NotBlank @NonNull
    private String password;
}
