package com.backend.gymcoach.Entities.Response;

public class ExerciseResponse {
    private Long exerciseId;
    private String name;

    public ExerciseResponse(Long exerciseId, String name) {
        this.exerciseId = exerciseId;
        this.name = name;
    }

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
}
