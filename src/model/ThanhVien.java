package model;

public class ThanhVien {
	private int MATv;
	private String HoTen;
	private String email;
	private String LoaiDoiTuong;
	public ThanhVien(int mATv, String hoTen, String email, String loaiDoiTuong) {
		super();
		MATv = mATv;
		HoTen = hoTen;
		email = email;
		LoaiDoiTuong = loaiDoiTuong;
	}
	public int getMATv() {
		return MATv;
	}
	public void setMATv(int mATv) {
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
