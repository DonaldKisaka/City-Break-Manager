package com.example.citybreak.city.repository;

import com.example.citybreak.city.model.City;
import com.example.citybreak.city.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByName(String name);

    List<City> findByUserId(Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
    Optional<City> findByIdAndUserId(Long id, Long userId);

    Long user(User user);
}
