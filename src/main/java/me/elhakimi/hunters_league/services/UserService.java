package me.elhakimi.hunters_league.services;

import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.repositories.UserRepository;
import me.elhakimi.hunters_league.utils.HashPassword;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public Optional<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
