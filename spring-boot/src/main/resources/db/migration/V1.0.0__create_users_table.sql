CREATE TABLE users (
    uuid             binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
    nickname         varchar(40) NOT NULL,
    icon_url         varchar(2048),
    email            varchar(255) NOT NULL UNIQUE,
    firebase_uid     varchar(100) NOT NULL UNIQUE,
    PRIMARY KEY (uuid)
);