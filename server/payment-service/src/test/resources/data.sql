INSERT INTO users (id, name, last_name, password, login, role, email) VALUES ('baffb5b0-f258-48f8-9d8e-7dbae34288fb',
                                                                              'Aleksandr', 'Matveev', 'admin', 'admin', 'admin', 'test@mail.com');

INSERT INTO account (account_number, user_id, balance, blocked) VALUES ('346598060226129', 'baffb5b0-f258-48f8-9d8e-7dbae34288fb', 100, 'FALSE');

INSERT INTO users (id, name, last_name, password, login, role, email) VALUES ('18cba0ef-7d6e-4441-8796-26321cd5dfb5',
                                                                              'Petr', 'Petrov', 'admin', 'petro', 'user', 'petr_pertrov@mail.com');

INSERT INTO account (account_number, user_id, balance, blocked) VALUES ('5285787197053207', '18cba0ef-7d6e-4441-8796-26321cd5dfb5', 10, 'FALSE');

INSERT INTO account (account_number, user_id, balance, blocked) VALUES ('5456974023407780', '18cba0ef-7d6e-4441-8796-26321cd5dfb5', 0, 'FALSE');

INSERT INTO account (account_number, user_id, balance, blocked) VALUES ('5277293804362073', '18cba0ef-7d6e-4441-8796-26321cd5dfb5', 0, 'TRUE');
