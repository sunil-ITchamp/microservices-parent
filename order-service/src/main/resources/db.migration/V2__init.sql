create sequence test1_id_seq start with 1 increment by 50;

create table test1
(
id bigint DEFAULT nextval('test1_id_seq') not null,
name varchar,
primary key (id)
);