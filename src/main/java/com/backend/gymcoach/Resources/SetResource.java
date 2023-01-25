package com.backend.gymcoach.Resources;

import com.backend.gymcoach.Entities.Exercise;
import com.backend.gymcoach.Entities.Response.SetResponse;
import com.backend.gymcoach.Entities.Response.WorkoutResponse;
import com.backend.gymcoach.Entities.Set;
import com.backend.gymcoach.Entities.User;
import com.backend.gymcoach.Entities.Workout;
import com.backend.gymcoach.Repositories.ExerciseRepository;
import com.backend.gymcoach.Repositories.SetRepository;
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
public class SetResource {

    @Autowired
    private SetRepository setRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostMapping("/exercise/{id}/set")
    public ResponseEntity<Object> createSet(@PathVariable Long id, @RequestBody Set set) {
        set.setExercise(exerciseRepository.findById(id).get());
        Set savedSet = setRepository.save(set);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSet.getSetId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/exercise/{id}/set")
    public List<SetResponse> retrieveSets(@PathVariable Long id) {
        Exercise exercise = exerciseRepository.findById(id).get();

        List<SetResponse> responses = new ArrayList<>();
        List<Set> sets = setRepository.findAllByExercise(exercise);

        for (Set set : sets) {
            responses.add(new SetResponse(set.getSetId(), set.getWeight(), set.getRepetitions(), set.getMentions()));
        }

        return responses;
    }

    @PutMapping("/set/{id}")
    public ResponseEntity<Object> updateSet(@RequestBody Set set, @PathVariable Long id) {
        Optional<Set> setOptional = setRepository.findById(id);

        if (setOptional.isEmpty())
            return ResponseEntity.notFound().build();

        set.setSetId(id);
        set.setExercise(setOptional.get().getExercise());

        setRepository.save(set);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/set/{id}")
    public void deleteSet(@PathVariable Long id) {
        Optional<Set> setOptional = setRepository.findById(id);

        if (setOptional.isPresent())
            setRepository.deleteById(id);
    }

    @GetMapping("/set/{id}")
    public ResponseEntity<Set> retrieveSet(@PathVariable Long id) {
        Optional<Set> setOptional = setRepository.findById(id);

        if (setOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<Set>(setOptional.get(), HttpStatus.OK);
    }
}
