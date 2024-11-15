package me.elhakimi.hunters_league.service;

import me.elhakimi.hunters_league.domain.User;
import me.elhakimi.hunters_league.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    UserDTO login(String email, String password);
    UserDTO save(User user);
    UserDTO update(User user);
    Optional<UserDTO> findByUserName(String username);
    Optional<UserDTO> findByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
