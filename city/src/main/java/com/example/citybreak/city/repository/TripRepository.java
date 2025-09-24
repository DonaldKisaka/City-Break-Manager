package com.example.citybreak.city.repository;

import com.example.citybreak.city.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
