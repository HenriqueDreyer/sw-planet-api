package com.example.sw_planet_api.domain;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {
    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet create(Planet planet) {
        return planetRepository.save(planet);
    }

    public Optional<Planet> findById(Long id) {
        return planetRepository.findById(id);
    }

    public List<Planet> findByQuery(String climate, String terrain) {
        return Collections.emptyList();
    }

    public Optional<Planet> findByName(String name) {
        return planetRepository.findByName(name);
    }
}
