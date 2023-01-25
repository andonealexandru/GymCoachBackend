package com.backend.gymcoach.Entities.Response;

import com.backend.gymcoach.Entities.Muscle;

public class WorkoutResponse {
    private Long workoutId;
    private String name;
    private Muscle muscle;

    public WorkoutResponse(Long workoutId, String name, Muscle muscle) {
        this.workoutId = workoutId;
        this.name = name;
        this.muscle = muscle;
    }

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

    public Muscle getMuscle() {
        return muscle;
    }

    public void setMuscle(Muscle muscle) {
        this.muscle = muscle;
    }
}
