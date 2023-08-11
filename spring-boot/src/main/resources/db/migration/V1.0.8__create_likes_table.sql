CREATE TABLE likes (
    molkky_activity_id   binary(16),
    user_id              binary(16),
    PRIMARY KEY (molkky_activity_id, user_id)
);