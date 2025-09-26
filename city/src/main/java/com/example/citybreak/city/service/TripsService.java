package com.example.citybreak.city.service;

import com.example.citybreak.city.model.City;
import com.example.citybreak.city.model.Trip;
import com.example.citybreak.city.model.User;
import com.example.citybreak.city.repository.CityRepository;
import com.example.citybreak.city.repository.TripRepository;
import com.example.citybreak.city.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripsService {

    private final TripRepository tripRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    public TripsService(TripRepository tripRepository, CityRepository cityRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
    }

    public List<Trip> getAllTrips(Long userId) {
        return tripRepository.findByUserId(userId);
    }

    public Optional<Trip> getTripById(Long id, Long userId) {
        return tripRepository.findByIdAndUserId(id, userId);
    }

    public Trip createTrip(Trip trip, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (trip.getCity().getId() != null) {
            City city = cityRepository.findByIdAndUserId(trip.getCity().getId(), userId)
                    .orElseThrow(() -> new IllegalArgumentException("City not found for this user"));
            trip.setCity(city);
        }

        if (trip.getStartDate().isAfter(trip.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        trip.setUser(user);
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip trip, Long userId) {
        return tripRepository.findByIdAndUserId(id, userId)
                .map(existingTrip -> {
                    if (trip.getCity() != null && trip.getCity().getId() != null) {
                        City city = cityRepository.findByIdAndUserId(trip.getCity().getId(), userId)
                                .orElseThrow(() -> new IllegalArgumentException("City not found for this user"));
                        existingTrip.setCity(city);
                    }
                    existingTrip.setStartDate(trip.getStartDate());
                    existingTrip.setEndDate(trip.getEndDate());
                    existingTrip.setRating(trip.getRating());
                    existingTrip.setPersonalNotes(trip.getPersonalNotes());
                    return tripRepository.save(existingTrip);
                })
                .orElseThrow(() -> new IllegalArgumentException("Trip not found for this user"));
    }

    public void deleteTrip(Long id, Long userId) {
        if (!tripRepository.existsById(id)) {
            throw new IllegalArgumentException("Trip not found with id");
        }

        Trip trip = tripRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found for this user"));
        tripRepository.delete(trip);



    }
}
