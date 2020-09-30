--Inserindo users

INSERT INTO users (login, password, admin) VALUES ('month', '$2a$10$Lt/sT0AU.SoNLjlC1thmw.dN8yrHVtokXSHxPJeeUaY6.bfXKEg9i', true);
INSERT INTO users (login, password, admin) VALUES ('junior', '$2a$10$Lt/sT0AU.SoNLjlC1thmw.dN8yrHVtokXSHxPJeeUaY6.bfXKEg9i', false);

--Inserindo clientes

INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 01');
INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 02');
INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 03');
INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 04');
INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 05');
INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 06');
INSERT INTO client (cpf, name) values ( '80332920321', 'Cliente 07');

-- Inserindo produtos

INSERT INTO product (description, price) VALUES ('Notebook', '3500');
INSERT INTO product (description, price) VALUES ('Cadeira Gamer', '750');
INSERT INTO product (description, price) VALUES ('Mesa Gamer', '750');
INSERT INTO product (description, price) VALUES ('Headphone', '100');
INSERT INTO product (description, price) VALUES ('Filtro de linha', '35');
INSERT INTO product (description, price) VALUES ('Monitor 21 polegadas', '400');
INSERT INTO product (description, price) VALUES ('Kit Mouse/Teclado sem fio', '100');