create table customers(
id varchar(255) not null primary key,
name varchar(255) not null
) engine InnoDB;

alter table customers
    add column primary_email varchar(150);