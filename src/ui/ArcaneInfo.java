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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;

public class ArcaneInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String customerID;
	private String Title = "Arcane";
	private String Director = "Pascal Charrue và Arnaud Delord";
	private String RoomName = "RA";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArcaneInfo frame = new ArcaneInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ArcaneInfo(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * Create the frame.
	 */
	public ArcaneInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 165, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Arcane");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBounds(38, 10, 237, 44);
		contentPane.add(label);
		
		
	
		
		
		
		
		JTextArea txtroDinAnthony = new JTextArea();
		txtroDinAnthony.setText("Đạo diễn: Pascal Charrue và Arnaud Delord\r\nNgày phát hành: 6/11/2021\r\nhời lượng: 171 phút\r\nNội dung: Arcane là loạt phim hoạt hình lấy\r\nbối cảnh vũ trụ League of Legends, kể về \r\ncuộc xung đột giữa hai thành phố Piltover \r\nvà Zaun, cùng hành trình bi kịch của hai chị \r\nem Vi và Jinx. Bộ phim khai thác sâu về công \r\nnghệ Hextech, mối quan hệ gia đình, và sự \r\nđối đầu giữa lý tưởng và tham vọng.\r\n");
		txtroDinAnthony.setBounds(285, 64, 346, 246);
		contentPane.add(txtroDinAnthony);
		
		JButton btnNewButton = new JButton("Đặt vé ngay");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                        dispose();
                        String MovieID = getMovieID(Title, Director);
                        new MovieTicketBooking().setVisible(true);
                        new MovieTicketBooking(customerID, MovieID, RoomName);
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
				new DatVe().setVisible(true);
				new DatVe(customerID);
				
			}

			
			
        });
		contentPane.add(btnHy);
		
		ImageIcon originalIcon1 = new ImageIcon("D:\\riot-chi-6-nghin-ty-lam-arcane.jpg");
        int imageWidth1 = 250; // Desired image width
        int imageHeight1 = 300; // Desired image height
        ImageIcon resizedIcon1 = resizeImage1(originalIcon1, imageWidth1, imageHeight1);
        JLabel imageLabel1 = new JLabel(resizedIcon1);
        imageLabel1.setBounds(10, 59, 250, 300);
        contentPane.add(imageLabel1);
        imageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		
	}

	private ImageIcon resizeImage1(ImageIcon originalIcon1, int imageWidth1, int imageHeight1) {
		// TODO Auto-generated method stub
		return new ImageIcon(originalIcon1.getImage().getScaledInstance(imageWidth1, imageHeight1, java.awt.Image.SCALE_SMOOTH));
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

