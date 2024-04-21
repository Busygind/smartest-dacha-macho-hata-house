CREATE TABLE IF NOT EXISTS logs (
    user_login  String,
    log_time    TIMESTAMP,
    house_id    UUID,
    room_id     UUID,
    device_id   UUID,
    new_state   BOOL,
    message     String
) engine = MergeTree() ORDER BY user_login;