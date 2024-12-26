create database Movie;

use Movie;

-- tao bang User_System // luu tru mail va password nguoi dung
-- create table User_System(
-- Username				nvarchar(100) not null unique,
-- Usermail				nvarchar(100) not null unique,
-- UserPassword			nvarchar(100) not null,
-- UserRole				enum('Customer', 'Admin', 'Staff') Default 'Customer',
-- IDCustomer				int,
-- IDStaff					int
-- );	
create table User_System(
Username				nvarchar(100) not null unique,
UserPassword			nvarchar(100) not null,
UserRole				nvarchar(10) CHECK (UserRole IN ('Customer', 'Admin', 'Staff')) Default 'Customer',
IDCustomer				int,
IDStaff					int
);	
-- khoa ngoai cua bang User_System
alter table User_System add constraint fk_User_Customer foreign key (IDCustomer) references Customer(IDCustomer) on delete cascade on update cascade;
alter table User_System add constraint fl_User_Staff foreign key (IDStaff) references Staff(IDStaff) on delete cascade on update cascade;


-- tao bang customer
create table Customer(
IDCustomer				int auto_increment,
CustomerName			nvarchar(100) not null,
CustomerPhoneNumber		varchar(15) check (CustomerPhoneNumber regexp '^[0-9]{10,15}$'),
CustomerType			enum('Nguoi lon', 'Tre em') Default ('Nguoi lon'),
constraint pk_User	primary key(IDCustomer)
);

-- tao bang Staff
create table Staff (
IDStaff					int auto_increment,
Staff_Name				nvarchar(100) not null,
Staff_Mail				nvarchar(100) not null,
IDCinema				int not null,
constraint pk_Staff	primary key (IDStaff),
constraint fk_Staff_Cinema foreign key (IDCinema) references Cinema(IDCinema) on delete cascade
);



-- tao bang Movie 
create table Movie (
IDMovie					int auto_increment,
Title					nvarchar(255) not null,
Genre					nvarchar(255) not null,
Duration				int not null check (Duration > 0),
Director				nvarchar(255) not null,
release_date			date,
Moviedescrip			text not null,
create_at timestamp default CURRENT_TIMESTAMP,
update_at timestamp default current_timestamp on update current_timestamp,
constraint pk_Movie primary key (IDMovie)
);
 
 
 -- tao bang Cinema
create table Cinema (
IDCinema				int auto_increment,
CinemaName				nvarchar(255) not null,
Adress					nvarchar(255) not null,
PhoneNumber				varchar(15) not null check (PhoneNumber regexp '^[0-9]{10,15}$'),
constraint pk_Cinema primary key (IDCinema)
);

-- tao bang Room
create table Room (
IDRoom				int auto_increment,
IDCinema			int not null,	-- Trung gian cho Movie - Cinema
RoomName			nvarchar(100) not null,
capacity			int not null,	-- Suc chua cua phong
Status 				enum('Con trong', 'Het cho') default 'Con trong',
create_at timestamp default CURRENT_TIMESTAMP,
update_at timestamp default current_timestamp on update current_timestamp,
constraint pk_Room primary key (IDRoom),
constraint fk_Cinema_Room foreign key (IDCinema) references Cinema(IDCinema) on delete cascade on update cascade
);

-- tao bang Schedule (Lich phim)
create table Schedule (
IDSchedule				int AUTO_INCREMENT,
IDMovie					int not null,
IDRoom					int not null,
Showdate				date not null,
Showtime				time not null,
Price					Decimal(10,2) not null,
Status 					enum('Dang Chieu', 'Ngung Chieu') default 'Dang Chieu',
create_at timestamp default CURRENT_TIMESTAMP,
update_at timestamp default current_timestamp on update current_timestamp,
constraint pk_Schedule primary key (IDSchedule),
constraint fk_Schedule_Room foreign key(IDRoom) references Room(IDRoom) on delete cascade on update cascade,
constraint fk_Schedule_Movie foreign key(IDMovie) references Movie(IDMovie) on delete cascade on update cascade
);




create table Ticket (
IDTicket			int auto_increment,
IDSchedule			int not null,
IDSeat				int not null,
TicketStatus		enum('Da Dat', 'Bi Huy', 'Chua Thanh Toan') default 'Chua Thanh Toan',
Price				decimal(10, 2) not null,
IDCustomer 			int not null,
IDStaff 			int not null,
constraint pk_Ticket primary key (IDTicket),
constraint fk_Schedule_Ticket foreign key (IDSchedule) references Schedule(IDSchedule) on delete cascade on update cascade,
constraint fk_Seat_Ticket foreign key (IDSeat) references Seat(IDSeat) on delete cascade on update cascade,
constraint fk_Custom_Ticket foreign key (IDCustomer) references Customer(IDCustomer) on delete cascade,
constraint fk_Staff_Ticket foreign key (IDStaff) references Staff(IDStaff) on delete cascade on update cascade 
);



create table Seat (
IDSeat     		 int auto_increment,
IDRoom     		 int not null,
SeatRow    		 char(1) not null, -- Hàng ghế (A, B, C, ...)
SeatNumber 		 int not null,     -- Số ghế trong hàng
Status      	 enum('Available', 'Booked', 'Broken') default 'Available',
create_at  		 timestamp default CURRENT_TIMESTAMP,
update_at 		 timestamp default current_timestamp on update current_timestamp,
constraint pk_Seat primary key (IDSeat),
constraint fk_Room_Seat foreign key (IDRoom) references Room(IDRoom) on delete cascade
);

insert into User_System(Username, Usermail, UserPassword, UserRole) values ('Bach', 'bachnd2006@outlook.com', 'Bachnguyen54', 'Staff'), ('Duy', 'Duy2006@gmail.com', 'Duy123432', 'Staff');
select * from User_System;

insert into Staff(Staff_Name,Staff_Mail, IDCinema ) values ('Bach', 'bachnd2006@outlook.com', '1'), ('Duy', 'Duy2006@gmail.com', '1'), ('Tuat', 'Tuat06.24ai@vku.udn.vn', '1');
select * from Staff;

select * from Movie;
insert into Movie(Title, Genre, Duration, Director, release_date, Moviedescrip) values ('Avenger: Endgame', 'Sci-fi', '181', 'Anthony Russo', '2019-4-26', 'Sau cu bung tay cua thanos...');

insert into Cinema(IDCinema, CinemaName, Adress, PhoneNumber) values ('1', 'CGV', 'Vincom Ngo Quyen', '0123456789');
