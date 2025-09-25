package com.example.citybreak.city.repository;

import com.example.citybreak.city.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByCityId(Long cityId);

}
