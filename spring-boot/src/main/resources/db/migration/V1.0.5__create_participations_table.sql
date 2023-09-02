CREATE TABLE participations (
    practice_id         binary(16),
    user_firebase_uid   varchar(100),
    PRIMARY KEY (practice_id, user_firebase_uid),
    FOREIGN KEY (practice_id) REFERENCES practices(uuid) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_firebase_uid) REFERENCES users(firebase_uid) ON UPDATE CASCADE ON DELETE CASCADE
);