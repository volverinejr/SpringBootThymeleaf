/* populando clientes */
insert into clientes (nome, apelido, email, criado_em, foto) values ('Doralynne Sparry', 'Doralynne', 'dsparry0@sakura.ne.jp', '2019-04-08', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Edie Schrinel', 'Edie', 'eschrinel1@bigcartel.com', '2019-02-14', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Clayborn Gorrick', 'Clayborn', 'cgorrick2@is.gd', '2020-03-29', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Tisha Boshier', 'Tisha', 'tboshier3@geocities.jp', '2019-03-27', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Gwenny Makepeace', 'Gwenny', 'gmakepeace4@nature.com', '2020-05-31', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Elinore Cornier', 'Elinore', 'ecornier5@sakura.ne.jp', '2019-10-06', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Mano Ruckert', 'Mano', 'mruckert6@ifeng.com', '2019-05-09', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Karlis Ollerhead', 'Karlis', 'kollerhead7@rambler.ru', '2019-12-26', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Toiboid Brandon', 'Toiboid', 'tbrandon8@networkadvertising.org', '2019-08-16', '');
insert into clientes (nome, apelido, email, criado_em, foto) values ('Terrijo Fussen', 'Terrijo', 'tfussen9@businesswire.com', '2019-10-17', '');

/* populando produtos */
insert into produtos (nome, preco, criado_em) values ('Wine - Kwv Chenin Blanc South', '74.59', '2019-09-13');
insert into produtos (nome, preco, criado_em) values ('Beer - Heinekin', '45.77', '2019-02-08');
insert into produtos (nome, preco, criado_em) values ('Pan Grease', '52.20', '2020-04-28');
insert into produtos (nome, preco, criado_em) values ('Olives - Green, Pitted', '92.73', '2020-05-23');
insert into produtos (nome, preco, criado_em) values ('Chambord Royal', '55.27', '2020-03-27');
insert into produtos (nome, preco, criado_em) values ('Guava', '36.60', '2019-07-31');
insert into produtos (nome, preco, criado_em) values ('Mushrooms - Black, Dried', '87.59', '2020-01-19');
insert into produtos (nome, preco, criado_em) values ('Wine - Soave Folonari', '76.26', '2019-08-11');
insert into produtos (nome, preco, criado_em) values ('Lamb - Whole, Fresh', '70.59', '2019-08-10');
insert into produtos (nome, preco, criado_em) values ('Oil - Peanut', '40.52', '2019-10-29');
insert into produtos (nome, preco, criado_em) values ('Rice - Jasmine Sented', '80.32', '2019-03-08');
insert into produtos (nome, preco, criado_em) values ('Fish - Base, Bouillion', '78.78', '2020-04-08');
insert into produtos (nome, preco, criado_em) values ('Buffalo - Tenderloin', '54.61', '2019-09-15');
insert into produtos (nome, preco, criado_em) values ('Bulgar', '69.82', '2020-04-16');
insert into produtos (nome, preco, criado_em) values ('Arizona - Green Tea', '98.84', '2019-06-29');
insert into produtos (nome, preco, criado_em) values ('Bread - Triangle White', '70.44', '2019-07-24');
insert into produtos (nome, preco, criado_em) values ('Milk - 2% 250 Ml', '31.74', '2019-06-22');
insert into produtos (nome, preco, criado_em) values ('Bread - Pumpernickle, Rounds', '24.15', '2019-09-12');
insert into produtos (nome, preco, criado_em) values ('Bread - Pain Au Liat X12', '72.96', '2019-06-03');
insert into produtos (nome, preco, criado_em) values ('Lamb - Loin, Trimmed, Boneless', '46.06', '2019-12-05');



/* Populando as faturas */
INSERT INTO faturas ( descricao, observacao, criado_em, cliente_id ) VALUES ('1º Fatura', 'Primeira Fatura pra testar', '2019-09-25', 1);
INSERT INTO faturas_items ( fatura_id, produto_id, quantidade ) VALUES (1, 1, 6);
INSERT INTO faturas_items ( fatura_id, produto_id, quantidade ) VALUES (1, 3, 7);


INSERT INTO faturas ( descricao, observacao, criado_em, cliente_id ) VALUES ('2º Fatura cliente 2', 'Fatura de teste do cliente 2', '2019-09-13', 2);
INSERT INTO faturas_items ( fatura_id, produto_id, quantidade ) VALUES (2, 11, 2);
INSERT INTO faturas_items ( fatura_id, produto_id, quantidade ) VALUES (2, 13, 8);







/* Populando cliente */
INSERT INTO users (username, password, enabled) values ('Miro', '$2a$10$gk.EtwV8HuqgvIqkSjOngOsN6utSYg1i.zNFEAeCli1jTCOe8IZ8a', 1);
INSERT INTO users (username, password, enabled) values ('Admin', '$2a$10$PmCDNH931fDM0RJqRWVgmeoNrFVc.iujQ7x8/gyys1dSBi6GxqmIW', 1);


insert into authorities (user_id, authority) values (1, 'ROLE_USER');
insert into authorities (user_id, authority) values (2, 'ROLE_ADMIN');
insert into authorities (user_id, authority) values (2, 'ROLE_USER');

