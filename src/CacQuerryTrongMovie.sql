use Movie;


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

-- insert du lieu mau cho table Customer
insert into Customer(CustomerName, CustomerPhoneNumber, CustomerType) values ('Duong', '0905040302', 'Nguoi Lon'), ('Hai', '0847576756', 'Tre em');
 select * from Customer;
 
 -- insert du lieu mau cho table Staff
insert into Staff(Staff_Name, Staff_Mail) values ('Duy', 'Duy123432@gmail.com' ), ('Khanh','Khanh98@gmail.com');

--insert du leu cho movie
INSERT INTO Movie (Title, Genre, Duration, Director,release_date, Moviedescrip )VALUES 
	('Arcane', N'HĐ', 171, 'Pascal Charrue và Arnaud Delord', '2021/11/06',N' Arcane là loạt phim hoạt hình lấy
bối cảnh vũ trụ League of Legends, kể về 
cuộc xung đột giữa hai thành phố Piltover 
và Zaun, cùng hành trình bi kịch của hai chị 
em Vi và Jinx. Bộ phim khai thác sâu về công 
nghệ Hextech, mối quan hệ gia đình, và sự 
đối đầu giữa lý tưởng và tham vọng.
'),
	( 'Avenger' , 'KHVT', 181 ,'Anthony Russo, Joe Russo', '2019/04/26', N'Sau cú búng tay của Thanos,các 
Avengers còn lại phải tập hợp để đảo ngược 
thiệt hại và cứu vãn vũ trụ' ),
	( N'Lật mặt 7', 'CK', 138 , N'Lý Hải', '2024/04/26', N' Phim kể về cuộc sống của bà Hai, 
một bà lão 73 tuổi sống tại làng KLong
 KLanh ở huyện Lạc Dương, Lâm Đồng. Bà 
Hai đã tự mình nuôi nấng năm người con 
khôn lớn sau khi chồng qua đời sớm. Mỗi 
người con khi trưởng thành đều có cuộc
sống riêng và thậm chí đi làm ăn và sinh 
sống xa nhà mẹ. Một ngày nọ, bà Hai bị
 tai nạn và bó bột chân, buộc năm người 
con phải tìm cách về chăm lo cho mẹ.' )

