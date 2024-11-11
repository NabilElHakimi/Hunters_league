package me.elhakimi.hunters_league.web.auth;


import jakarta.validation.Valid;
import me.elhakimi.hunters_league.domain.User;
import me.elhakimi.hunters_league.dto.UserDTO;
import me.elhakimi.hunters_league.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Login {

    private UserServiceImpl userServiceImpl;

    public Login(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid User user) {
        UserDTO loggedUser = userServiceImpl.login(user.getEmail(), user.getPassword());

        if (loggedUser != null) {
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Email or password is incorrect");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }


    }
