CREATE TABLE participations (
    practice_id   binary(16),
    user_id       binary(16),
    PRIMARY KEY (practice_id, user_id)
);