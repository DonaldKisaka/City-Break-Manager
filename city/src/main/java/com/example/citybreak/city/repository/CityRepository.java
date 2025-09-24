package com.example.citybreak.city.repository;

import com.example.citybreak.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
