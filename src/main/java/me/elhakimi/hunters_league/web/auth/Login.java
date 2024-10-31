package me.elhakimi.hunters_league.web.auth;


import jakarta.validation.Valid;
import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.dto.UserDTO;
import me.elhakimi.hunters_league.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Login {

    private  UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid User user) {
        UserDTO loggedUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedUser != null) {
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Email or password is incorrect");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    }
