package com.backend.gymcoach.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "muscle")
public class Muscle {

    @Id
    @GeneratedValue
    @Column(name = "muscleId")
    private Long muscleId;
    @Column(name = "name")
    private String name;

    public Long getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(Long muscleId) {
        this.muscleId = muscleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
