package com.backend.gymcoach.Resources;

import com.backend.gymcoach.Entities.Exercise;
import com.backend.gymcoach.Entities.Response.ExerciseResponse;
import com.backend.gymcoach.Entities.Workout;
import com.backend.gymcoach.Repositories.ExerciseRepository;
import com.backend.gymcoach.Repositories.UserRepository;
import com.backend.gymcoach.Repositories.WorkoutRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExerciseResource {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostMapping("/workout/{id}/exercise")
    public ResponseEntity<Object> createExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        exercise.setWorkout(workoutRepository.findById(id).get());
        Exercise savedExercise = exerciseRepository.save(exercise);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedExercise.getExerciseId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/workout/{id}/exercises")
    public ResponseEntity<List<ExerciseResponse>> retrieveExercises(@PathVariable Long id) {
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        if (optionalWorkout.isEmpty())
            return ResponseEntity.notFound().build();

        List<ExerciseResponse> responses = new ArrayList<>();
        List<Exercise> exercises = exerciseRepository.findAllByWorkout(optionalWorkout.get());

        for (Exercise exercise : exercises) {
            responses.add(new ExerciseResponse(exercise.getExerciseId(), exercise.getName()));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/exercise/{id}")
    public ResponseEntity<Exercise> retrieveExercise(@PathVariable Long id) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isEmpty())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<>(optionalExercise.get(), HttpStatus.OK);
    }

    @DeleteMapping("/exercise/{id}")
    public void deleteExercise(@PathVariable Long id) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent())
            exerciseRepository.deleteById(id);
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity<Object> updateExercise(@RequestBody Exercise exercise, @PathVariable Long id) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isEmpty())
            return ResponseEntity.notFound().build();

        exercise.setExerciseId(id);
        exercise.setWorkout(optionalExercise.get().getWorkout());

        exerciseRepository.save(exercise);

        return ResponseEntity.noContent().build();
    }
}
