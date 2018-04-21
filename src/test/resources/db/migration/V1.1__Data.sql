insert into Merchant (id, username, password_hash) values (1, 'handelaar1', '$2a$10$canjeIxsPKeCe4A7J2FK2e8PXWCa39peFCFdCXwlsQap2J/RkDAXa');
insert into Merchant (id, username, password_hash) values (2, 'handelaar2', '$2a$10$canjeIxsPKeCe4A7J2FK2e8PXWCa39peFCFdCXwlsQap2J/RkDAXa');

insert into Product (id, name, merchant_id) values (1, 'sokken', 1);
insert into Product (id, name, merchant_id) values (2, 'schoenen', 1);


insert into Product (id, name, merchant_id) values (3, 'sokken', 2);

