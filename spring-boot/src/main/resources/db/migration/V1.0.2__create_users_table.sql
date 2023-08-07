CREATE TABLE users (
    id               bigint PRIMARY KEY,
    nickname         varchar(40) NOT NULL,
    icon_url         varchar(2048),
    email            varchar(255) NOT NULL,
    firebase_uid     varchar(100) NOT NULL
);