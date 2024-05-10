create table if not exists Task (
    id int not null,
    title varchar(50) not null,
    text varchar(500) not null,
    completed boolean not null,
    
    primary key (id)
);

create table if not exists Client (
    id int not null,
    username varchar(50) not null,
    password_hash varchar(60) not null,

    primary key (id)

);