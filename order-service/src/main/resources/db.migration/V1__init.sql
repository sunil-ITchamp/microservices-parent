create sequence order_id_seq start with 1 increment by 50;

create table order
(
id bigint DEFAULT nextval('order_id_seq') not null,
name varchar,
primary key (id)
);