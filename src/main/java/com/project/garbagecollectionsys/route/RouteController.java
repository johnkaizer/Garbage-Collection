package com.project.garbagecollectionsys.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Get all routes
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    // Get route by ID
    @GetMapping("/{id}")
    public Optional<Route> getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id);
    }

    // Create a new route
    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

    // Update a route by ID
    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable Long id, @RequestBody Route route) {
        return routeService.updateRoute(id, route);
    }

    // Delete a route by ID
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
    }

    // Get routes by driver ID
    @GetMapping("/driver/{driverId}")
    public List<Route> getRoutesByDriverId(@PathVariable Long driverId) {
        return routeService.getRoutesByDriverId(driverId);
    }

    // Get routes by scheduled day
    @GetMapping("/scheduled-day/{scheduledDay}")
    public List<Route> getRoutesByScheduledDay(@PathVariable String scheduledDay) {
        return routeService.getRoutesByScheduledDay(scheduledDay);
    }
}

