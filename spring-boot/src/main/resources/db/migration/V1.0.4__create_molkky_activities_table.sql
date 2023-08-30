CREATE TABLE molkky_activities (
    uuid             binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
    content          varchar(500) NOT NULL,
    poster_id        binary(16) NOT NULL,
    prefecture_id    tinyint(3) unsigned,
    created_at       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (uuid),
    FOREIGN KEY (poster_id) REFERENCES users(uuid) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(id) ON UPDATE CASCADE ON DELETE CASCADE
);