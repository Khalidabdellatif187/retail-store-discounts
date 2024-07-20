create table store.product(
id int8 PRIMARY KEY,
name VARCHAR(250),
price float8,
category_id int8,
FOREIGN KEY (category_id) REFERENCES store.category(id)
);


CREATE SEQUENCE product_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE store.product
    ALTER COLUMN id SET DEFAULT nextval('product_id_seq');