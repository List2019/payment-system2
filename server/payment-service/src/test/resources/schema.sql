CREATE TABLE IF NOT EXISTS users (
                       id UUID NOT NULL,
                       name VARCHAR NOT NULL,
                       last_name VARCHAR NOT NULL,
                       password VARCHAR NOT NULL,
                       login VARCHAR NOT NULL,
                       role VARCHAR NOT NULL,
                       email VARCHAR,
                       PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS account (
                         account_number VARCHAR NOT NULL,
                         user_id UUID NOT NULL,
                         balance NUMERIC NOT NULL,
                         blocked BOOLEAN,
                         PRIMARY KEY(account_number),
                         CONSTRAINT fk_user
                            FOREIGN KEY(user_id)
                                REFERENCES users(id)
);
