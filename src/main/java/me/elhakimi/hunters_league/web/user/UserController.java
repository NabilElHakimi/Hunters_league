package me.elhakimi.hunters_league.web.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.dto.UserDTO;
import me.elhakimi.hunters_league.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private  final  UserService userService;

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        try {
            User updatedUser = userService.update(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByUserName/{userName}")
    public ResponseEntity<Object> findByUserName(@PathVariable String userName) {
        // Retrieve the user DTO
        Optional<UserDTO> userOptional = userService.findByUserName(userName);

        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email).orElse(null);

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}
