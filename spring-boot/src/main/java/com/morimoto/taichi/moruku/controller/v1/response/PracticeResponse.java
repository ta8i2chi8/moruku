package com.morimoto.taichi.moruku.controller.v1.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

// lombok:アノテーションを追加すると自動的にソースコードを生成してくれる
import lombok.*;

// 全てのフィールドを引数に持つコンストラクタを生成
@AllArgsConstructor
// フィールドのGetterを自動的に生成
@Getter
// Builderクラスを自動的に生成
@Builder
public class PracticeResponse {
    private String uuid;
    private String title;
    private String description;
    private Integer maxParticipants;
    private byte prefectureId;
    private String organizerId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate heldOn;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
