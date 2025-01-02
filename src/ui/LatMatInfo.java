package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import model.ThanhVien;

public class LatMatInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ThanhVien thanhvien;
	private String Title = "Lật mặt 7";
	private String Director = "Lý Hải";
	private String RoomName = "RC";
	/**
	 * Launch the application.
	 */
	
	public LatMatInfo(ThanhVien thanhvien){
		this.thanhvien = thanhvien;
		initializeGUILatMatInfo();
	}

	/**
	 * Create the frame.
	 */
	private void initializeGUILatMatInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 165, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Lật Mặt 7");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBounds(38, 10, 237, 44);
		contentPane.add(label);
		
		JLabel imageLabel = new JLabel(new ImageIcon("D:\\Áp_phích_chính_thức_Lật_mặt_7.jpg"));
		imageLabel.setBounds(25, 57, 250, 307);
		contentPane.add(imageLabel);
		
		JTextArea txtroDinAnthony = new JTextArea();
		txtroDinAnthony.setText("Đạo diễn: Lý Hải ,\r\nNgày phát hành: 26/04/2024,T\r\nhời lượng: 138 phút\r\nNội dung: Phim kể về cuộc sống của bà Hai, \r\nmột bà lão 73 tuổi sống tại làng K'Long\r\n K'Lanh ở huyện Lạc Dương, Lâm Đồng. Bà \r\nHai đã tự mình nuôi nấng năm người con \r\nkhôn lớn sau khi chồng qua đời sớm. Mỗi \r\nngười con khi trưởng thành đều có cuộc\r\nsống riêng và thậm chí đi làm ăn và sinh \r\nsống xa nhà mẹ. Một ngày nọ, bà Hai bị\r\n tai nạn và bó bột chân, buộc năm người \r\ncon phải tìm cách về chăm lo cho mẹ.");
		txtroDinAnthony.setBounds(285, 64, 346, 246);
		contentPane.add(txtroDinAnthony);
		
		JButton btnNewButton = new JButton("Đặt vé ngay");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                        dispose();
                        String MovieID = getMovieID(Title, Director);
                        new MovieTicketBooking(thanhvien, MovieID, RoomName);
                        
			}
		});
		btnNewButton.setBounds(366, 348, 158, 44);
		contentPane.add(btnNewButton);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHy.setBounds(366, 402, 158, 44);
		btnHy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DatVe(thanhvien);
			}

        });
		contentPane.add(btnHy);
		setVisible(true);
	}
	private String getMovieID(String Title, String Director) {
		String dbUrl = "jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=QLRCP;encrypt=true;trustServerCertificate=true;";
        String dbUsername = "sa";
        String dbPassword = "duy15122006";
        String query = "  SELECT IDMovie from Movie WHERE Title = ? AND Director = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        		PreparedStatement preparedStatement = connection.prepareStatement(query);
        		){
        		preparedStatement.setString(1, Title);
        		preparedStatement.setString(2, Director);
        	ResultSet resultSet = preparedStatement.executeQuery();
        	if (resultSet.next()) {
        		return resultSet.getString("IDMovie");
        	}
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
	}
	
	
}

