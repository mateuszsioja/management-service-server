/* ROLES */
insert into role(id, role_type) values (1001, 'ROLE_USER');
insert into role(id, role_type) values (1002, 'ROLE_ADMIN');

/* USERS*/
insert into person(id, username, password, first_name, last_name, email) values(1001, 'user', '$2a$06$5Qv2gj8RMjJnB0w.755BC.dX5i.PTZ4ZUh9XerWt2qYqT9knvmeYS', 'Grzegorz', 'Waski', 'user@gmail.com');
insert into person(id, username, password, first_name, last_name, email) values(1002, 'admin', '$2a$06$Ec3ffedFAqAXGBKeQRRokeWqdJwWFp3ZTEo3ndOh/VTEYTni9gb1i', 'Maciej', 'Wazny', 'admin@gmail.com');

insert into user_role(user_id, role_id) values(1001, 1001);
insert into user_role(user_id, role_id) values(1002, 1001);
insert into user_role(user_id, role_id) values(1002, 1002);