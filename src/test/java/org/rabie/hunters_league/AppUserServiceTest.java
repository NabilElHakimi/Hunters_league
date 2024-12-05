package org.rabie.hunters_league;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rabie.hunters_league.domain.AppUser;
import org.rabie.hunters_league.domain.enums.Role;
import org.rabie.hunters_league.repository.UserRepository;
import org.rabie.hunters_league.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private static PasswordEncoder passwordEncoder;



    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    static Stream<AppUser> userGenerateOld() {
        AppUser newAppUser = AppUser.builder()
                .username("newUser123")
                .password("password")
                .role(Role.MEMBER)
                .firstName("John")
                .lastName("Doe")
                .cin("X1234567")
                .email("john.doe@example.com")
                .nationality("American")
                .joinDate(LocalDateTime.now())
                .licenseExpirationDate(LocalDateTime.now().plusYears(1))
                .build();
        return Stream.of(newAppUser);
    }




//    @ParameterizedTest
//    @MethodSource("userGenerateOld")
//    void testSaveUserWithUsernameExist(User user) {
//        UserSearchDto searchDto = new UserSearchDto();
//        searchDto.setUsername(user.getUsername());
//
//        when(userRepository.findOne(UserSpecification.getUsersByCriteria(searchDto)))
//                .thenReturn(Optional.of(user));
//        assertThrows(UserAlreadyExistsException.class, () -> userService.save(user));
//    }
//
//    @ParameterizedTest
//    @MethodSource("userGenerateOld")
//    void testSaveUserWithEmailExist(User user){
//        UserSearchDto searchDto = new UserSearchDto();
//        searchDto.setEmail(user.getEmail());
//        when(userRepository.findOne(UserSpecification.getUsersByCriteria(searchDto)))
//                .thenReturn(Optional.of(user));
//        assertThrows(UserNotExistException.class, () -> {
//            userService.save(user);
//        });
//    }
//    @ParameterizedTest
//    @MethodSource("userGenerateOld")
//    void testSaveUserNull(User user){
//        assertThrows(UserNotExistException.class, () -> {
//            userService.save(null);
//        });
//    }
}
