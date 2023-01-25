package com.backend.gymcoach.Repositories;

import com.backend.gymcoach.Entities.Exercise;
import com.backend.gymcoach.Entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByWorkout(Workout workout);
}
