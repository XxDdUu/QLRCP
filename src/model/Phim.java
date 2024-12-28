package model;

import java.time.Year;

public class Phim {
	private int MaP;
	private String TenP;
	private String Theloai;
	private int thoiluong;
	private Year NamSX;
	private String daodien;
	private String mota;

	public Phim(int maP, String tenP, String theloai, int thoiluong, Year namSX, String daodien, String mota) {
		MaP = maP;
		TenP = tenP;
		Theloai = theloai;
		this.thoiluong = thoiluong;
		NamSX = namSX;
		this.daodien = daodien;
		this.mota = mota;
	}

	public int getMaP() {
		return MaP;
	}

	public void setMaP(int maP) {
		MaP = maP;
	}

	public String getTenP() {
		return TenP;
	}

	public void setTenP(String tenP) {
		TenP = tenP;
	}

	public String getTheloai() {
		return Theloai;
	}

	public void setTheloai(String theloai) {
		Theloai = theloai;
	}

	public int getThoiluong() {
		return thoiluong;
	}

	public void setThoiluong(int thoiluong) {
		this.thoiluong = thoiluong;
	}

	public Year getNamSX() {
		return NamSX;
	}

	public void setNamSX(Year namSX) {
		NamSX = namSX;
	}

	public String getDaodien() {
		return daodien;
	}

	public void setDaodien(String daodien) {
		this.daodien = daodien;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}
}
