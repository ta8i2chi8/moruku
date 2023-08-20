package com.morimoto.taichi.moruku.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// lombok:アノテーションを追加すると自動的にソースコードを生成してくれる
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// パラメータのないデフォルトコンストラクタを自動的に生成
@NoArgsConstructor
// 全てのフィールドを引数に持つコンストラクタを生成
@AllArgsConstructor
// @Getter,@Setter,@ToString,@EqualsAndHashCodeをまとめて使用
@Data
// Builderクラスを自動的に生成
@Builder
public class Practice {
    private UUID uuid;
    private String title;
    private String description;
    private Integer maxParticipants;
    private Integer prefectureId;
    private UUID organizerId;
    private LocalDate heldOn;
    private LocalDateTime createdAt;
}