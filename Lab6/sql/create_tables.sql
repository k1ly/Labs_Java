use `lab_servlet_db`;

create table `user_roles`
(
`id` int PRIMARY KEY auto_increment,
`role_name` nvarchar(20) NOT NULL unique
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `users`
(
`id` int PRIMARY KEY auto_increment,
`login` varchar(20) NOT NULL unique,
`password` blob NOT NULL,
`name` nvarchar(30) NOT NULL,
`user_role_id` int NOT NULL,
constraint `user_role_fk` foreign key (`user_role_id`) references `user_roles` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `items`
(
`id` int PRIMARY KEY auto_increment,
`name` nvarchar(30) NOT NULL,
`price` decimal(6) check(`price` >= 0),
`owner_id` int NOT NULL,
constraint `owner_fk` foreign key (`owner_id`) references `users` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;