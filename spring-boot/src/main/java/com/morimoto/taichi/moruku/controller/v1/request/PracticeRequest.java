package com.morimoto.taichi.moruku.controller.v1.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class PracticeRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @PositiveOrZero
    private Integer maxParticipants;

    @Min(1)
    @Max(47)
    private Integer prefectureId;
    
    @NotBlank
    private String organizerId;
    
    private LocalDate heldOn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Integer getPrefectureId() {
        return prefectureId;
    }

    public void setPrefectureId(Integer prefectureId) {
        this.prefectureId = prefectureId;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public LocalDate getHeldOn() {
        return heldOn;
    }

    public void setHeldOn(LocalDate heldOn) {
        this.heldOn = heldOn;
    }
    
} 