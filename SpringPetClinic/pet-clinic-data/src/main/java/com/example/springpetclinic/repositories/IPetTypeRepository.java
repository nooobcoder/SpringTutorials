package com.example.springpetclinic.repositories;

import com.example.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface IPetTypeRepository extends CrudRepository<PetType, Long> {
}
