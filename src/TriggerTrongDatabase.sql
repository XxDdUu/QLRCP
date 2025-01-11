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
