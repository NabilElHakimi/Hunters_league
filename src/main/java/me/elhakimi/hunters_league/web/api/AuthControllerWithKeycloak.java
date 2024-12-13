package me.elhakimi.hunters_league.web.api;

import me.elhakimi.hunters_league.domain.AppUser;
import me.elhakimi.hunters_league.service.UserServiceWithKeycloak;
import me.elhakimi.hunters_league.web.vm.request.LoginVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/keycloak/")
public class AuthControllerWithKeycloak {

    private final UserServiceWithKeycloak userServiceWithKeycloak;


    public AuthControllerWithKeycloak(UserServiceWithKeycloak userServiceWithKeycloak) {
        this.userServiceWithKeycloak = userServiceWithKeycloak;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        return userServiceWithKeycloak.login(loginRequest.get("username"), loginRequest.get("password"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser appUser) {
        return userServiceWithKeycloak.register(appUser);
    }
}
