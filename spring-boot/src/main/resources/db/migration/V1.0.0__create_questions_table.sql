CREATE TABLE questions (
    id               bigint PRIMARY KEY,
    content          varchar(1000) NOT NULL,
    image_content    varchar(1000),
    attention_count  integer NOT NULL DEFAULT 0,
    created_at       datetime
);