
package ui;

import java.awt.EventQueue;

import dao.DatabaseOperation;
import model.ThanhVien;
import ui.Login;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Scrollbar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AvengerInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ThanhVien thanhvien;
	private String Title = "Avenger";
	private String Director = "Anthony Russo, Joe Russo";
	private String RoomName = "RB";
	/**
	 * Launch the application.
	 */
	public AvengerInfo(ThanhVien thanhvien) {
		this.thanhvien = thanhvien;
		initializeGUIAvengerInfo();
	}

	private void initializeGUIAvengerInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 165, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Avenger : Endgame");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBounds(38, 10, 237, 44);
		contentPane.add(label);
		
		JLabel imageLabel = new JLabel(new ImageIcon("D:\\MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_FMjpg_UX1000_.jpg"));
		imageLabel.setBounds(25, 57, 250, 300);
		contentPane.add(imageLabel);
		
		JTextArea txtroDinAnthony = new JTextArea();
		txtroDinAnthony.setText("Đạo diễn: Anthony Russo, Joe Russo\r\nNgày phát hành: 26/04/2019\r\nThời lượng: 181 phút\r\nNội dung: Sau cú búng tay của Thanos,các \r\nAvengers còn lại phải tập hợp để đảo ngược \r\nthiệt hại và cứu vãn vũ trụ.\r\n\r\n");
		txtroDinAnthony.setBounds(285, 64, 346, 139);
		txtroDinAnthony.setEditable(false);
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
		btnNewButton.setBounds(366, 235, 158, 44);
		contentPane.add(btnNewButton);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHy.setBounds(366, 294, 158, 44);
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
        String query = "  SELECT IDMovie from Movie WHERE Title = ? AND Director = ?";
        try (Connection connection = DatabaseOperation.connectToDataBase();
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

