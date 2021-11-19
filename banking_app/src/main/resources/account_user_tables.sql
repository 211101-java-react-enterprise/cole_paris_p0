--------TABLE CREATION--------------------------------
create table users(
	id varchar not null unique,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	email varchar(25) not null unique,
	username varchar(25) not null unique,
	password varchar(25) not null,
	register_time varchar(25) not null,
	
	constraint users_pk
	primary key (id)
	);

create table accounts(
	account_id varchar not null unique,
	--name varchar(25) not null,
	balance numeric(9, 2) not null,
	id varchar not null unique,
	
	constraint accounts_pk
	primary key(account_id),
	foreign key (id) references users(id)
);

-------PRINT TABLES
select * from accounts a;
select * from users u;


---------DROP ACCOUNTS-----------------------
drop table accounts

drop table users




