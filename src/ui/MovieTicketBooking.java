package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MovieTicketBooking extends JFrame {

    private final JButton[][] seatButtons = new JButton[5][5];
    private final ArrayList<String> selectedSeats = new ArrayList<>();
    private final JLabel totalLabel = new JLabel("Total: 0 VND");
    private final int seatPrice = 50000;

    public MovieTicketBooking() {
        setTitle("Movie Ticket Booking");
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
                seatButton.setBackground(Color.LIGHT_GRAY);
                seatButton.addActionListener(new SeatSelectionListener(seatNumber, seatButton));
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
        int total = selectedSeats.size() * seatPrice;
        totalLabel.setText("Tổng: " + total + " VND");
    }

    private void confirmBooking() {
        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ghế", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Booking confirmed for seats: " + selectedSeats, "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void cancelBooking() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
        }
        dispose();
        
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieTicketBooking bookingApp = new MovieTicketBooking();
            bookingApp.setVisible(true);
        });
    }
}