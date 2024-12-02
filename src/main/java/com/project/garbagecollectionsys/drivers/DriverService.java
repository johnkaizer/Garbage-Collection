package com.project.garbagecollectionsys.drivers;

import com.project.garbagecollectionsys.enums.Role;
import com.project.garbagecollectionsys.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    // Create or Add a new driver with role DRIVER by default
    public Drivers createDriver(Drivers driver) {
        driver.setRole("DRIVER"); // Set default role
        return driverRepository.save(driver);
    }

    // Fetch all Drivers
    public List<Drivers> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Search Drivers by name
    public List<Drivers> searchDriversByName(String name) {
        return driverRepository.findByNameContainingIgnoreCase(name);
    }

    // Get a driver by ID
    public Optional<Drivers> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    // Edit or Update a driver
    public Drivers updateDriver(Long id, Drivers updatedDriver) {
        return driverRepository.findById(id).map(driver -> {
            driver.setName(updatedDriver.getName());
            driver.setPhone(updatedDriver.getPhone());
            driver.setRouteAssigned(updatedDriver.getRouteAssigned());
            driver.setPassword(updatedDriver.getPassword());
            driver.setRole(updatedDriver.getRole());
            return driverRepository.save(driver);
        }).orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    // Delete a driver
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public Drivers authenticateUser(String name, String password) {
        // Query the repository to find a user with the provided name
        Drivers drivers = driverRepository.findByName(name);

        // If user is found and password matches, return the user
        if (drivers != null && drivers.getPassword().equals(password)) {
            return drivers;
        } else {
            return null; // Authentication failed
        }
    }
}
