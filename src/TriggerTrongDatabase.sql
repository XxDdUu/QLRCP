use Movie
-- Chuyển SeatStatus thành 'Booked' khi thêm vé mới và 'Available' khi xoá vé cũ
CREATE TRIGGER UpdateSeatStatus
ON Ticket
AFTER INSERT, DELETE
AS
BEGIN
    IF EXISTS (SELECT * FROM Inserted)
    BEGIN
        UPDATE Seat
        SET SeatStatus = 'Booked'
        FROM Seat
        INNER JOIN Inserted i
        ON Seat.IDSeat = i.IDSeat;
    END;
    IF EXISTS (SELECT * FROM Deleted)
    BEGIN
        UPDATE Seat
        SET SeatStatus = 'Available'
        FROM Seat
        INNER JOIN Deleted d
        ON Seat.IDSeat = d.IDSeat;
    END;
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
