package me.elhakimi.hunters_league.web.auth;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor

public class Register {

    UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<Object> save(@RequestBody @Valid User user) {
        UserDTO savedUser = userServiceImpl.save(user);
        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}
