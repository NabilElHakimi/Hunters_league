package me.elhakimi.hunters_league.services;

import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.repositories.UserRepository;
import me.elhakimi.hunters_league.utils.HashPassword;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        Optional<User> checkUser = findByEmail(user.getEmail());
        if (checkUser.isPresent()) {
            return null;
        } else {
            user.setPassword(HashPassword.hashPassword(user.getPassword())) ;
            return userRepository.save(user);
        }
    }



    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}