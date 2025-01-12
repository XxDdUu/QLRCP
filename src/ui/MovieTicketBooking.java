package ui;

import java.awt.BorderLayout;
import model.Phim;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.ThanhVien;
import dao.DatabaseOperation;


public class MovieTicketBooking extends JFrame {

    private final JButton[][] seatButtons = new JButton[5][5];
    private ArrayList<String> selectedSeats = new ArrayList<>();
    private final JLabel totalLabel = new JLabel("Total: 0 VND");
    private int seatPrice = 50000;
    private int totalPrice;
    private ThanhVien thanhvien;
    private String MovieID;
    private Object[] movieProperties;
    private String RoomName = "R";
    private String IDRoom;
    private List<String> bookedSeats;
    
    public MovieTicketBooking(ThanhVien thanhvien, String MovieID, String RoomName) {
    	this.thanhvien = thanhvien;
    	this.MovieID = MovieID;
    	this.RoomName = RoomName;
    	initializeBooking();
    }
    

	private void initializeBooking() {
		IDRoom = getIDRoom(RoomName);
		bookedSeats = fetchBookedSeatsFromDatabase(IDRoom);
        setTitle("Movie Ticket Booking" + " - " + RoomName);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Seat selection panel
        JPanel seatPanel = new JPanel(new GridLayout(5, 5, 5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String seatNumber = "R" + (i + 1) + "C" + (j + 1);
                JButton seatButton = new JButton(seatNumber);
                
                if (bookedSeats.contains(seatNumber)) {
                	seatButton.setBackground(Color.RED);
                	seatButton.setEnabled(false);
                } else {
                	seatButton.setBackground(Color.LIGHT_GRAY);
                	seatButton.addActionListener(new SeatSelectionListener(seatNumber, seatButton));
                }
                
                seatButtons[i][j] = seatButton;
                seatPanel.add(seatButton);
            }
        }

        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JButton confirmButton = new JButton("Confirm Booking");
        JButton cancelButton = new JButton("Cancel");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        infoPanel.add(totalLabel);
        infoPanel.add(buttonPanel);

        confirmButton.addActionListener(e -> confirmBooking());
        cancelButton.addActionListener(e -> cancelBooking());

        mainPanel.add(seatPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private class SeatSelectionListener implements ActionListener {
        private final String seatNumber;
        private final JButton seatButton;

        public SeatSelectionListener(String seatNumber, JButton seatButton) {
            this.seatNumber = seatNumber;
            this.seatButton = seatButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeats.contains(seatNumber)) {
                selectedSeats.remove(seatNumber);
                seatButton.setBackground(Color.LIGHT_GRAY);
            } else {
                selectedSeats.add(seatNumber);
                seatButton.setBackground(Color.GREEN);
            }
            updateTotal();
        }
    }

    private void updateTotal() {
        totalPrice = selectedSeats.size() * seatPrice;
        NumberFormat formatter = NumberFormat.getInstance();
        totalLabel.setText("Tổng: " + formatter.format(totalPrice) + " VND");
    }

    private void confirmBooking() {
        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ghế", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
//        	for (String seat : selectedSeats) {
//        		handleBookingSeatStatus(seat);
//        	}
        	int result = JOptionPane.showConfirmDialog(this, "Booking confirmed for seats: " + selectedSeats, "Confirm", JOptionPane.OK_CANCEL_OPTION);
        	if (result == JOptionPane.OK_OPTION) {
        	totalPrice = (selectedSeats.size() * seatPrice);
        	IDRoom = getIDRoom(RoomName);
        	movieProperties = getMoviePropertiesFromDB(MovieID);
        	String title = (String)movieProperties[0];
        	String genre = (String)movieProperties[1];
        	int duration = (int)movieProperties[2];
        	Phim phim = new Phim(MovieID, title, genre, duration, null, null, null);
        	new MovieTicketPropertiesDisplayBooking(thanhvien, phim, RoomName, IDRoom ,selectedSeats, totalPrice);
            dispose();
        	}
        }
    }

    private void cancelBooking() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            dispose();
            new DatVe(thanhvien);
        }
    }
    private String getIDRoom(String RoomName) {
        String query = "  SELECT IDRoom from Room WHERE RoomName = ? ";
        try (Connection connection = DatabaseOperation.connectToDataBase();
        		PreparedStatement preparedStatement = connection.prepareStatement(query);
        		){
        		preparedStatement.setString(1, RoomName);
        	ResultSet resultSet = preparedStatement.executeQuery();
        	if (resultSet.next()) {
        		return resultSet.getString("IDRoom");
        	}
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
	}
    private List<String> fetchBookedSeatsFromDatabase(String IDRoom){
    	List<String> bookedSeats = new ArrayList<>();
        
        String query = "SELECT SeatName From Seat Where SeatStatus = 'Booked' AND IDRoom = ?";
        try (Connection connection = DatabaseOperation.connectToDataBase();
        	 PreparedStatement preparedStatement = connection.prepareStatement(query);
        		){
        		preparedStatement.setString(1, IDRoom);
        		ResultSet resultSet = preparedStatement.executeQuery();
        		
        	while (resultSet.next()) {
        		bookedSeats.add(resultSet.getString("SeatName"));
        		}
        	} catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        	}
        return bookedSeats;
        }
    private Object[] getMoviePropertiesFromDB(String MovieID) {
    	Object[] movieProperties = null;
        String query = "SELECT Title, Genre, Duration, release_date FROM Movie WHERE IDMovie = ?";
        
        try (Connection connection = DatabaseOperation.connectToDataBase()){
        	PreparedStatement preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, MovieID);
        	ResultSet resultSet = preparedStatement.executeQuery();
        		while (resultSet.next()) {
        			String title = resultSet.getString("Title");
        			String genre = resultSet.getString("Genre");
        			int duration = resultSet.getInt("Duration");
        			java.sql.Date release_date = resultSet.getDate("release_date");
        			
        			movieProperties = new Object[] {title, genre, duration, release_date};
        		}
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error3: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return movieProperties;
    }
}
