package com.backend.gymcoach.Repositories;

import com.backend.gymcoach.Entities.Exercise;
import com.backend.gymcoach.Entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findAllByExercise(Exercise exercise);
}
