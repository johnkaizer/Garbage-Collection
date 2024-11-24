package com.project.garbagecollectionsys.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    // Create a new route
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    // Update an existing route
    public Route updateRoute(Long id, Route updatedRoute) {
        Optional<Route> routeOptional = routeRepository.findById(id);
        if (routeOptional.isEmpty()) {
            throw new IllegalArgumentException("Route not found with ID: " + id);
        }

        Route existingRoute = routeOptional.get();
        existingRoute.setRouteName(updatedRoute.getRouteName());
        existingRoute.setLandmarks(updatedRoute.getLandmarks());
        existingRoute.setScheduledDay(updatedRoute.getScheduledDay());

        return routeRepository.save(existingRoute);
    }

    // Delete a route by ID
    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new IllegalArgumentException("Route not found with ID: " + id);
        }
        routeRepository.deleteById(id);
    }

    // Get all routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Get a route by ID
    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + id));
    }
}

