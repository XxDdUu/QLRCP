package ui;

import java.awt.BorderLayout;
import dao.DatabaseOperation;
import model.Phim;
import java.awt.FlowLayout;
import java.sql.Connection;
import model.ThanhVien;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MovieTicketPropertiesDisplayBooking extends JFrame {
	private final ThanhVien thanhvien;
	private final Phim phim;
	private final String RoomName;
	private final String IDRoom;
	private final ArrayList<String> selectedSeats; // SeatNumber == SeatName
	private String TicketStatus;
	private final int Price;
	private NumberFormat formatter = NumberFormat.getInstance();
		
	
	   
	
	   

	public MovieTicketPropertiesDisplayBooking(ThanhVien thanhvien, Phim phim, String RoomName,String IDRoom, ArrayList<String> selectedSeats, int price) {
		super("Xác nhận đặt vé");
		this.thanhvien = thanhvien;
		this.phim = phim;
		this.RoomName = RoomName;
		this.IDRoom = IDRoom;
		this.selectedSeats = selectedSeats;
		this.Price = price;
		initializeFinalConfirmBookingTicket();
		
	}
	private void initializeFinalConfirmBookingTicket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);

        // Tạo panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblHoten= new JLabel("Họ tên:");
        lblHoten.setBounds(10, 11, 228, 30);
        JTextField txtHoten = new JTextField();
        txtHoten.setText(thanhvien.getHoten());
        txtHoten.setBounds(248, 11, 228, 30);
        txtHoten.setEditable(false);
        JLabel lblTenPhim = new JLabel("Tên Phim:");
        lblTenPhim.setBounds(10, 51, 228, 30);
        JTextField txtTenPhim = new JTextField();
        txtTenPhim.setText(phim.getTitle());
        txtTenPhim.setBounds(248, 51, 228, 30);
        txtTenPhim.setEditable(false);
        JLabel lblThoiluong = new JLabel("Thời lượng:");
        lblThoiluong.setBounds(10, 91, 228, 30);
        JTextField txtThoiluong = new JTextField();
        txtThoiluong.setText(String.valueOf(phim.getDuration()) + " phút");
        txtThoiluong.setBounds(248, 91, 228, 30);
        txtThoiluong.setEditable(false);
        JLabel lblTheloai = new JLabel("Thể loại:");
        lblTheloai.setBounds(10, 131, 228, 30);
        JTextField txtTheloai= new JTextField();
        txtTheloai.setText(phim.getGenre());
        txtTheloai.setBounds(248, 131, 228, 30);
        txtTheloai.setEditable(false);
        JLabel lblVitrighe = new JLabel("Vị trí ghế:"); //Seatname
        lblVitrighe.setBounds(10, 171, 228, 30);
        JTextField txtVitrighe = new JTextField();
        txtVitrighe.setText(String.join(", ", selectedSeats));
        txtVitrighe.setBounds(248, 171, 228, 30);
        txtVitrighe.setEditable(false);
        JLabel lblPhongChieu = new JLabel("Phòng chiếu:");
        lblPhongChieu.setBounds(10, 211, 228, 30);
        JTextField txtPhongChieu = new JTextField();
        txtPhongChieu.setText(RoomName);
        txtPhongChieu.setBounds(248, 211, 228, 30);
        txtPhongChieu.setEditable(false);
        JLabel lblPrice = new JLabel("Tổng tiền:");
        lblPrice.setBounds(10, 251, 228, 30);
        JTextField txtPrice = new JTextField();
        txtPrice.setText((formatter.format(Price) + " VND"));
        txtPrice.setBounds(248, 251, 228, 30);
        txtPrice.setEditable(false);
        JLabel lblTicketStatus = new JLabel("Thanh toán:");
        lblTicketStatus.setBounds(10, 291, 228, 30);
        JComboBox<String> cbTicketStatus = new JComboBox<>(new String[]{"Thanh toán trực tuyến", "Tiền mặt"});
        cbTicketStatus.setBounds(248, 291, 228, 30);
        mainPanel.setLayout(null);

        mainPanel.add(lblHoten);
        mainPanel.add(txtHoten);
        mainPanel.add(lblTenPhim);
        mainPanel.add(txtTenPhim);
        mainPanel.add(lblThoiluong);
        mainPanel.add(txtThoiluong);
        mainPanel.add(lblTenPhim);
        mainPanel.add(txtTenPhim);
        mainPanel.add(lblTheloai);
        mainPanel.add(txtTheloai);
        mainPanel.add(lblVitrighe);
        mainPanel.add(txtVitrighe);
        mainPanel.add(lblPhongChieu);
        mainPanel.add(txtPhongChieu);
        mainPanel.add(lblPrice);
        mainPanel.add(txtPrice);
        mainPanel.add(lblTicketStatus);
        mainPanel.add(cbTicketStatus);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton btnConfirm = new JButton("Xác Nhận");
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> {
        	int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new DatVe(thanhvien);
            }
        });
        btnConfirm.addActionListener(e -> confirmBooking());
        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancel);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(248, 252, 228, 30);
        mainPanel.add(textField);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị frame
        setVisible(true);
	}

	private void handleUpdateSeatStatus(String SeatNumber) {
	        String query = "Update Seat SET SeatStatus = 'Booked' WHERE SeatName = ? ";

	        try (Connection connection = DatabaseOperation.connectToDataBase();
	        	PreparedStatement preparedStatement = connection.prepareStatement(query);	
	        		){
	        	preparedStatement.setString(1, SeatNumber);
	        	preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	private void insertDataIntoDatabase(int IDMovie, int IDRoom, int IDSeat, int IDCustomer, String TicketStatus, String price) {
		
	}
	
	private void confirmBooking() {
		for (String seat : selectedSeats) {
			handleUpdateSeatStatus(seat);
			
		}
	}
}
