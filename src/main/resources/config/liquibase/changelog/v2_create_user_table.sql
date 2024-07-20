create table store.users(
id int8 PRIMARY KEY,
name VARCHAR(50),
join_date DATE,
user_type VARCHAR(50)
);


CREATE SEQUENCE user_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE store.users
    ALTER COLUMN id SET DEFAULT nextval('user_id_seq');