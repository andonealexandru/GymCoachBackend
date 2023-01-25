package com.backend.gymcoach.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "set")
public class Set {

    @Id
    @GeneratedValue
    @Column(name = "setId")
    private Long setId;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "repetitions")
    private Integer repetitions;
    @Column(name = "mentions")
    private String mentions;
    @ManyToOne
    @JoinColumn(name = "exerciseId")
    private Exercise exercise;

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public String getMentions() {
        return mentions;
    }

    public void setMentions(String mentions) {
        this.mentions = mentions;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
