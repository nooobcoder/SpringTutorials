package com.example.springpetclinic.repositories;

import com.example.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface IPetRepository extends CrudRepository<Pet, Long> {
}
