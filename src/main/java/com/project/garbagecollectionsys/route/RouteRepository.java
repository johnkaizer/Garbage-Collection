package com.project.garbagecollectionsys.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByDriverId(Long driverId);
    List<Route> findByScheduledDay(String scheduledDay);
}

