package com.morimoto.taichi.moruku.controller.v1;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.morimoto.taichi.moruku.controller.v1.request.PracticeRequest;
import com.morimoto.taichi.moruku.controller.v1.response.PracticeDetailResponse;
import com.morimoto.taichi.moruku.controller.v1.response.PracticeResponse;
import com.morimoto.taichi.moruku.domain.entity.Practice;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;
import com.morimoto.taichi.moruku.exception.NotMyEntityException;
import com.morimoto.taichi.moruku.service.PracticeService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Validated
@RestController
@RequestMapping("/api/v1/practices")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping
    public List<PracticeResponse> getPractices(
        @RequestParam(value = "limit", required = false) Integer limit, 
        @RequestParam(value = "offset", required = false) Integer offset
    ) {
        if (limit == null) limit = 20;
        if (offset == null) offset = 0;

        List<Practice> practices = practiceService.getPractices(limit, offset);

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

    @GetMapping("/search")
    public List<PracticeResponse> searchPractices(
        @RequestParam(value = "limit", required = false) Integer limit, 
        @RequestParam(value = "offset", required = false) Integer offset,
        @RequestParam(value = "prefectureId", required = false) @Min(1) @Max(47) Integer prefectureId,
        @RequestParam(value = "from", required = false) LocalDate from,
        @RequestParam(value = "to", required = false) LocalDate to,
        @RequestParam(value = "keyword", required = false) String keyword
    ) {
        if (limit == null) limit = 20;
        if (offset == null) offset = 0;

        List<Practice> practices = practiceService.searchPractices(
            limit, 
            offset, 
            prefectureId,
            from,
            to,
            keyword
        );

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
    public PracticeDetailResponse getPracticeById(@PathVariable @Size(min=36, max=36) String uuid) throws NoSuchIdException {
        Practice practice = practiceService.getPracticeById(UUID.fromString(uuid));
        return PracticeDetailResponse.builder()
                .uuid(practice.getUuid().toString())
                .title(practice.getTitle())
                .description(practice.getDescription())
                .maxParticipants(practice.getMaxParticipants())
                .prefectureId(practice.getPrefectureId())
                .organizerId(practice.getOrganizerId().toString())
                .heldOn(practice.getHeldOn())
                .isJoined(practice.getIsJoined())
                .createdAt(practice.getCreatedAt())
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPractice(@RequestBody @Validated PracticeRequest practiceRequest) throws NoSuchIdException {
        Practice newPractice = new Practice();
        newPractice.setTitle(practiceRequest.getTitle());
        newPractice.setDescription(practiceRequest.getDescription());
        newPractice.setMaxParticipants(practiceRequest.getMaxParticipants());
        newPractice.setPrefectureId(practiceRequest.getPrefectureId());
        newPractice.setHeldOn(practiceRequest.getHeldOn());

        practiceService.insertPractice(newPractice);
    }
    
    @PutMapping("/{uuid}")
    public void updatePractice(
        @PathVariable @Size(min=36, max=36) String uuid, 
        @RequestBody @Validated PracticeRequest practiceRequest
    ) throws NoSuchIdException, NotMyEntityException {
        Practice newPractice = new Practice();
        newPractice.setUuid(UUID.fromString(uuid));
        newPractice.setTitle(practiceRequest.getTitle());
        newPractice.setDescription(practiceRequest.getDescription());
        newPractice.setMaxParticipants(practiceRequest.getMaxParticipants());
        newPractice.setPrefectureId(practiceRequest.getPrefectureId());
        newPractice.setHeldOn(practiceRequest.getHeldOn());
        
        practiceService.updatePractice(newPractice);
    }
    
    @DeleteMapping("/{uuid}")
    public void deletePractice(@PathVariable @Size(min=36, max=36) String uuid) throws NoSuchIdException {
        practiceService.deletePractice(UUID.fromString(uuid));
    }

    @PostMapping("/{uuid}/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinPractice(@PathVariable @Size(min=36, max=36) String uuid) throws NoSuchIdException {
        practiceService.joinPractice(UUID.fromString(uuid));
    }
    
    @DeleteMapping("/{uuid}/join")
    public void cancelPractice(@PathVariable @Size(min=36, max=36) String uuid) throws NoSuchIdException {
        practiceService.cancelPractice(UUID.fromString(uuid));
    }
}
