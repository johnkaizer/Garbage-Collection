package com.project.garbagecollectionsys.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Object> findByRouteName(String routeName);
    // Add custom queries if needed
}
