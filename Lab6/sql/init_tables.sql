use `lab_servlet_db`;

insert into `user_roles` (`role_name`) values
('USER_ROLE_GUEST'),
('USER_ROLE_CLIENT'),
('USER_ROLE_MANAGER'),
('USER_ROLE_ADMIN');

-- update `users` set `user_role_id`='4' where `id`='1' and `login`='admin1';