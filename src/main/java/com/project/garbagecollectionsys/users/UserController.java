package com.project.garbagecollectionsys.users;
import com.project.garbagecollectionsys.route.Route;
import com.project.garbagecollectionsys.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.prefs.Preferences;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phone) {
        return userService.getUserByPhone(phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {
        String username = loginDetails.get("username");
        String password = loginDetails.get("password");

        // Authenticate the user
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            // Prepare response data
            Map<String, String> response = new HashMap<>();
            if ("ADMIN".equals(user.getRole())) {
                response.put("redirectUrl", "/dashboard_admin");
            } else if ("USER".equals(user.getRole())) {
                response.put("redirectUrl", "/dashboard_user");
            } else {
                // Handle unknown roles if needed
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Access denied: Unknown role");
            }

            // Save preferences (use preferences only if working with local storage like desktop apps)
            Preferences prefs = Preferences.userNodeForPackage(UserController.class);
            prefs.putLong("loggedInUserId", user.getId());
            prefs.put("loggedInUserRole", user.getRole());

            // Return response with the redirect URL
            return ResponseEntity.ok(response);
        } else {
            // Invalid credentials; return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    // Get current logged-in user details and route details
    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> getCurrentUserWithRouteDetails() {
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        String loggedInUserId = prefs.get("loggedInUserId", null);
        if (loggedInUserId != null) {
            Optional<User> user = userService.getUserById(Long.valueOf(loggedInUserId));
            if (user != null) {
                Route route = routeService.getRouteByName(user.get().getRoute());
                Map<String, Object> response = new HashMap<>();
                response.put("user", user);
                response.put("route", route);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/currentUserId")
    public ResponseEntity<Long> getCurrentUserId() {
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        Long loggedInUserId = prefs.getLong("loggedInUserId", -1L);
        if (loggedInUserId != -1L) {
            System.out.println("Logged in User ID: " + loggedInUserId); // Debug log
            return new ResponseEntity<>(loggedInUserId, HttpStatus.OK);
        } else {
            System.out.println("No User ID found in preferences."); // Debug log
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        // Clear stored preferences
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        prefs.remove("loggedInUserId");
        prefs.remove("loggedInUsername");
        prefs.remove("loggedInUserRole");

        // Redirect to login page
        return new ModelAndView(new RedirectView("/"));
    }

}
