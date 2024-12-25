package model;

public class Room {
	private int IDRoom;
	private int IDCinema;
	private String RoomName;
	private int capacity;
	private String Status;
	public Room(int iDRoom, int iDCinema, String roomName, int capacity, String status) {
		super();
		IDRoom = iDRoom;
		IDCinema = iDCinema;
		RoomName = roomName;
		this.capacity = capacity;
		Status = status;
	}
	public int getIDRoom() {
		return IDRoom;
	}
	public void setIDRoom(int iDRoom) {
		IDRoom = iDRoom;
	}
	public int getIDCinema() {
		return IDCinema;
	}
	public void setIDCinema(int iDCinema) {
		IDCinema = iDCinema;
	}
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
