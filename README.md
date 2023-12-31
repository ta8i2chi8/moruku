# モルク
## 環境構築手順
1. envファイルを作成
* .env.local（場所：docker > db 配下）
  * MYSQL_DATABASE
  * MYSQL_ROOT_PASSWORD
  * MYSQL_USER
  * MYSQL_PASSWORD
* .env.local（場所：nuxt 配下）
  * NUXT_PUBLIC_FB_API_KEY
  * NUXT_PUBLIC_FB_AUTH_DOMAIN
  * NUXT_PUBLIC_FB_PROJECT_ID
  * NUXT_PUBLIC_FB_STORAGE_BUCKET
  * NUXT_PUBLIC_FB_MESSAGING_SENDER_ID
  * NUXT_PUBLIC_FB_APP_ID
  * NUXT_PUBLIC_FB_MEASUREMENT_ID
  * NUXT_PUBLIC_BASE_URL
2. firebaseサービスアカウントの秘密鍵をダウンロードし、ファイル名を「serviceAccountKey.json」にして、spring-boot > src > main > resources 配下に配置

## UI設計
figma
https://www.figma.com/file/r882IZ0EDyNOmAsc9xah5A/%E3%83%A2%E3%83%AB%E3%82%AFUI%E8%A8%AD%E8%A8%88?type=design&node-id=5-2&mode=design&t=7jTxUseyYZ86HPTQ-0

## DB設計
```mermaid
erDiagram

users ||--o{ practices: "1:n"
prefectures ||--o{ practices: "1:n"
users ||--o{ molkky_activities: "1:n"
prefectures ||--o{ molkky_activities: "1:n"
practices ||--o{ participations: "1:n"
users ||--o{ participations: "1:n"
molkky_activities ||--o{ likes: "1:n"
users ||--o{ likes: "1:n"

users {
  binary(16) uuid
  varchar(40) nickname
  VARCHAR(2048) icon_url
  VARCHAR(255) email
  VARCHAR(100) firebase_uid
}

practices {
  binary(16) uuid
  varchar(100) title
  varchar(500) description
  integer max_participants
  tinyint(3) prefecture_id
  binary(16) organizer_id
  date held_at
  datetime created_at
}

molkky_activities {
  binary(16) uuid
  varchar(500) content
  binary(16) poster_id
  tinyint(3) prefecture_id
  datetime created_at
}

participations {
  binary(16) practice_id
  VARCHAR(100) user_firebase_uid
}

likes {
  binary(16) molkky_activity_id
  VARCHAR(100) user_firebase_uid
}

prefectures {
  tinyint(3) id
  varchar(255) name
  varchar(255) name_kana
}
```
