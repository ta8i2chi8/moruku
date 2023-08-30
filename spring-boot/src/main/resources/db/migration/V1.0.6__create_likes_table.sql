CREATE TABLE likes (
    molkky_activity_id   binary(16),
    user_firebase_uid    varchar(100),
    PRIMARY KEY (molkky_activity_id, user_firebase_uid)
);