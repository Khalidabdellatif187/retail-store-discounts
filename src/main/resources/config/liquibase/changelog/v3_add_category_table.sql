
CREATE TABLE store.category (
    id int8 PRIMARY KEY,
    name VARCHAR(250)
);


CREATE SEQUENCE category_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE store.category
     ALTER COLUMN id SET DEFAULT nextval('category_id_seq');


