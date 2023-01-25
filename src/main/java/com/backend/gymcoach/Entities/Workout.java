package com.backend.gymcoach.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue
    @Column(name = "workoutId")
    private Long workoutId;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "muscleId")
    private Muscle muscleId;

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Muscle getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(Muscle muscleId) {
        this.muscleId = muscleId;
    }
}
