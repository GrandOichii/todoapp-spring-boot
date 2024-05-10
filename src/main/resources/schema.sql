create table if not exists Task (
    id int not null,
    title varchar(50) not null,
    text varchar(500) not null,
    completed boolean not null,
    
    version int not null
);