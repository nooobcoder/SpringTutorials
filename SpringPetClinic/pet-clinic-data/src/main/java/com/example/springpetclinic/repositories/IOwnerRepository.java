package com.example.springpetclinic.repositories;

import com.example.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface IOwnerRepository extends CrudRepository<Owner, Long> {

}
