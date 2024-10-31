package me.elhakimi.hunters_league.services;

import lombok.AllArgsConstructor;
import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.dto.UserDTO;
import me.elhakimi.hunters_league.dto.mappers.UserMapper;
import me.elhakimi.hunters_league.repositories.UserRepository;
import me.elhakimi.hunters_league.utils.HashPassword;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final  UserRepository userRepository;
    private final UserMapper userMapper;


    public User login(String email, String password) {
        Optional<User> user = findByEmail(email);
        if (user.isPresent()) {
            if (HashPassword.checkPassword(password, user.get().getPassword())) {
                return user.get();
            }
        }
        return null;
    }

    public User save(User user) {
        Optional<User> checkUser = findByEmail(user.getEmail());
        if (checkUser.isPresent()) {
            return null;
        } else {
            user.setJoinDate(LocalDateTime.now());
            user.setLicenseExpirationDate(LocalDateTime.now().plusMonths(1));
            user.setPassword(HashPassword.hashPassword(user.getPassword())) ;
            return userRepository.save(user);
        }
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public Optional<UserDTO> findByUserName(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
