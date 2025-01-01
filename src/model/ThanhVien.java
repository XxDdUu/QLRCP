package model;

public class ThanhVien {
	private String MATv;
	private String Hoten;
	private String phoneNumber;
	private String LoaiDoiTuong;
	public ThanhVien(String mATv, String phoneNumber, String Hoten, String loaiDoiTuong) {
		super();
		this.MATv = mATv;
		this.phoneNumber = phoneNumber;
		this.Hoten = Hoten;
		this.LoaiDoiTuong = loaiDoiTuong;
	}
	public String getMATv() {
		return MATv;
	}
	public void setMATv(String mATv) {
		MATv = mATv;
	}
	public String getHoten() {
		return Hoten;
	}
	public void setHoten(String hoten) {
		Hoten = hoten;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLoaiDoiTuong() {
		return LoaiDoiTuong;
	}
	public void setLoaiDoiTuong(String loaiDoiTuong) {
		LoaiDoiTuong = loaiDoiTuong;
	}
	
	
}
