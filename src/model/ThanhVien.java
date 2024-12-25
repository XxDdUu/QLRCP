package model;

public class ThanhVien {
	private String MATv;
	private String HoTen;
	private String email;
	private String LoaiDoiTuong;
	public ThanhVien(String mATv, String hoTen, String email, String loaiDoiTuong) {
		super();
		MATv = mATv;
		HoTen = hoTen;
		email = email;
		LoaiDoiTuong = loaiDoiTuong;
	}
	public String getMATv() {
		return MATv;
	}
	public void setMATv(String mATv) {
		MATv = mATv;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		email = email;
	}
	public String getLoaiDoiTuong() {
		return LoaiDoiTuong;
	}
	public void setLoaiDoiTuong(String loaiDoiTuong) {
		LoaiDoiTuong = loaiDoiTuong;
	}
	
}
