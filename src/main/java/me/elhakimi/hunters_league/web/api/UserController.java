package me.elhakimi.hunters_league.web.api;

import jakarta.validation.Valid;
import me.elhakimi.hunters_league.domain.AppUser;
import me.elhakimi.hunters_league.service.UserService;
import me.elhakimi.hunters_league.service.dto.UserSearchDto;
import me.elhakimi.hunters_league.web.vm.mapper.UserMapper;
import me.elhakimi.hunters_league.web.vm.request.UserUpdateVm;
import me.elhakimi.hunters_league.web.vm.response.UserResponseVm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/getAll")
    public Page<UserResponseVm> getUsers(@Valid UserSearchDto userSearchDto, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        return userService.searchUsers(userSearchDto,page, size).map(userMapper::toUserResponseVm);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseVm> updateUser(@Valid @RequestBody UserUpdateVm userUpdateVm) {
        AppUser appUser = userMapper.toUserFromUpdateVm(userUpdateVm);
        AppUser updatedAppUser = userService.update(appUser);
        return ResponseEntity.ok(userMapper.toUserResponseVm(updatedAppUser));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseVm> deleteUser(@PathVariable String id) {
        AppUser appUser = userService.getById(UUID.fromString(id));
        userService.delete(appUser);
        return ResponseEntity.ok(userMapper.toUserResponseVm(appUser));
    }

}
