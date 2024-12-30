package ui;

import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(400, 500);
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
	    getContentPane().setBackground(new Color(0x2196F3));

        getContentPane().setBackground(new Color(0x2196F3)); // Blue background

        JPanel panel = new JPanel();
        panel.setBounds(50, 80, 300, 300);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        getContentPane().add(panel);

        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0x0057D9));
        titleLabel.setBounds(100, 20, 100, 30);
        panel.add(titleLabel);

        JTextField usernameField = new JTextField();
        usernameField.setToolTipText("username");
        usernameField.setBounds(50, 72, 200, 38);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        String placeholder = " Enter your username";
        usernameField.setText(placeholder);
        usernameField.setForeground(java.awt.Color.GRAY);
        usernameField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (usernameField.getText().equals(placeholder)) {
                    usernameField.setText("");
                    usernameField.setForeground(java.awt.Color.BLACK);
                }
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (usernameField.getText().isEmpty()) {
                    usernameField.setText(placeholder);
                    usernameField.setForeground(java.awt.Color.GRAY);
                }
			}



        });
        panel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 122, 200, 38);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        String placeholder2 = " Enter ";
        passwordField.setText(placeholder2);
        passwordField.setForeground(java.awt.Color.GRAY);
        passwordField.addFocusListener(new FocusListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (passwordField.getText().equals(placeholder2)) {
					passwordField.setText("");
					passwordField.setForeground(java.awt.Color.BLACK);
                }
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (passwordField.getText().isEmpty()) {
					passwordField.setText(placeholder2);
                    passwordField.setForeground(java.awt.Color.GRAY);
                }
			}

        });
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        loginButton.setBounds(100, 170, 100, 30);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(51, 0, 255));
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        JButton btnRegister = new JButton("Create an new account");
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRegister.setForeground(new Color(51, 0, 255));
        btnRegister.setFocusPainted(false);
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setBounds(50, 216, 200, 38);
        btnRegister.addActionListener(e -> {
                dispose();
                new Register().setVisible(true);
            });
        panel.add(btnRegister);

        // Button Action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateCredentials(username, password)) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                dispose();
                if (username.startsWith("@admin")) {
                    new NhanVienUI().setVisible(true);
                }
                else {
                	new DatVe().setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });



        // Set Frame Visible
        setLocationRelativeTo(null);
        setVisible(true);
	}

        private boolean validateCredentials(String username, String password) {
            String dbUrl = "jdbc:mysql://localhost:3306/Movie";
            String dbUsername = "root";
            String dbPassword = "12345678";
            String query;

            if (username.startsWith("@admin")) {
                // Staff login
                 query = "SELECT * FROM Staff WHERE Staff_Name = ? AND Staff_Pass = ?";
            } else {
                // Customer logins
                 query = "SELECT * FROM Customer WHERE CustomerName = ? AND CustomerPass = ?";
            }
            try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        	}

	}

