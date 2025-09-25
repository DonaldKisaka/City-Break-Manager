package com.example.citybreak.city.service;

import com.example.citybreak.city.model.City;
import com.example.citybreak.city.model.Trip;
import com.example.citybreak.city.repository.CityRepository;
import com.example.citybreak.city.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripsService {

    private final TripRepository tripRepository;
    private final CityRepository cityRepository;

    public TripsService(TripRepository tripRepository, CityRepository cityRepository) {
        this.tripRepository = tripRepository;
        this.cityRepository = cityRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip createTrip(Trip trip) {
        if (trip.getCity().getId() != null) {
            City city = cityRepository.findById(trip.getCity().getId())
                    .orElseThrow(() -> new IllegalArgumentException("City not found with id"));
            trip.setCity(city);
        }

        if (trip.getStartDate().isAfter(trip.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip trip) {
        return tripRepository.findById(id)
                .map(existingTrip -> {
                    if (trip.getCity() != null && trip.getCity().getId() != null) {
                        City city = cityRepository.findById(trip.getCity().getId())
                                .orElseThrow(() -> new IllegalArgumentException("City not found with id"));
                        existingTrip.setCity(city);
                    }
                    existingTrip.setStartDate(trip.getStartDate());
                    existingTrip.setEndDate(trip.getEndDate());
                    existingTrip.setRating(trip.getRating());
                    existingTrip.setPersonalNotes(trip.getPersonalNotes());
                    return tripRepository.save(existingTrip);
                })
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id"));
    }

    public void deleteTrip(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new IllegalArgumentException("Trip not found with id");
        }
        tripRepository.deleteById(id);
    }
}
