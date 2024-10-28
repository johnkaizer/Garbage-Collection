package com.project.garbagecollectionsys.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route updatedRoute) {
        return routeRepository.findById(id).map(route -> {
            route.setRouteName(updatedRoute.getRouteName());
            route.setLandmarks(updatedRoute.getLandmarks());
            route.setScheduledDay(updatedRoute.getScheduledDay());
            route.setDriver(updatedRoute.getDriver());
            return routeRepository.save(route);
        }).orElseThrow(() -> new RuntimeException("Route not found"));
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    public List<Route> getRoutesByDriverId(Long driverId) {
        return routeRepository.findByDriverId(driverId);
    }

    public List<Route> getRoutesByScheduledDay(String scheduledDay) {
        return routeRepository.findByScheduledDay(scheduledDay);
    }
}
