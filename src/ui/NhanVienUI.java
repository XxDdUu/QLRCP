package ui;
import dao.DatabaseOperation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public final class NhanVienUI extends JFrame {
    public NhanVienUI() {
        super("Quản Lí");
        initUI();
    }
    private Object[][] loadMovieDataFromDatabase() {
        ArrayList<Object[]> movielist = new ArrayList<>();
        try (Connection connection = DatabaseOperation.connectToDataBase()){
            String sql = "Select * from Movie;";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("IDMovie");
                    String title = rs.getString("Title");
                    String genre = rs.getString("Genre");
                    int duration = rs.getInt("Duration");
                    String director = rs.getString("Director");
                    java.sql.Date release_date = rs.getDate("release_date");
                    String description = rs.getString("Moviedescrip");

                    movielist.add(new Object[]{id, title, genre, duration, director, release_date, description});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movielist.toArray(new Object[0][0]);
    }
    private Object[][] loadCustomerFromDatabase() {
        ArrayList<Object[]> CustomerList = new ArrayList<>();
        try (Connection connection = DatabaseOperation.connectToDataBase()){
            String sql = "Select * from Customer;";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 java.sql.ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    int id = rs.getInt("IDCustomer");
                    String name = rs.getString("CustomerName");
                    String phone = rs.getString("CustomerPhoneNumber");
                    String type = rs.getString("CustomerType");

                    CustomerList.add(new Object[]{id, name, phone, type});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CustomerList.toArray(new Object[CustomerList.size()][]);
    }
    public void initUI() {
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel p1= new JPanel();
        p1.setLayout(new GridLayout(1,2));
        JButton b1= new JButton("Khách hàng");
        JButton b2= new JButton("Phim");
        p1.add(b1); p1.add(b2);
        this.add(p1, BorderLayout.NORTH);
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout(2, 4));
        JButton b3 = new JButton("Thêm Phim");
        JButton b4 = new JButton("Tìm phim");
        JButton b5 = new JButton("Xoa phim");
        JButton b6 = new JButton("Sửa phim");
        ButtonPanel.add(b3);
        ButtonPanel.add(b4);
        ButtonPanel.add(b5);
        ButtonPanel.add(b6);
        ButtonPanel.setVisible(false);
        this.add(ButtonPanel, BorderLayout.SOUTH);

        Object[][] khdatatab= loadCustomerFromDatabase();
        String[] cotkh= {"ID", "Tên", "So dien thoai", "Loại khách"};

        Object[][] phimdatatab= loadMovieDataFromDatabase();
        String[] cotph= {"ID", "Tiêu đề", "Thể loại phim", "Thời lượng phim",  "Đạo diễn", "Ngày phát hành", "Miêu tả"};
        DefaultTableModel tblmodel1= new DefaultTableModel(khdatatab, cotkh);
        DefaultTableModel tblmodel2= new DefaultTableModel(phimdatatab, cotph);
        JTable t1= new JTable(tblmodel1);
        JTable t2= new JTable(tblmodel2);
        DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0; i<t1.getColumnCount(); i++) {
            t1.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        for (int i=0; i<t2.getColumnCount(); i++) {
            t2.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        JScrollPane sp1= new JScrollPane(t1);
        JScrollPane sp2= new JScrollPane(t2);
        JPanel p2= new JPanel();
        p2.setLayout(new CardLayout());
        p2.add(sp1); p2.add(sp2);
        this.add(p2, BorderLayout.CENTER);


        b1.addActionListener((ActionEvent e)-> {
            sp1.setVisible(true);
            sp2.setVisible(false);
            ButtonPanel.setVisible(false);
        });


        b2.addActionListener((ActionEvent e)-> {
            sp1.setVisible(false);
            sp2.setVisible(true);
            ButtonPanel.setVisible(true);
        });


        b3.addActionListener((e) -> {
            JTextField title = new JTextField();
            JTextField genre = new JTextField();
            JTextField duration = new JTextField();
            SpinnerDateModel datamodel = new SpinnerDateModel();
            JSpinner release_date = new JSpinner(datamodel);
            release_date.setEditor(new JSpinner.DateEditor(release_date, "yyyy-MM-dd"));
            JTextField director = new JTextField();
            JTextArea description = new JTextArea();

            Object[] inputFields = {
                    "Tên phim: ", title,
                    "the loai: ", genre,
                    "Thoi Luong: ", duration,
                    "Ngay chieu phim: ", release_date,
                    "Dao dien: ", director,
                    "Mo ta: ", new JScrollPane(description)
            };

            int OK_option =JOptionPane.showConfirmDialog(this, inputFields, "Them Phim Moi", JOptionPane.OK_CANCEL_OPTION);

            if (OK_option == JOptionPane.OK_OPTION) {
                try (Connection connection = DatabaseOperation.connectToDataBase()) {
                    String sql = "Insert into Movie(Title, Genre, Duration, Director, release_date, Moviedescrip) values (?, ?, ?, ?, ?, ?);";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, title.getText());
                        stmt.setString(2, genre.getText());
                        stmt.setInt(3, Integer.parseInt(duration.getText()));
                        stmt.setString(4, director.getText());
                        java.util.Date date = (java.util.Date) release_date.getValue();
                        stmt.setDate(5, new java.sql.Date(date.getTime()));
                        stmt.setString(6, description.getText());
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Them Phim Thanh Cong");
                    }
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Loi khi them phim!", "Loi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public static void main(String[] args) {
        try{
            (new NhanVienUI()).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
