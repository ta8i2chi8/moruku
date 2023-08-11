CREATE TABLE practices (
    uuid             binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
    title            varchar(100) NOT NULL,
    description      varchar(500) NOT NULL,
    max_participants integer,
    prefecture_id    tinyint(3) unsigned,
    organizer_id     binary(16) NOT NULL,
    held_on          date,
    created_at       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (uuid),
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (organizer_id) REFERENCES users(uuid) ON UPDATE CASCADE ON DELETE CASCADE
);