package com.example.citybreak.city.service;

import com.example.citybreak.city.model.City;
import com.example.citybreak.city.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City createCity(City city) {

        if (cityRepository.existsByName(city.getName())) {
            throw new IllegalArgumentException("City already exists");
        }

        return cityRepository.save(city);
    }

    public City updateCity(Long id, City city) {
        return cityRepository.findById(id)
                .map(existingCity -> {
                    existingCity.setName(city.getName());
                    existingCity.setCountry(city.getCountry());
                    existingCity.setDetails(city.getDetails());
                    return cityRepository.save(existingCity);
                })
                .orElseThrow(() -> new IllegalArgumentException("City not found with id"));
    }

    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new IllegalArgumentException("City not found with id");
        }
        cityRepository.deleteById(id);
    }
}
