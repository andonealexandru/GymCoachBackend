package com.backend.gymcoach.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue
    @Column(name = "exerciseId")
    private Long exerciseId;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workout;

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
