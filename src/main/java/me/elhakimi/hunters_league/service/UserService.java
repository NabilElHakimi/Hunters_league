package me.elhakimi.hunters_league.service;

import me.elhakimi.hunters_league.domain.AppUser;
import me.elhakimi.hunters_league.domain.enums.Role;
import me.elhakimi.hunters_league.exceptions.UserAlreadyExistsException;
import me.elhakimi.hunters_league.exceptions.UserPasswordWrongException;
import me.elhakimi.hunters_league.exceptions.UserNotExistException;
import me.elhakimi.hunters_league.repository.UserRepository;
import me.elhakimi.hunters_league.service.dto.UserSearchDto;
import me.elhakimi.hunters_league.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public AppUser getById(UUID id) {
        return userRepository.findById(id).orElse(new AppUser());
    }

    public AppUser save(AppUser appUser) {
        if(appUser == null) throw new UserNotExistException("User does not exist");
        if(appUser.getRole()==null) appUser.setRole(Role.MEMBER);
        appUser.setJoinDate(LocalDateTime.now());
        appUser.setLicenseExpirationDate(LocalDateTime.now().plusMonths(1));
        UserSearchDto searchDtoByUsername = new UserSearchDto();
        UserSearchDto searchDtoByEmail = new UserSearchDto();
        searchDtoByEmail.setEmail(appUser.getEmail());
        searchDtoByUsername.setUsername(appUser.getUsername());
        if(userRepository.findOne(UserSpecification.getUsersByCriteria(searchDtoByEmail)).isPresent())
            throw new UserAlreadyExistsException("User with email : " + appUser.getEmail() + " already exist");
        if(userRepository.findOne(UserSpecification.getUsersByCriteria(searchDtoByUsername)).isPresent())
            throw new UserAlreadyExistsException("User with username : " + appUser.getUsername() + " already exist");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    public AppUser login(AppUser appUser){
        UserSearchDto searchDto = new UserSearchDto();
        searchDto.setEmail(appUser.getEmail());
        AppUser appUserFromDb = userRepository.findOne(UserSpecification.getUsersByCriteria(searchDto)).orElse(null);
        if(appUserFromDb == null) throw new UserNotExistException("User with email: " + appUser.getEmail() + " not found");
        else {
            boolean isPasswordMatch = passwordEncoder.matches(appUser.getPassword(), appUserFromDb.getPassword());
            if (!isPasswordMatch) throw new UserPasswordWrongException("Error : Password is wrong");
            return appUserFromDb;
        }
    }
    public AppUser update(AppUser appUser) {
        return userRepository.save(appUser);
    }


    public Page<AppUser> searchUsers(UserSearchDto searchDto, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(UserSpecification.getUsersByCriteria(searchDto), pageRequest);
    }
    public void delete(AppUser appUser) {
        if(appUser == null) throw new UserNotExistException("User does not exist");
        userRepository.delete(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSearchDto searchDto = new UserSearchDto();
        searchDto.setUsername(username);
        AppUser appUserFromDb = userRepository.findOne(UserSpecification.getUsersByCriteria(searchDto)).orElse(null);
        if(appUserFromDb == null) throw new UserNotExistException("User with username: " + username + " not found");

        return appUserFromDb;
    }

    public  String getCurrentUserName(){
        return
                SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public AppUser getByUserName(String username) {
        UserSearchDto searchDto = new UserSearchDto();
        searchDto.setUsername(username);
        return userRepository.findOne(UserSpecification.getUsersByCriteria(searchDto)).orElse(null);
    }

}

