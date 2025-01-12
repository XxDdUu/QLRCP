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
-- Cập nhật RoomStatus thành 'Het cho' khi chỗ ngồi >= 25 và 'Con trong' khi ngược lại
CREATE TRIGGER UpdateRoomStatus
ON Seat
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE Room
    SET RoomStatus = CASE
        WHEN (SELECT COUNT(*) 
              FROM Seat 
              WHERE Seat.IDRoom = Room.IDRoom AND Seat.SeatStatus = 'Booked') >= 25 
        THEN 'Het cho'
        ELSE 'Con trong'
    END
    FROM Room;
END;
GO
