package model;

public class ThanhVien {
	private int IDCustomer;
	private String Customername;
	private int CustomerPhoneNumber;
	private String LoaiDoiTuong;
	public ThanhVien(int IDCustomer, String Customername, String CustomerPhoneNumber, String loaiDoiTuong) {
		super();
		IDCustomer = IDCustomer;
		Customername = Customername;
		CustomerPhoneNumber = CustomerPhoneNumber;
		LoaiDoiTuong = loaiDoiTuong;
	}
	public int getIDCustomer() {
		return IDCustomer;
	}
	public void setMATv(int IDCustomer) {
		IDCustomer = IDCustomer;
	}
	public String getCustomername() {
		return Customername;
	}
	public void setCustomername(String Customername) {
		Customername = Customername;
	}
	public int getCustomerPhoneNumber() {
		return CustomerPhoneNumber;
	}
	public void setCustomerPhoneNumber(int CustomerPhoneNumber) {
		CustomerPhoneNumber = CustomerPhoneNumber;
	}
	public String getLoaiDoiTuong() {
		return LoaiDoiTuong;
	}
	public void setLoaiDoiTuong(String loaiDoiTuong) {
		LoaiDoiTuong = loaiDoiTuong;
	}
	
}
