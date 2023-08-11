package com.morimoto.taichi.moruku.controller.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.morimoto.taichi.moruku.controller.v1.request.PracticeRequest;
import com.morimoto.taichi.moruku.controller.v1.response.PracticeResponse;
import com.morimoto.taichi.moruku.domain.entity.Practice;
import com.morimoto.taichi.moruku.domain.service.PracticeService;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;


@RestController
@RequestMapping("/api/v1/practices")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping
    public List<PracticeResponse> findAll() {
        List<Practice> practices = practiceService.findAll();

        List<PracticeResponse> responses = new ArrayList<>();
        for (Practice practice : practices) {
            responses.add(new PracticeResponse(
                practice.getUuid().toString(), 
                practice.getTitle(), 
                practice.getDescription(), 
                practice.getMaxParticipants(), 
                practice.getPrefectureId(), 
                practice.getOrganizerId().toString(), 
                practice.getHeldOn(), 
                practice.getCreatedAt()
            ));
        }
        return responses;
    }

    @GetMapping("/{uuid}")
    public PracticeResponse findById(@PathVariable String uuid) throws NoSuchIdException {
        Practice practice = practiceService.findById(UUID.fromString(uuid));
        return PracticeResponse.builder()
                .uuid(practice.getUuid().toString())
                .title(practice.getTitle())
                .description(practice.getDescription())
                .maxParticipants(practice.getMaxParticipants())
                .prefectureId(practice.getPrefectureId())
                .organizerId(practice.getOrganizerId().toString())
                .heldOn(practice.getHeldOn())
                .createdAt(practice.getCreatedAt())
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Validated PracticeRequest practiceRequest) throws NoSuchIdException {
        Practice newPractice = new Practice();
        newPractice.setTitle(practiceRequest.getTitle());
        newPractice.setDescription(practiceRequest.getDescription());
        newPractice.setMaxParticipants(practiceRequest.getMaxParticipants());
        newPractice.setPrefectureId(practiceRequest.getPrefectureId());
        newPractice.setOrganizerId(UUID.fromString(practiceRequest.getOrganizerId()));
        newPractice.setHeldOn(practiceRequest.getHeldOn());

        practiceService.insert(newPractice);
    }

    @PutMapping("/{uuid}")
    public void update(@PathVariable String uuid, @RequestBody @Validated PracticeRequest practiceRequest) throws NoSuchIdException {
        Practice newPractice = new Practice();
        newPractice.setUuid(UUID.fromString(uuid));
        newPractice.setTitle(practiceRequest.getTitle());
        newPractice.setDescription(practiceRequest.getDescription());
        newPractice.setMaxParticipants(practiceRequest.getMaxParticipants());
        newPractice.setPrefectureId(practiceRequest.getPrefectureId());
        newPractice.setOrganizerId(UUID.fromString(practiceRequest.getOrganizerId()));
        newPractice.setHeldOn(practiceRequest.getHeldOn());

        practiceService.update(newPractice);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid) throws NoSuchIdException {
        practiceService.delete(UUID.fromString(uuid));
    }
}
