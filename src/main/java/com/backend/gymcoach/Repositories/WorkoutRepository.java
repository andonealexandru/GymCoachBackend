package com.backend.gymcoach.Repositories;

import com.backend.gymcoach.Entities.User;
import com.backend.gymcoach.Entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findAllByUserId(User user);
}
