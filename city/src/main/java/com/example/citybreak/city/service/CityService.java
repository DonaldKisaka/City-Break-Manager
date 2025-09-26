package com.example.citybreak.city.service;

import com.example.citybreak.city.model.City;
import com.example.citybreak.city.model.User;
import com.example.citybreak.city.repository.CityRepository;
import com.example.citybreak.city.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final UserRepository userRepository;


    public CityService(CityRepository cityRepository, UserRepository userRepository) {
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
    }

    public List<City> getAllCities(Long userId) {
        return cityRepository.findByUserId(userId);
    }

    public Optional<City> getCityById(Long id, Long userId) {
        return cityRepository.findByIdAndUserId(id, userId);
    }

    public City createCity(City city, Long userId) {

        if (cityRepository.existsByNameAndUserId(city.getName(), userId)) {
            throw new IllegalArgumentException("City already exists for this user");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        city.setUser(user);


        return cityRepository.save(city);
    }

    public City updateCity(Long id, City city, Long userId) {
        return cityRepository.findByIdAndUserId(id, userId)
                .map(existingCity -> {
                    existingCity.setName(city.getName());
                    existingCity.setCountry(city.getCountry());
                    existingCity.setDetails(city.getDetails());
                    return cityRepository.save(existingCity);
                })
                .orElseThrow(() -> new IllegalArgumentException("City not found with id"));
    }

    public void deleteCity(Long id, Long userId) {
        if (!cityRepository.existsById(id)) {
            throw new IllegalArgumentException("City not found with id");
        }

        City city = cityRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new IllegalArgumentException("City not found for this user"));
        cityRepository.delete(city);

    }
}
