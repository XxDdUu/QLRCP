use Movie;




-- khoa ngoai cua table Ticket
alter table Ticket add constraint fk_Seat_Ticket foreign key (IDSeat) references Seat(IDSeat) on delete cascade on update cascade;
alter table Ticket add constraint fk_Custom_Ticket foreign key (IDCustomer) references Customer(IDCustomer) on delete cascade;
alter table Ticket add constraint fk_Room_Ticket foreign key (IDRoom) references Room(IDRoom) on delete no action
alter table Ticket add constraint fk_Movie_Ticket foreign key (IDMovie) references Movie(IDMovie) on delete cascade on update cascade;

-- khoa ngoai cua table Seat
alter table Seat add constraint fk_Room_Seat foreign key (IDRoom) references Room(IDRoom) on delete cascade on update cascade;

-- insert du lieu mau cho table Customer
insert into Customer(CustomerName, CustomerPass, CustomerPhoneNumber, CustomerType) values ('Duong', '', '0905040302', 'Nguoi Lon'), ('Hai', 'Hai12345', '0847576756', 'Tre em');
select * from Customer;
 
 -- insert du lieu mau cho table Staff
insert into Staff(Staff_Name,Staff_Pass ) values ('@adminBach', 'Bachnguyen54'), ('@adminDuy', 'Duy15122006'), ('@adminTuat', 'Tuat12345'), ('@adminThong', 'Thong12345');


INSERT INTO Movie (Title, Genre, Duration, Director,release_date, Moviedescrip ) VALUES 
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
con phải tìm cách về chăm lo cho mẹ.' );

-- insert du lieu mau cho table Room
insert into Room(RoomName, capacity, Status) values ('RA', '25', default), ('RB', '25', default), ('RC', '25', default);
select * from Room;
-- insert dữ liệu mẫu cho table Seat
INSERT INTO Seat(IDRoom, SeatName)
VALUES 
(1, 'R1C1'),(1, 'R1C2'),(1, 'R1C3'),(1, 'R1C4'),(1, 'R1C5'),
(1, 'R2C1'),(1, 'R2C2'),(1, 'R2C3'),(1, 'R2C4'),(1, 'R2C5'),
(1, 'R3C1'),(1, 'R3C2'),(1, 'R3C3'),(1, 'R3C4'),(1, 'R3C5'),
(1, 'R4C1'),(1, 'R4C2'),(1, 'R4C3'),(1, 'R4C4'),(1, 'R4C5'),
(1, 'R5C1'),(1, 'R5C2'),(1, 'R5C3'),(1, 'R5C4'),(1, 'R5C5'),

(2, 'R1C1'),(2, 'R1C2'),(2, 'R1C3'),(2, 'R1C4'),(2, 'R1C5'),
(2, 'R2C1'),(2, 'R2C2'),(2, 'R2C3'),(2, 'R2C4'),(2, 'R2C5'),
(2, 'R3C1'),(2, 'R3C2'),(2, 'R3C3'),(2, 'R3C4'),(2, 'R3C5'),
(2, 'R4C1'),(2, 'R4C2'),(2, 'R4C3'),(2, 'R4C4'),(2, 'R4C5'),
(2, 'R5C1'),(2, 'R5C2'),(2, 'R5C3'),(2, 'R5C4'),(2, 'R5C5'),

(3, 'R1C1'),(3, 'R1C2'),(3, 'R1C3'),(3, 'R1C4'),(3, 'R1C5'),
(3, 'R2C1'),(3, 'R2C2'),(3, 'R2C3'),(3, 'R2C4'),(3, 'R2C5'),
(3, 'R3C1'),(3, 'R3C2'),(3, 'R3C3'),(3, 'R3C4'),(3, 'R3C5'),
(3, 'R4C1'),(3, 'R4C2'),(3, 'R4C3'),(3, 'R4C4'),(3, 'R4C5'),
(3, 'R5C1'),(3, 'R5C2'),(3, 'R5C3'),(3, 'R5C4'),(3, 'R5C5');
