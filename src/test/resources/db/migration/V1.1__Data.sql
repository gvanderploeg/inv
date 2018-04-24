insert into Merchant (id, username, password_hash) values (1, 'handelaar1', '$2a$10$canjeIxsPKeCe4A7J2FK2e8PXWCa39peFCFdCXwlsQap2J/RkDAXa');
insert into Merchant (id, username, password_hash) values (2, 'handelaar2', '$2a$10$canjeIxsPKeCe4A7J2FK2e8PXWCa39peFCFdCXwlsQap2J/RkDAXa');

insert into Product (id, name, merchant_id) values (1, 'sokken', 1);
insert into Product (id, name, merchant_id) values (2, 'schoenen', 1);


insert into Product (id, name, merchant_id) values (3, 'sokken', 2);

insert into Product_Stock(id, name, product_id, nr_in_stock, eligable_for_promotion, projected_out_of_stock_date)
values(1, 'Pallet met veel afgedankte sokken', 1, 1000, false, '2018-10-23');
insert into Product_Stock(id, name, product_id, nr_in_stock, eligable_for_promotion, projected_out_of_stock_date)
values(2, 'Pallet met weinig afgedankte sokken', 1, 10, false, '2018-10-23');