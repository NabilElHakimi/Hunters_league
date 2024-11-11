package me.elhakimi.hunters_league.services;

import me.elhakimi.hunters_league.domains.User;
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
