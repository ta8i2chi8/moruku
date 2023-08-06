# モルク
## 環境構築手順
1. .envを作成
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
molkky_activities ||--o{ like: "1:n"
users ||--o{ like: "1:n"

users {
  bigint id
  varchar(40) nickname
  VARCHAR(255) icon_url
  VARCHAR(255) email
  VARCHAR(50) cognito_username
}

practices {
  bigint id
  varchar(100) title
  varchar(500) description
  integer max_participants
  integer prefecture_id
  integer organizer_id
  date event_date
  datetime created_at
}

molkky_activities {
  bigint id
  varchar(500) content
  integer poster_id
  integer prefecture_id
  datetime created_at
}

like {
  bigint molkky_activity_id
  bigint user_id
}

prefectures {
  tinyint id
  varchar(255) name
}
```
