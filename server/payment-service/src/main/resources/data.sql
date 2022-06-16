INSERT INTO bank_card (id, card_number, balance, blocked) VALUES (2342, '4000001234567899', 100, 0);

INSERT INTO users (id, name, last_name, card_id, password, login, role, email) VALUES (1, 'Aleksandr', 'Matveev', 2342, 'admin', 'admin', 'admin', 'test@mail.com');
