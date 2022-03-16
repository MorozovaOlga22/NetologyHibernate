package com.example.hibernate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PERSONS")
@Data
@NoArgsConstructor
public class Person implements Serializable {
    @EmbeddedId
    private PersonId id;
    private String phoneNumber;
    private String cityOfLiving;
}