insert into users_table(id, email, login, password) values
('6b1879af-ccaf-46f7-a001-66657ce11943', 'ivanov@mail.ru', 'ivanov', '$2a$12$AR.iOEOLlnYCr.lLclt4SeUCAHpoJiLwPEbyUPojaB8VOCEA8qgmq'),
('054d2853-1b93-4f83-9540-1b6481193a1c', 'petrov@mail.ru', 'petrov', '$2a$12$5CXhbJoxRzC6UZOMtYxy6usTzAHxahgOKsv3EitWtqHhcjjo.IJSS'),
('69e63c0c-0741-4973-9760-c11bfcea4a1f', 'sidorov@mail.ru', 'sidorov', '$2a$12$ZwWCLHNHOpG1pvhzBQD7ZeghaD4sUFlb9JumuLqVeQQLOFlrSb/o2');

insert into roles_table(id, name) values
(1, 'ROLE_USER');

insert into users_roles_table(user_id, role_id) values
('6b1879af-ccaf-46f7-a001-66657ce11943',1),
('054d2853-1b93-4f83-9540-1b6481193a1c',1),
('69e63c0c-0741-4973-9760-c11bfcea4a1f',1);

insert into users_attempts_table(id, user_id, date_of_attempt, number_of_attempts) values
('8e9068a6-8ca7-11ec-a8a3-0242ac120002','6b1879af-ccaf-46f7-a001-66657ce11943','2022-02-13 20:07:09', 6),
('91fb3732-8ca7-11ec-a8a3-0242ac120002','054d2853-1b93-4f83-9540-1b6481193a1c','2022-02-14 20:07:09', 4),
('96fb64b4-8ca7-11ec-a8a3-0242ac120002','69e63c0c-0741-4973-9760-c11bfcea4a1f','2022-02-15 20:07:09', 3),
('8930e884-8d7b-11ec-b909-0242ac120002','69e63c0c-0741-4973-9760-c11bfcea4a1f','2022-02-16 20:07:09', 4);
