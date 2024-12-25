package model;

public class Nhanvien {
    private String IDStaff;
    private String Staffname;
    private String Staffmail;
    private String IDCinema;

    public Nhanvien(String IDStaff, String staffname, String staffmail, String IDCinema) {
        this.IDStaff = IDStaff;
        Staffname = staffname;
        Staffmail = staffmail;
        this.IDCinema = IDCinema;
    }

    public String getIDStaff() {
        return IDStaff;
    }

    public void setIDStaff(String IDStaff) {
        this.IDStaff = IDStaff;
    }

    public String getStaffname() {
        return Staffname;
    }

    public void setStaffname(String staffname) {
        Staffname = staffname;
    }

    public String getStaffmail() {
        return Staffmail;
    }

    public void setStaffmail(String staffmail) {
        Staffmail = staffmail;
    }

    public String getIDCinema() {
        return IDCinema;
    }

    public void setIDCinema(String IDCinema) {
        this.IDCinema = IDCinema;
    }
}
