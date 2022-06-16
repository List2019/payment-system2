DROP TABLE IF EXISTS users;
CREATE TABLE refresh_token (
                       id UUID,
                       username VARCHAR_IGNORECASE,
                       refresh_token VARCHAR_IGNORECASE,
                       revoked BOOLEAN,
                       date_created TIMESTAMP WITH TIME ZONE
);