create table TESTDB.customer (
    customer_id bigint not null identity(1,1),
    customer_name varchar(100) not null,
    customer_email varchar(100)
);

alter table TESTDB.customer
    add constraint customer_pkey
        primary key (customer_id);