
---------------- ADD USER TEST DATA ---------

INSERT INTO store.users (name, join_date, user_type)
VALUES ('Khalid', NULL, 'EMPLOYEE');

INSERT INTO store.users (name, join_date, user_type)
VALUES ('Mohammed', NULL, 'AFFILIATE');

INSERT INTO store.users (name, join_date, user_type)
VALUES ('Ali', '2024-07-18', 'CUSTOMER');

INSERT INTO store.users (name, join_date, user_type)
VALUES ('Mostafa', '2020-07-18', 'CUSTOMER');


----------------ADD CATEGORY TEST DATA ---------

INSERT INTO store.category (name)
VALUES ('groceries');

INSERT INTO store.category (name)
VALUES ('electronics');


----------------ADD PRODUCT TEST DATA ---------
INSERT INTO store.product (name, price, category_id)
VALUES ('APPLE', 14, (SELECT id FROM store.category WHERE name = 'groceries'));

INSERT INTO store.product (name, price, category_id)
VALUES ('MEAT', 50, (SELECT id FROM store.category WHERE name = 'groceries'));

INSERT INTO store.product (name, price, category_id)
VALUES ('IPHONE14', 1500, (SELECT id FROM store.category WHERE name = 'electronics'));

INSERT INTO store.product (name, price, category_id)
VALUES ('BATTERY', 20, (SELECT id FROM store.category WHERE name = 'electronics'));

