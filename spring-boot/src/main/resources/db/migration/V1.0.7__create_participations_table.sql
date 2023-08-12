CREATE TABLE participations (
    practice_id         binary(16),
    user_firebase_uid   varchar(100),
    PRIMARY KEY (practice_id, user_firebase_uid)
);