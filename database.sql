create table customers(
id varchar(255) not null primary key,
name varchar(255) not null
) engine InnoDB;

alter table customers
    add column primary_email varchar(150);

create table categories(
    id int not null auto_increment primary key,
    name varchar(100) not null,
    description varchar(500)
)engine InnoDB;


alter table customers
    add column age tinyint;

alter table customers
    add column married boolean;

alter table customers
    add column type varchar(50);

alter table categories
    add column created_at timestamp;

alter table categories
    add column updated_at timestamp;