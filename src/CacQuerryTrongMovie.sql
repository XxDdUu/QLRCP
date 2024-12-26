use Movie;

-- khoa ngoai cua table User_System
alter table User_System add constraint fk_User_Customer foreign key (IDCustomer) references Customer(IDCustomer) on delete cascade on update cascade;
alter table User_System add constraint fl_User_Staff foreign key (IDStaff) references Staff(IDStaff) on delete cascade on update cascade;

-- khoa ngoai cua table Staff
alter table Staff add constraint fk_Staff_Cinema foreign key (IDCinema) references Cinema(IDCinema) on delete cascade on update cascade;

-- khoa ngoai cua table Room
alter table Room add constraint fk_Cinema_Room foreign key (IDCinema) references Cinema(IDCinema) on delete cascade on update cascade;

-- khoa ngoai cua table Schedule
alter table Schedule add constraint fk_Schedule_Room foreign key(IDRoom) references Room(IDRoom) on delete cascade on update cascade;
alter table Schedule add constraint fk_Schedule_Movie foreign key(IDMovie) references Movie(IDMovie) on delete cascade on update cascade;

-- khoa ngoai cua table Ticket
alter table Ticket add constraint fk_Schedule_Ticket foreign key (IDSchedule) references Schedule(IDSchedule) on delete cascade on update cascade;
alter table Ticket add constraint fk_Seat_Ticket foreign key (IDSeat) references Seat(IDSeat) on delete cascade on update cascade;
alter table Ticket add constraint fk_Custom_Ticket foreign key (IDCustomer) references Customer(IDCustomer) on delete cascade;
alter table Ticket add constraint fk_Staff_Ticket foreign key (IDStaff) references Staff(IDStaff) on delete cascade on update cascade;

-- khoa ngoai cua table Seat
alter table Seat add constraint fk_Room_Seat foreign key (IDRoom) references Room(IDRoom) on delete cascade on update cascade;

-- insert du lieu mau cho table User_System
insert into User_System(Username, UserPassword, UserRole) values ('Bach', 'Bachnguyen54', 'Staff'),
 ('Duy','Duy123432', 'Staff'), ('Thong','Thong098765', 'Customer'), ('Tuat','Tuat999999', 'Admin');
 select * from User_System;

-- insert du lieu mau cho table Customer
insert into Customer(CustomerName, CustomerPhoneNumber, CustomerType) values ('Duong', '0905040302', 'Nguoi Lon'), ('Hai', '0847576756', 'Tre em');
 select * from Customer;
 
 -- insert du lieu mau cho table Staff
insert into Staff(Staff_Name, Staff_Mail, IDCinema) values ('Duy', 'Duy123432@gmail.com', '400' ), ('Khanh','Khanh98@gmail.com','400');
select * from Staff;

-- insert du lieu mau cho table Cinema
insert into Cinema(IDCinema, CinemaName, Adress, PhoneNumber) values ('400', 'CGV', 'Vincom Bach Dang', '0111222333');

