package com.example.sw_planet_api.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.sw_planet_api.constants.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetService planetService;

    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        when(planetRepository.save(PLANET_NO_ID))
                .thenReturn(PLANET_WITH_ID);

        final var sut = this.planetService.create(PLANET_NO_ID);

        verify(planetRepository).save(PLANET_NO_ID);

        assertThat(sut).isEqualTo(PLANET_WITH_ID);
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void findById_ByExistingId_ReturnPlanet() {
        when(planetRepository.findById(1L))
                .thenReturn(Optional.of(PLANET_WITH_ID));

        final var sut = planetService.findById(1L);

        verify(planetRepository).findById(1L);
        assertThat(sut.get()).isEqualTo(PLANET_WITH_ID);
    }

    @Test
    public void findById_ByUnexistingId_ReturnsEmpty() {
        when(planetRepository.findById(1L))
                .thenReturn(Optional.empty());

        final var sut = planetService.findById(1L);

        verify(planetRepository).findById(1L);
        assertThat(sut.isEmpty()).isTrue();
    }

    @Test
    public void findByName_ByExistingName_ReturnPlanet() {
        final var name = "planet";
        when(planetRepository.findByName(anyString()))
                .thenReturn(Optional.of(PLANET_WITH_ID));

        final var sut = planetService.findByName(name);

        verify(planetRepository).findByName(name);
        assertThat(sut.get()).isNotNull();
    }

    @Test
    public void findByName_ByUnexistingName_ReturnEmpty() {
        final var name = "planet";
        when(planetRepository.findByName(anyString()))
                .thenReturn(Optional.empty());

        final var sut = planetService.findByName(name);

        verify(planetRepository).findByName(name);
        assertThat(sut.isEmpty()).isTrue();
    }

}