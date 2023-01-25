package com.backend.gymcoach.Resources;

import com.backend.gymcoach.Entities.Response.WorkoutResponse;
import com.backend.gymcoach.Entities.User;
import com.backend.gymcoach.Entities.Workout;
import com.backend.gymcoach.Repositories.UserRepository;
import com.backend.gymcoach.Repositories.WorkoutRepository;
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
public class WorkoutResource {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/{id}/workout")
    public ResponseEntity<Object> createWorkout(@PathVariable Long id, @RequestBody Workout workout) {
        workout.setUserId(userRepository.findById(id).get());
        Workout savedWorkout = workoutRepository.save(workout);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWorkout.getWorkoutId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/workouts")
    public List<WorkoutResponse> retrieveWorkouts(@PathVariable Long id) {
        User user = userRepository.findById(id).get();

        List<WorkoutResponse> responses = new ArrayList<>();
        List<Workout> workouts = workoutRepository.findAllByUserId(user);

        for (Workout workout : workouts) {
            responses.add(new WorkoutResponse(workout.getWorkoutId(), workout.getName(), workout.getMuscleId()));
        }

        return responses;
    }

    @PutMapping("/workout/{id}")
    public ResponseEntity<Object> updateWorkout(@RequestBody Workout workout, @PathVariable Long id) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);

        if (workoutOptional.isEmpty())
            return ResponseEntity.notFound().build();

        workout.setWorkoutId(id);
        workout.setUserId(workoutOptional.get().getUserId());

        workoutRepository.save(workout);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/workout/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);

        if (workoutOptional.isPresent())
            workoutRepository.deleteById(id);
    }

    @GetMapping("/workout/{id}")
    public ResponseEntity<Workout> retrieveWorkout(@PathVariable Long id) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);

        if (workoutOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<Workout>(workoutOptional.get(), HttpStatus.OK);
    }
}
