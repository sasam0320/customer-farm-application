-- insert role and users data

insert into role values(1, 'USER');
insert into role values(2, 'CREATOR');
insert into role values(3, 'ADMIN');


insert into users (`username`, `password`, `enabled`) values('mmarkovic', '$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG', '1');
insert into users (`username`, `password`, `enabled`) values('bmaric', '$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG', '1');
insert into users (`username`, `password`, `enabled`) values('sasam0320', '$2y$12$k4v0UJiVpqezTEOEtMiNRuyqM4139qASPa/dbgL2xXzolFNSvfvXG', '1');

insert into users_role values(1, 1);
insert into users_role values(2, 2);
insert into users_role values(3, 3);

-- insert account data

insert into account (id, `user`) values (1, 1);
insert into account (id, `user`) values (2, 1);
insert into account (id, `user`) values (3, 1);
insert into account (id, `user`) values (4, 2);
insert into account (id, `user`) values (5, 2);
insert into account (id, `user`) values (6, 3);


-- insert customer  data

insert into customer values('marko.markovic@gmail.com','Marko', 'Markovic', 1);
insert into customer values('william.petters@gmail.com','William', 'Petters', 2);
insert into customer values('branko.maric@gmail.com','Branko', 'Maric', 4);
insert into customer values('customer2021@gmail.com','Chris', 'Wild', 5);
insert into customer values('sasam0320@gmail.com','Sasa', 'Milenkovic', 6);


-- insert farm data

insert into farm values('Markos Walleye', 30500.25, 1);
insert into farm values('Williams Ranch', 44500.00, 2);
insert into farm values('Magic Walleye', 52500.5, 4);
insert into farm values('Green Haven', 60200.00, 5);
insert into farm values('Mountain Shadow Fields', 50000.00, 6);

