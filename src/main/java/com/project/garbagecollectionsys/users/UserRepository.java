package com.project.garbagecollectionsys.users;

import com.project.garbagecollectionsys.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
}

