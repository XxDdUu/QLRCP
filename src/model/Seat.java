package model;

public class Seat {
	private int IDSeat;
	private int IDRoom;
	private char row;
	private int SeatNumber;
	private String Status;
	public Seat(int iDSeat, int iDRoom, char row, int seatNumber, String status) {
		super();
		IDSeat = iDSeat;
		IDRoom = iDRoom;
		this.row = row;
		SeatNumber = seatNumber;
		Status = status;
	}
	public int getIDSeat() {
		return IDSeat;
	}
	public void setIDSeat(int iDSeat) {
		IDSeat = iDSeat;
	}
	public int getIDRoom() {
		return IDRoom;
	}
	public void setIDRoom(int iDRoom) {
		IDRoom = iDRoom;
	}
	public char getRow() {
		return row;
	}
	public void setRow(char row) {
		this.row = row;
	}
	public int getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		SeatNumber = seatNumber;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
