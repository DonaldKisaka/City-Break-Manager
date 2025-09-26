package com.example.citybreak.city.repository;

import com.example.citybreak.city.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByCityId(Long cityId);

    List<Trip> findByUserId(Long userId);
    List<Trip> findByCityIdAndUserId(Long cityId, Long userId);
    Optional<Trip> findByIdAndUserId(Long id, Long userId);

}
