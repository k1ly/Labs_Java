use `lab_jdbc_db`;

create table `users`
(
`id` int PRIMARY KEY auto_increment,
`name` nvarchar(50) NOT NULL,
`birth_date` timestamp NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `letters`
(
`id` int PRIMARY KEY auto_increment,
`sender_id` int NOT NULL,
`receiver_id` int NOT NULL,
`text` nvarchar(100) NOT NULL,
`theme` nvarchar(50) NOT NULL,
`send_date` timestamp NOT NULL,
constraint `letter_sender_fk` foreign key (`sender_id`) references `users` (`id`) on delete cascade on update cascade,
constraint `letter_receiver_fk` foreign key (`receiver_id`) references `users` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;