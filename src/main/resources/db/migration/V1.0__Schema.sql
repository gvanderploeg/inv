create table Product (
  id bigint(20) not null AUTO_INCREMENT,
  name varchar(255) not null,
  merchant_id bigint(20) not null,
  PRIMARY KEY (id)
);

create table Merchant (
  id bigint(20) not null AUTO_INCREMENT,
  username varchar(255) not null,
  password_hash varchar(255) not null,
  PRIMARY KEY (id)
);

create table Product_Stock (
  id bigint(20) not null AUTO_INCREMENT,
  name varchar(255) not null,
  product_id bigint(20) not null,
  nr_in_stock int not null,
  eligable_for_promotion boolean default false,
  projected_out_of_stock_date date,
  PRIMARY KEY (id)
);