package me.elhakimi.hunters_league.web.user;

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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private  final UserServiceImpl userServiceImpl;

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable UUID id, @RequestBody @Valid User user) {
        try {

            user.setId(id);
            UserDTO updatedUser = userServiceImpl.update(user);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByUserName/{userName}")
    public ResponseEntity<Object> findByUserName(@PathVariable String userName) {
        Optional<UserDTO> userOptional = userServiceImpl.findByUserName(userName);

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
        UserDTO user = userServiceImpl.findByEmail(email).orElse(null);

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}
