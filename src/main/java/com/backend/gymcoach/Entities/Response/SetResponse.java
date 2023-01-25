package com.backend.gymcoach.Entities.Response;

public class SetResponse {
    private Long setId;
    private Integer weight;
    private Integer repetitions;
    private String mentions;

    public SetResponse(Long setId, Integer weight, Integer repetitions, String mentions) {
        this.setId = setId;
        this.weight = weight;
        this.repetitions = repetitions;
        this.mentions = mentions;
    }

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
}
