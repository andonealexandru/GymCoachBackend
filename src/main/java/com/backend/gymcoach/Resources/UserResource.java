package com.backend.gymcoach.Resources;

import com.backend.gymcoach.Entities.User;
import com.backend.gymcoach.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    private ResponseEntity<User> retrieveUser(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/login")
    private ResponseEntity<User> retrieveUserByUsername(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findUserByUsername(user.getUsername());

        if (foundUser.isEmpty())
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        if (!Objects.equals(user.getPassword(), foundUser.get().getPassword()))
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<User>(foundUser.get(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
            userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getUserId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
