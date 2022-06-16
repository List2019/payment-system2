CREATE TABLE bank_card (
                           id INTEGER,
                           card_number VARCHAR_IGNORECASE,
                           balance NUMERIC,
                           blocked BOOLEAN
);
CREATE TABLE users (
                       id INTEGER,
                       name VARCHAR_IGNORECASE,
                       last_name VARCHAR_IGNORECASE,
                       card_id INTEGER,
                       password VARCHAR_IGNORECASE,
                       login VARCHAR_IGNORECASE,
                       role VARCHAR_IGNORECASE,
                       email VARCHAR_IGNORECASE
);