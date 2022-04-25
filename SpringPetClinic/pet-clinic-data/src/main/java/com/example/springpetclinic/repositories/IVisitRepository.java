package com.example.springpetclinic.repositories;

import com.example.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface IVisitRepository extends CrudRepository<Visit, Long> {
}
