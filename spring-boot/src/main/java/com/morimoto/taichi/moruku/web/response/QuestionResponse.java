package com.morimoto.taichi.moruku.web.response;

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
public class QuestionResponse {
    private Integer id;
    private String content;
    private Integer attentionCount;
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime createdAt;
}
