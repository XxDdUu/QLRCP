
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.LICHCHIEU;
import model.Phim;
import model.ThanhVien;
import model.Ve;

public class THANHVIENDAOImpl implements THANHVIENDAO{
	public Connection getConnection() throws SQLException{
		try {return DriverManager.getConnection(Constant.String_DB_Url, Constant.username, Constant.password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" ,"Error connection.", JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}
	public void bookTicket(Ve ticket) {
		String query = "";
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
				) {
			
		}catch (SQLException e) {
			
		}
	}

	@Override
	public List<LICHCHIEU> viewMovieSchedule() {
		return null;
	}

	@Override
	public Phim getMovieDetails(String MaPhim) {
		return null;
	}

	@Override
	public void addThanhVien(ThanhVien thanhvien) {
		String query = "INSERT INTO Customer (Username, PhoneNumber, CustomerType) VALUES (?, ?, ?)";
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, thanhvien.getHoTen());
			preparedStatement.setString(2, thanhvien.getDienThoai());
			preparedStatement.setString(3, thanhvien.getLoaiDoiTuong());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error", "Error adding Thanh Vien:" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
