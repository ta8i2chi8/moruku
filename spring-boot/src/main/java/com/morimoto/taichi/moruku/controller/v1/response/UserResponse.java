package com.morimoto.taichi.moruku.controller.v1.response;

// lombok:アノテーションを追加すると自動的にソースコードを生成してくれる
import lombok.*;

// 全てのフィールドを引数に持つコンストラクタを生成
@AllArgsConstructor
// フィールドのGetterを自動的に生成
@Getter
// Builderクラスを自動的に生成
@Builder
public class UserResponse {
    private String uuid;
    private String nickname;
    private String iconUrl;
    private String email;
    private String firebaseUid;
}
