package me.elhakimi.hunters_league.web.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private  final  UserService userService;


    @PostMapping("/update")
    public User update(@RequestBody @Valid User user) {
        return userService.update(user);

    }

}
