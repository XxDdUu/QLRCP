
package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private JTextField usernameField, phoneField, typeField;
    private JPasswordField passwordField;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Register frame = new Register();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Register() {

        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(0x2196F3));

        JPanel panel = createFormPanel();
        add(panel);

        setLocationRelativeTo(null); // Center window
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(40, 50, 300, 350);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0x0057D9));
        titleLabel.setBounds(100, 10, 120, 30);
        panel.add(titleLabel);

        usernameField = createPlaceholderTextField("Enter your username", 50);
        panel.add(usernameField);

        phoneField = createPlaceholderTextField("Enter your phone number", 100);
        panel.add(phoneField);

        passwordField = createPlaceholderPasswordField();
        panel.add(passwordField);

        typeField = createPlaceholderTextField("Enter user type", 200);
        panel.add(typeField);

        JButton registerButton = new JButton("Confirm");
        registerButton.setBounds(90, 260, 120, 30);
        registerButton.setForeground(Color.BLACK);
        registerButton.setBackground(new Color(0x0057D9));
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(this::handleRegister);
        panel.add(registerButton);

        return panel;
    }

    private JTextField createPlaceholderTextField(String placeholder, int y) {
        JTextField textField = new JTextField(placeholder);
        textField.setBounds(50, y, 200, 30);
        setPlaceholderBehavior(textField, placeholder);
        return textField;
    }

    private JPasswordField createPlaceholderPasswordField() {
        JPasswordField passwordField = new JPasswordField("Enter your password");
        passwordField.setBounds(50, 150, 200, 30);
        setPlaceholderBehavior(passwordField, "Enter your password");
        return passwordField;
    }

    private void setPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());
        String type = typeField.getText();
        
        
        if (username.isEmpty() || phone.isEmpty() || password.isEmpty() || (!username.startsWith("@") && type.isEmpty())) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!phone.matches("^[0-9]{10,15}$")) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 to 15 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String insertQuery;
        boolean isStaff = username.startsWith("@admin");

        if (isStaff) {
            // Staff registration
            insertQuery = "INSERT INTO Staff (Staff_Name, Staff_Mail, Staff_Pass) VALUES (?, ?, ?)";
        } else {
            // Customer registration
            insertQuery = "INSERT INTO Customer (CustomerName, CustomerPhoneNumber, CustomerPass, CustomerType) VALUES (?, ?, ?, ?)";
        }

        String dbUrl = "jdbc:mysql://localhost:3306/Movie";
        String dbUsername = "root";
        String dbPassword = "12345678";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, password);

            if (!isStaff) {
                // Add the fourth parameter only for customer registration
                preparedStatement.setString(4, type);
            }

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Login().setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
