package me.elhakimi.hunters_league.web.auth;

import jakarta.validation.Valid;
import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.repositories.UserRepository;
import me.elhakimi.hunters_league.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Register {

    UserService userService;


    public Register(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> save(@RequestBody @Valid User user) {
        User savedUser = userService.save(user);
        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}
