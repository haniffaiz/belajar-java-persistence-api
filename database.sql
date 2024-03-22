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


create table images(
    id int not null auto_increment primary key,
    name varchar(100) not null,
    description text,
    image blob
)engine InnoDB;

create table members(
    id int not null auto_increment primary key,
    email varchar(150) not null,
    title varchar(100),
    first_name varchar(100)not null,
    middle_name varchar(100),
    last_name varchar(100)
)engine InnoDB;

create table departments(
    company_id varchar(100) not null,
    department_id varchar(100) not null,
    name varchar(150) not null,
    primary key (company_id, department_id)
)engine InnoDB;

create table hobbies(
    id int not null auto_increment primary key,
    member_id int not null,
    name varchar(150) not null,
    foreign key fk_members_hobbies (member_id)
    references members (id)
)engine InnoDB;

create table skills(
    id int not null auto_increment primary key,
    member_id int not null,
    name varchar(100) not null,
    value int not null,
    foreign key fk_members_skills (member_id)
    references members (id),
    constraint skills_unique unique (member_id, name)
)engine InnoDB;

create table credentials(
    id varchar(100) not null primary key,
    email varchar(150) not null,
    password varchar(150) not null
)engine InnoDB;

create table users(
    id varchar(100) not null primary key,
    name varchar(150) not null
)engine InnoDB;

create table wallet(
    id int not null auto_increment primary key,
    user_id varchar(100) not null,
    balance bigint not null,
    foreign key fk_users_wallet (user_id) references users(id)
)engine InnoDB;

create table brands(
    id varchar(100) not null primary key,
    name varchar(150) not null,
    description varchar(500)
)engine InnoDB;

create table products(
    id varchar(100) not null primary key,
    brand_id varchar(100) not null,
    name varchar(100) not null,
    price bigint not null,
    description varchar(500),
    foreign key fk_brands_products (brand_id) references brands(id)
)engine InnoDB;