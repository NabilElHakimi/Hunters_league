package me.elhakimi.hunters_league.web.api;

import jakarta.validation.Valid;
import me.elhakimi.hunters_league.domain.AppUser;
import me.elhakimi.hunters_league.service.UserService;
import me.elhakimi.hunters_league.util.JwtUtil;
import me.elhakimi.hunters_league.web.vm.mapper.UserMapper;
import me.elhakimi.hunters_league.web.vm.request.CreatNewUserVm;
import me.elhakimi.hunters_league.web.vm.request.LoginVM;
import me.elhakimi.hunters_league.web.vm.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthController(UserService userService, UserMapper userMapper, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<CreatNewUserVm> register(@Valid @RequestBody CreatNewUserVm creatNewUserVm) {
        AppUser appUser = userMapper.toUser(creatNewUserVm);
        AppUser savedAppUser = userService.save(appUser);
        return ResponseEntity.ok(userMapper.toUserVm(savedAppUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginVM authRequest) {

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
//        return ResponseEntity.of(Optional.ofNullable(userDetails.getAuthorities()));
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
