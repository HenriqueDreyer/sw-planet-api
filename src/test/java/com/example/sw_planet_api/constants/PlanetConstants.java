package com.example.sw_planet_api.constants;

import com.example.sw_planet_api.domain.Planet;

public class PlanetConstants {
    public static final Planet PLANET_NO_ID = Planet.builder()
            .name("name")
            .climate("climate")
            .terrain("terrain")
            .build();

    public static final Planet PLANET_WITH_ID = Planet.builder()
            .id(1L)
            .name("name")
            .climate("climate")
            .terrain("terrain")
            .build();

    public static final Planet INVALID_PLANET = Planet.builder()
            .build();
}
