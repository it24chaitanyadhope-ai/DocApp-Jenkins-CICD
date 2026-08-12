package com.example.Controller;

import com.example.DocApp.Model.User;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // User Registration - Store new users in MongoDB
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> userData) {
        try {
            String name = userData.get("name");
            String email = userData.get("email");
            String password = userData.get("password");
            String role = userData.get("role") != null ? userData.get("role") : "PATIENT";
            
            // Validate input
            if (name == null || email == null || password == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Name, email, and password are required"));
            }
            
            // Check if user already exists
            if (userRepository.existsByEmail(email)) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email already registered"));
            }
            
            // Create new user and save to MongoDB
            User user = new User(name, email, password, role);
            User savedUser = userRepository.save(user);
            
            // Return success response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", Map.of(
                "id", savedUser.getId(),
                "name", savedUser.getName(),
                "email", savedUser.getEmail(),
                "role", savedUser.getRole()
            ));
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Registration failed: " + e.getMessage()));
        }
    }

    // User Login - Check users from MongoDB
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");
            
            // Validate input
            if (email == null || password == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email and password are required"));
            }
            
            // Find user by email in MongoDB
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "User not found. Please register first."));
            }
            
            User user = userOptional.get();
            
            // Check password
            if (!user.getPassword().equals(password)) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid password"));
            }
            
            // Login successful
            Map<String, Object> response = new HashMap<>();
            response.put("token", "mongodb-token-" + user.getId());
            response.put("user", Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole()
            ));
            response.put("message", "Login successful");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Login failed: " + e.getMessage()));
        }
    }

    // Get all users (for testing - remove in production)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/test")
    public String test() {
      return "AuthController is working!";
    }
}