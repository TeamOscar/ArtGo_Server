create database sell;
use sell;
create table user
(
	id varchar(40) primary key,
	name varchar(32) not null,
	address varchar(100) not null,
	sign varchar(255) not null,
	email varchar(255) not null unique,
	password varchar(255) not null,
	phone varchar(255) not null
);

create table sell
(
	id varchar(40) primary key,
	path varchar(256) null,
	userId varchar(40) not null,
	time varchar(40) not null,
	price float,
	description varchar(255) not null,
	address varchar(255) not null
);

create table buy
(
	id varchar(40) primary key,
	sellOrderId varchar(256) not null,
	sellId varchar(256) not null,
	buyId varchar(40) not null,
	receiveFalg int,
	sendFalg int
);