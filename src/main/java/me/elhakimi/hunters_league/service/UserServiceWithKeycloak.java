package me.elhakimi.hunters_league.service;

import me.elhakimi.hunters_league.domain.AppUser;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceWithKeycloak implements UserDetailsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String KEYCLOAK_BASE_URL = "http://localhost:8080";
    private static final String REALM = "hakimi";
    private static final String CLIENT_ID = "huntersleague";
    private static final String CLIENT_SECRET = "TxZhoXj709iXL11ThJ4SyymdodovGilO";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("User loading is handled by Keycloak.");
    }

    public ResponseEntity<?> login(String username, String password) {
        String tokenEndpoint = KEYCLOAK_BASE_URL + "/realms/" + REALM + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=password"
                + "&client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET
                + "&username=" + username
                + "&password=" + password;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }


    public ResponseEntity<?> register(AppUser appUser) {
        String adminToken = getAdminToken();

        String createUserEndpoint = "http://localhost:8080/admin/realms/hakimi/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> userRequest = new HashMap<>();
        userRequest.put("username", appUser.getUsername());
        userRequest.put("enabled", true);
        userRequest.put("email", appUser.getEmail());
        userRequest.put("firstName", appUser.getFirstName());
        userRequest.put("lastName", appUser.getLastName());
        userRequest.put("credentials", List.of(Map.of(
                "type", "password",
                "value", appUser.getPassword(),
                "temporary", false
        )));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userRequest, headers);

        return restTemplate.exchange(createUserEndpoint, HttpMethod.POST, request, Map.class);
    }



    private String getAdminToken() {
        String tokenEndpoint = "http://localhost:8080/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=password"
                + "&client_id=admin-cli"
                + "&username=admin"
                + "&password=admin";  // Replace with actual admin password

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String token = response.getBody().get("access_token").toString();
            System.out.println("Admin Token: " + token); // Log the token for debugging
            return token;
        }
        throw new RuntimeException("Failed to obtain admin token from Keycloak");
    }


}
