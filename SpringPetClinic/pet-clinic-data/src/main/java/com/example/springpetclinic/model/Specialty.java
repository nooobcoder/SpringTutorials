package com.example.springpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialties")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Specialty extends BaseEntity {
    @Column(name = "description")
    private String description;

    @Builder
    public Specialty(Long id, String description) {
        super(id);
        this.description = description;
    }
}
