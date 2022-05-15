package com.example.springpetclinic.services.map;

import com.example.springpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpecialtyMapServiceTest {
    private static final Long SPECIALTY_ID = 1L;

    private SpecialtyMapService SpecialtyMapService;

    @BeforeEach
    void setUp() {
        SpecialtyMapService = new SpecialtyMapService();

        SpecialtyMapService.save(Specialty.builder().id(SPECIALTY_ID).build());
    }

    @Test
    void findAll() {
        final var specialities = SpecialtyMapService.findAll();
        assertEquals(1, specialities.size());
    }

    @Test
    void findByExistingId() {
        final var Specialty = SpecialtyMapService.findById(SPECIALTY_ID);
        assertEquals(SPECIALTY_ID, Specialty.getId());
    }

    @Test
    void findByNotExistingId() {
        final var Specialty = SpecialtyMapService.findById(5L);
        assertNull(Specialty);
    }

    @Test
    void findByIdNullId() {
        final var Specialty = SpecialtyMapService.findById(null);
        assertNull(Specialty);
    }

    @Test
    void saveExistingId() {
        final Long id = 2L;

        final var Specialty2 = Specialty.builder().id(id).build();

        final var savedSpecialty = SpecialtyMapService.save(Specialty2);

        assertEquals(id, savedSpecialty.getId());
    }

    @Test
    void saveDuplicateId() {

        final Long id = 1L;

        final var Specialty2 = Specialty.builder().id(id).build();

        final var savedSpecialty = SpecialtyMapService.save(Specialty2);

        assertEquals(id, savedSpecialty.getId());
        assertEquals(1, SpecialtyMapService.findAll().size());
    }

    @Test
    void saveNoId() {

        final var savedSpecialty = SpecialtyMapService.save(Specialty.builder().build());

        assertNotNull(savedSpecialty);
        assertNotNull(savedSpecialty.getId());
        assertEquals(2, SpecialtyMapService.findAll().size());
    }

    @Test
    void deletePetType() {

        SpecialtyMapService.delete(SpecialtyMapService.findById(SPECIALTY_ID));

        assertEquals(0, SpecialtyMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId() {

        final var speciality = Specialty.builder().id(5L).build();

        SpecialtyMapService.delete(speciality);

        assertEquals(1, SpecialtyMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        final var speciality = Specialty.builder().build();

        SpecialtyMapService.delete(speciality);

        assertEquals(1, SpecialtyMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        SpecialtyMapService.delete(null);

        assertEquals(1, SpecialtyMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        SpecialtyMapService.deleteById(SPECIALTY_ID);

        assertEquals(0, SpecialtyMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        SpecialtyMapService.deleteById(5L);

        assertEquals(1, SpecialtyMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        SpecialtyMapService.deleteById(null);

        assertEquals(1, SpecialtyMapService.findAll().size());
    }
}
