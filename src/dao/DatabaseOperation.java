package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperation {
	public Connection connectToDataBase() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(Constant.String_DB_Url, Constant.username, Constant.password);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return connection;
	}
	public int ExcuteUpdate(String sql, Object[] values) {
		int rowAffected = 0;
		try (Connection connection = connectToDataBase()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				preparedStatement.setObject(i + 1, values[i]);
			}
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}
	public ResultSet executeQuery(String sql, Object[] values) {
        try {
            Connection connection = connectToDataBase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query: " + e.getMessage());
        }
    }
}
