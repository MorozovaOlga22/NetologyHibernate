package com.example.hibernate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class PersonId implements Serializable {
    private String name;
    private String surname;
    private int age;
}