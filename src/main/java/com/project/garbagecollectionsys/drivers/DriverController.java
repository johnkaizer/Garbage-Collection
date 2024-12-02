package com.project.garbagecollectionsys.drivers;

import com.project.garbagecollectionsys.users.User;
import com.project.garbagecollectionsys.users.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.prefs.Preferences;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    // Create or Add a new driver
    @PostMapping
    public ResponseEntity<Drivers> createDriver(@RequestBody Drivers driver) {
        return ResponseEntity.ok(driverService.createDriver(driver));
    }

    // Fetch all Drivers
    @GetMapping
    public ResponseEntity<List<Drivers>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    // Search Drivers by name
    @GetMapping("/search")
    public ResponseEntity<List<Drivers>> searchDriversByName(@RequestParam String name) {
        return ResponseEntity.ok(driverService.searchDriversByName(name));
    }

    // Get a driver by ID
    @GetMapping("/{id}")
    public ResponseEntity<Drivers> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Edit or Update a driver
    @PutMapping("/{id}")
    public ResponseEntity<Drivers> updateDriver(@PathVariable Long id, @RequestBody Drivers updatedDriver) {
        try {
            return ResponseEntity.ok(driverService.updateDriver(id, updatedDriver));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a driver
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {
        String name = loginDetails.get("name");
        String password = loginDetails.get("password");

        // Authenticate the user
        Drivers drivers = driverService.authenticateUser(name, password);
        if (drivers != null) {
            // Prepare response data
            Map<String, String> response = new HashMap<>();
            if ("DRIVER".equals(drivers.getRole())) {
                response.put("redirectUrl", "/dashboard_driver");
            } else {
                response.put("redirectUrl", "/drivers_login");
            }

            // Save preferences (use preferences only if working with local storage like desktop apps)
            Preferences prefs = Preferences.userNodeForPackage(DriverController.class);
            prefs.putLong("loggedInDriverId", drivers.getId());
            prefs.put("loggedInUserRole", String.valueOf(drivers.getRole()));

            // Return response with the redirect URL
            return ResponseEntity.ok(response);
        } else {
            // Invalid credentials; return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid name or password");
        }
    }
    @PostMapping("/logout")
    public ModelAndView logout() {
        // Clear stored preferences
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        prefs.remove("loggedInDriverId");
        prefs.remove("loggedInName");
        prefs.remove("loggedInUserRole");

        // Redirect to login page
        return new ModelAndView(new RedirectView("/"));
    }
    // GET: Get current logged-in driver details
    @GetMapping("/current")
    public ResponseEntity<Optional<Drivers>> getCurrentDriver() {
        Preferences prefs = Preferences.userNodeForPackage(DriverController.class);
        String loggedInDriverId = prefs.get("loggedInDriverId", null);
        if (loggedInDriverId != null) {
            Optional<Drivers> drivers = driverService.getDriverById(Long.valueOf(loggedInDriverId));
            if (drivers != null) {
                return new ResponseEntity<>(drivers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

