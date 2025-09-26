package com.example.citybreak.city.controller;

import com.example.citybreak.city.model.Trip;
import com.example.citybreak.city.model.User;
import com.example.citybreak.city.service.TripsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripsService tripsService;

    public TripController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(tripsService.getAllTrips(user.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            Optional<Trip> trip = tripsService.getTripById(id, user.getId());
            return trip.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@Valid @RequestBody Trip trip, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            Trip createdTrip = tripsService.createTrip(trip, user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @Valid @RequestBody Trip trip, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            Trip updatedTrip = tripsService.updateTrip(id, trip, user.getId());
            return ResponseEntity.ok(updatedTrip);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            tripsService.deleteTrip(id, user.getId());
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
