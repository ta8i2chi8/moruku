package com.morimoto.taichi.moruku.domain.entity;

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
public class User {
    private UUID uuid;
    private String nickname;
    private String iconUrl;
    private String email;
    private String firebaseUid;
}