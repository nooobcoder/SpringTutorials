package com.example.springpetclinic.repositories;

import com.example.springpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface IVetRepository extends CrudRepository<Vet, Long> {
}
