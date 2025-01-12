use Movie
-- Chuyển SeatStatus thành 'Booked' khi thêm vé mới
CREATE TRIGGER UpdateSeatStatus
ON Ticket
AFTER INSERT
AS
BEGIN
    UPDATE Seat
    SET SeatStatus = 'Booked'
    FROM Seat
    INNER JOIN Inserted i
    ON Seat.IDSeat = i.IDSeat;
END;
GO
-- Chuyển RoomStatus thành 'Het cho' khi đủ 25 ghế ngồi
  CREATE TRIGGER UpdateRoomStatus
  ON Seat
  AFTER INSERT
  AS
	BEGIN
		UPDATE Room
		SET RoomStatus = 'Het cho'
		FROM Room 
		WHERE Room.IDRoom IN 
		(SELECT Seat.IDRoom
		FROM Seat
		WHERE Seat.SeatStatus = 'Booked'
		GROUP BY Seat.IDRoom
		HAVING COUNT(IDRoom) = 25);
	END;
GO
