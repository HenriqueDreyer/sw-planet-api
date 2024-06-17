package com.example.sw_planet_api.web;

import com.example.sw_planet_api.domain.Planet;
import com.example.sw_planet_api.domain.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody Planet planet) {
        final var created = planetService.create(planet);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getById(@PathVariable(required = true, name = "id") Long id) {
        return planetService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> findByName(@PathVariable String name) {
        return planetService.findByName(name).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Planet>> findByQuery(@RequestParam(required = true, name = "climate") String climate,
                                                    @RequestParam(required = true, name = "terrain") String terrain
    ) {
        final var planets = planetService.findByQuery(climate, terrain);
        if(planets.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(planets);
    }
}
