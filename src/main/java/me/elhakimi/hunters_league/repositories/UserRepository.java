package me.elhakimi.hunters_league.repositories;

import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.domains.enums.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
            Optional<User> findByEmail(String email);
            Optional<User> findByUsername(String username);
}

