package model;

public class RAPPHIM {
    private String IDCinema;
    private String CinemaName;
    private String Adress;
    private int PhoneNumber;

    public RAPPHIM(String IDCinema, String cinemaName, String adress, int phoneNumber) {
        this.IDCinema = IDCinema;
        CinemaName = cinemaName;
        Adress = adress;
        PhoneNumber = phoneNumber;
    }

    public String getIDCinema() {
        return IDCinema;
    }

    public void setIDCinema(String IDCinema) {
        this.IDCinema = IDCinema;
    }

    public String getCinemaName() {
        return CinemaName;
    }

    public void setCinemaName(String cinemaName) {
        CinemaName = cinemaName;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

}