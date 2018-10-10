DROP table if EXISTS baby_products;
DROP table if EXISTS baby_products_details;

create table IF NOT EXISTS baby_products
(ID MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
company_name VARCHAR(50),
product_name VARCHAR(50),
type VARCHAR(50));

TRUNCATE table baby_products;

create table IF NOT EXISTS baby_products_details
(ID MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
  product_id MEDIUMINT,
 product_detail VARCHAR(50));


insert into baby_products (ID, company_name, product_name, type) VALUES
  (1, 'gerber', 'baby formula', 'food');

insert into baby_products (ID, company_name, product_name) VALUES
  (2, 'tum tum', 'baby formula');

insert into baby_products_details (product_id, product_detail) VALUES
  ('1', 'This is baby formula.');