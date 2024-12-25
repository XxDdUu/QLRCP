create database Movie;

use Movie;

create table User_System(
Username		nvarchar(100) not null,
Usermail		nvarchar(100) not null,
UserPassword	nvarchar(100) not null,
UserRole		varchar(8) CHECK (UserRole IN ('Customer', 'Admin')) Default 'Customer'
);

create table Customer(
IDCustomer		int IDENTITY(1,1),
CustomerName	nvarchar(100) not null,
CustomerPhoneNumber		nvarchar(100),
CustomerType		nvarchar(14) CHECK (CustomerType IN ('Nguoi lon', 'Tre em')) Default ('Nguoi lon')
constraint pk_User	primary key(IDCustomer)
);


create table Staff (
ID_Staff		int auto_increment,
Staff_Name		nvarchar(100) not null,
Staff_Mail		nvarchar(100) not null,
IDCinema		int not null,
constraint pk_Staff	primary key (ID_Staff)
);

create table Movie (
IDMovie			int auto_increment,
Title			nvarchar(255) not null,
Genre			nvarchar(100) not null,
Duration		int not null,
Director		nvarchar(255) not null,
release_date	date ,
Moviedescrip	text not null,
main_cast		text not null,
create_at timestamp default CURRENT_TIMESTAMP,
update_at timestamp default current_timestamp on update current_timestamp,
constraint pk_Movie primary key (IDMovie)
);

create table Cinema (
IDCinema		int auto_increment,
CinemaName		nvarchar(255) not null,
Adress			nvarchar(255) not null,
PhoneNumber		int check (PhoneNumber < 11),
constraint pk_Cinema primary key (IDCinema)
);

create table Schedule (
IDSchedule		int AUTO_INCREMENT,
IDMovie			int not null,
IDRoom			int not null,
Showdate		date not null,
Showtime		time not null,
Price			Decimal(10,2) not null,
status ENUM('Dang Chieu', 'Ngung Chieu') default 'Dang Chieu',
create_at timestamp default CURRENT_TIMESTAMP,
update_at timestamp default current_timestamp on update current_timestamp,
constraint pk_Schedule primary key (IDSchedule)
);
alter table Schedule add constraint fk_Schedule_Movie foreign key (IDMovie) references Movie(IDMovie) on delete cascade;

create table Room (
IDRoom			int auto_increment,
IDCinema		int not null,
RoomName		nvarchar(100) not null,
capacity		int not null,	-- Suc chua cua phong
Status enum('Con trong', 'Het cho')	default 'Con trong',
create_at timestamp default CURRENT_TIMESTAMP,
update_at timestamp default current_timestamp on update current_timestamp,
constraint pk_Room primary key (IDRoom)
);

create table Ticket (
IDTicket		int auto_increment,
IDSchedule		int not null,
IDSeat			int not null,
TicketStatus	enum('Da Dat', 'Bi Huy', 'Chua Thanh Toan') default 'Chua Thanh Toan',
Price			decimal(10, 2) not null,
constraint pk_Ticket primary key (IDTicket),
constraint fk_Schedule_Ticket foreign key (IDSchedule) references Schedule(IDSchedule) on delete cascade on update cascade,
constraint fk_Seat_Ticket foreign key (IDSeat) references Seat(IDSeat) on delete cascade on update cascade
);

create table Seat (
IDSeat      int auto_increment,
IDRoom      int not null,
SeatRow     char(1) not null, -- Hàng ghế (A, B, C, ...)
SeatNumber  int not null,     -- Số ghế trong hàng
Status      enum('Available', 'Booked', 'Broken') default 'Available',
create_at   timestamp default CURRENT_TIMESTAMP,
update_at   timestamp default current_timestamp on update current_timestamp,
constraint pk_Seat primary key (IDSeat),
constraint fk_Room_Seat foreign key (IDRoom) references Room(IDRoom) on delete cascade
);

alter table User add column Role enum('Customer', 'Admin') default 'Customer';

alter table Ticket add column ID_User int not null;
alter table Ticket add constraint fk_User_Ticket foreign key (ID_User) references User(ID_User) on delete cascade;

alter table Room add constraint fk_Cinema_Room foreign key (IDCinema) references Cinema(IDCinema) on delete cascade on update cascade;
alter table Schedule add constraint fk_Schedule_Room foreign key(IDRoom) references Room(IDRoom) on delete cascade on update cascade;

alter table Cinema modify PhoneNumber varchar(15) not null check (PhoneNumber regexp '^[0-9]{10,15}$');

alter table Staff add constraint fk_Staff_Cinema foreign key (IDCinema) references Cinema(IDCinema) on delete cascade;

alter table Ticket add column ID_Staff int;
alter table Ticket add constraint fk_Staff_Ticket foreign key (ID_Staff) references Staff(ID_Staff) on delete set null;

alter table Schedule add column ID_Staff int;
alter table Schedule add constraint fk_Schedule_Staff foreign key (ID_Staff) references Staff(ID_STAFF) on delete set null;

