create table users_table
(
id uuid,
email      varchar(255) not null,
login varchar(255) not null,
password varchar(255) not null,
primary key (id)
);

create table roles_table
(
id int not null,
name varchar(255) not null,
primary key (id)
);

create table users_roles_table
(
user_id uuid,
role_id int not null,
primary key (user_id, role_id),
foreign key (user_id) references users_table (id),
foreign key (role_id) references roles_table (id),
);

create table users_attempts_table
(
id uuid,
user_id uuid,
date_of_attempt datetime,
number_of_attempts int,
primary key (id),
foreign key (user_id) references users_table (id)
);