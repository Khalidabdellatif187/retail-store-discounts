create table store.bill(
id int8 PRIMARY KEY,
user_id int8,
product_ids VARCHAR(500),
total_amount float8,
net_amount float8,
FOREIGN KEY (user_id) REFERENCES store.users(id)
);






CREATE SEQUENCE bill_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE store.bill
    ALTER COLUMN id SET DEFAULT nextval('bill_id_seq');