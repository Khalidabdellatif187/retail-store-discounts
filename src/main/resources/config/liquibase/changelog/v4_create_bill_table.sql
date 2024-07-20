create table store.bill(
id int8 PRIMARY KEY,
user_id int8,
total_amount float8,
net_amount float8,
FOREIGN KEY (user_id) REFERENCES store.users(id)
);


create table store.bill_product(
product_id int8,
bill_id int8,
FOREIGN KEY (product_id) REFERENCES store.product(id),
FOREIGN KEY (bill_id) REFERENCES store.bill(id)
);



CREATE SEQUENCE bill_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE store.bill
    ALTER COLUMN id SET DEFAULT nextval('bill_id_seq');