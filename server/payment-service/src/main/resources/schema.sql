CREATE TABLE users (
                       id UUID NOT NULL PRIMARY KEY,
                       name VARCHAR_IGNORECASE NOT NULL,
                       last_name VARCHAR_IGNORECASE NOT NULL,
                       password VARCHAR_IGNORECASE NOT NULL,
                       login VARCHAR_IGNORECASE NOT NULL,
                       role VARCHAR_IGNORECASE NOT NULL,
                       email VARCHAR_IGNORECASE
);
CREATE TABLE account (
                         account_number VARCHAR_IGNORECASE NOT NULL PRIMARY KEY,
                         user_id UUID NOT NULL,
                         balance NUMERIC NOT NULL,
                         blocked BOOLEAN
);
