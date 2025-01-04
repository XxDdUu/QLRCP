package ui;
import dao.DatabaseOperation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public final class NhanVienUI extends JFrame {
    public NhanVienUI() {
        super("Quản Lí");
        initUI();
    }
    private Object[][] loadTicketDataFromDatabase(){
    	ArrayList<Object[]> ticketlist = new ArrayList<>();
    	try (Connection connection = DatabaseOperation.connectToDataBase()){
    		String sql = "Select * from Ticket";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while (rs.next()) {
    			int idticket = rs.getInt("IDTicket");
    			int idmovie = rs.getInt("IDMovie");
    			int idroom = rs.getInt("IDRoom");
    			int idseat = rs.getInt("IDSeat");
    			int idcustomer = rs.getInt("IDCustomer");
    			java.sql.Date bookdate = rs.getDate("BookDate");
    			String ticketstatus = rs.getString("TicketStatus");
    			double price = rs.getDouble("Price");
    			ticketlist.add(new Object[] {idticket, idmovie, idroom, idseat, idcustomer, bookdate, ticketstatus, price});
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return ticketlist.toArray(new Object[ticketlist.size()][]);
    }
    private void UpdateTicketList(JTable t3) {
    	Object[][] ticketlist = loadTicketDataFromDatabase();
    	DefaultTableModel tblmodel3= new DefaultTableModel(ticketlist, new Object[] {"ID vé", "ID phim", "ID phòng", "ID chỗ ngồi", "ID khách hàng", "Ngày đặt", "Trạng thái vé", "Giá"});
    	t3.setModel(tblmodel3);
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
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return movielist.toArray(new Object[movielist.size()][]);
    }

    private void UpdateMovieList(JTable t2) {
        Object[][] phimdatatab = loadMovieDataFromDatabase();
        DefaultTableModel tblmodel2 = new DefaultTableModel(phimdatatab, new String[]{"ID", "Tiêu đề", "Thể loại phim", "Thời lượng phim",
                "Đạo diễn", "Ngày phát hành", "Miêu tả"});
        t2.setModel(tblmodel2);
    }
    private Object[][] loadCustomerFromDatabase() {
        ArrayList<Object[]> CustomerList = new ArrayList<>();
        try (Connection connection = DatabaseOperation.connectToDataBase()){
            String sql = "Select * from Customer;";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                try (java.sql.ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("IDCustomer");
                        String name = rs.getString("CustomerName");
                        String phone = rs.getString("CustomerPhoneNumber");
                        String type = rs.getString("CustomerType");

                        CustomerList.add(new Object[]{id, name, phone, type});
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return CustomerList.toArray(new Object[CustomerList.size()][]);
    }
    public void initUI() {
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel p1= new JPanel();
        p1.setLayout(new GridLayout(1,3));
        JButton b1= new JButton("Khách hàng");
        JButton b2= new JButton("Phim");
        JButton b7= new JButton("Vé");
        p1.add(b1); p1.add(b2); p1.add(b7);
        this.add(p1, BorderLayout.NORTH);
        JPanel ButtonPanelLayout = new JPanel();
        ButtonPanelLayout.setLayout(new CardLayout());
        JPanel ButtonPanel = new JPanel();
        JPanel TicketButtonPanel = new JPanel();
        TicketButtonPanel.setLayout(new GridLayout(2,4));
        JButton vb1 = new JButton("Thêm vé");
        JButton vb2 = new JButton("Tìm vé");
        JButton vb3 = new JButton("Xoá vé");
        JButton vb4 = new JButton("Sửa vé");
        TicketButtonPanel.add(vb1); TicketButtonPanel.add(vb2); TicketButtonPanel.add(vb3); TicketButtonPanel.add(vb4);
        ButtonPanelLayout.add(TicketButtonPanel);
        TicketButtonPanel.setVisible(false);
        ButtonPanel.setLayout(new GridLayout(2, 4));
        JButton b3 = new JButton("Thêm phim");
        JButton b4 = new JButton("Tìm phim");
        JButton b5 = new JButton("Xoá phim");
        JButton b6 = new JButton("Sửa phim");
        ButtonPanel.add(b3);
        ButtonPanel.add(b4);
        ButtonPanel.add(b5);
        ButtonPanel.add(b6);
        ButtonPanelLayout.add(ButtonPanel);
        this.add(ButtonPanelLayout, BorderLayout.SOUTH);
        ButtonPanelLayout.setVisible(false);
        ButtonPanel.setVisible(false);
        Object[][] vedatatab = loadTicketDataFromDatabase();
        Object[][] khdatatab = loadCustomerFromDatabase();
        String[] cotkh= {"ID", "Tên", "Số điện thoại", "Loại khách"};
        Object[][] phimdatatab= loadMovieDataFromDatabase();
        String[] cotph = {"ID", "Tiêu đề", "Thể loại phim", "Thời lượng phim",  "Đạo diễn", "Ngày phát hành", "Miêu tả"};
        String[] cotve = {"ID vé", "ID phim", "ID phòng", "ID chỗ ngồi", "ID khách hàng", "Ngày đặt", "Trạng thái vé", "Giá"};
        DefaultTableModel tblmodel1= new DefaultTableModel(khdatatab, cotkh);
        DefaultTableModel tblmodel2= new DefaultTableModel(phimdatatab, cotph);
        DefaultTableModel tblmodel3= new DefaultTableModel(vedatatab, cotve);
        JTable t1= new JTable(tblmodel1);
        JTable t2= new JTable(tblmodel2);
        JTable t3= new JTable(tblmodel3);
        DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0; i<t1.getColumnCount(); i++) {
            t1.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        for (int i=0; i<t2.getColumnCount(); i++) {
            t2.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        for (int i=0; i<t3.getColumnCount(); i++) {
            t3.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        JScrollPane sp1= new JScrollPane(t1);
        JScrollPane sp2= new JScrollPane(t2);
        JScrollPane sp3= new JScrollPane(t3);
        JPanel p2= new JPanel();
        p2.setLayout(new CardLayout());
        p2.add(sp1); p2.add(sp2); p2.add(sp3);
        this.add(p2, BorderLayout.CENTER);
        b1.addActionListener((ActionEvent e)-> {
            sp1.setVisible(true);
            sp2.setVisible(false);
            sp3.setVisible(false);
            ButtonPanel.setVisible(false);
            TicketButtonPanel.setVisible(false);
            ButtonPanelLayout.setVisible(false);
        });


        b2.addActionListener((ActionEvent e)-> {
            sp1.setVisible(false);
            sp2.setVisible(true);
            sp3.setVisible(false);
            ButtonPanel.setVisible(true);
            TicketButtonPanel.setVisible(false);
            ButtonPanelLayout.setVisible(true);
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
                    "Tên Phim: ", title,
                    "Thể Loại: ", genre,
                    "Thời Lượng: ", duration,
                    "Ngày Chiếu Phim: ", release_date,
                    "Đạo Diễn: ", director,
                    "Mô Tả: ", new JScrollPane(description)
            };

            int OK_option =JOptionPane.showConfirmDialog(this, inputFields, "Thêm phim mới", JOptionPane.OK_CANCEL_OPTION);

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
                        JOptionPane.showMessageDialog(this, "Thêm phim thành công");
                        UpdateMovieList(t2);
                    }
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm phim mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        b4.addActionListener(e -> {
            String key = JOptionPane.showInputDialog(this, "Nhập tên phim muốn tìm: ");
            if (key != null && !key.trim().isEmpty()) {
                try (Connection connection = DatabaseOperation.connectToDataBase()) {
                    String sql = "Select * from Movie where Title like ?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setString(1, "%" + key + "%");
                        try (ResultSet rs = stmt.executeQuery()){
                            StringBuilder result = new StringBuilder("Kết quả tìm kiếm: \n");
                            while (rs.next()) {
                                result.append("ID: ").append(rs.getInt("IDMovie")).append("\n")
                                        .append("Tên phim: ").append(rs.getString("Title")).append("\n")
                                        .append("Thể loại: ").append(rs.getString("Genre")).append("\n")
                                        .append("Thời lượng: ").append(rs.getString("Duration")).append(" Phút").append("\n")
                                        .append("Đạo diễn: ").append(rs.getString("Director")).append("\n")
                                        .append("Ngày phát hành: ").append(rs.getDate("release_date")).append("\n");
                            }
                            if (result.toString().equals("Kết quả tìm kiếm: \n")) {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy phim!");
                            } else {
                                JOptionPane.showMessageDialog(this, result.toString());
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm phim!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        b5.addActionListener(e -> {
            String key = JOptionPane.showInputDialog(this, "Nhập ID phim muốn xoá: ");
            if (key != null && !key.trim().isEmpty()) {
                try (Connection connection = DatabaseOperation.connectToDataBase()){
                    String sql = "Delete from Movie where IDMovie = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setInt(1, Integer.parseInt(key));
                        int RowsAffected = stmt.executeUpdate();
                        if (RowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "Xoá phim thành công!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy phim để xoá!");
                        }

                        UpdateMovieList(t2);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa phim!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        b6.addActionListener(e -> {
            String key = JOptionPane.showInputDialog(this, "Nhập ID phim muốn sửa: ");
            if (key != null && !key.trim().isEmpty()) {
                try (Connection connection = DatabaseOperation.connectToDataBase()){
                    String sql = "Select * from Movie where IDMovie = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setString(1, key);
                        try (ResultSet rs = stmt.executeQuery()){
                            if (rs.next()) {
                                String currentTitle = rs.getString("Title");
                                String currentGenre = rs.getString("Genre");
                                int currentDuration = rs.getInt("Duration");
                                String currentDirector = rs.getString("Director");
                                java.sql.Date currentRelease_date = rs.getDate("release_date");
                                String currentDescription = rs.getString("Moviedescrip");

                                JTextField title = new JTextField(currentTitle);
                                JTextField genre = new JTextField(currentGenre);
                                JTextField duration = new JTextField(currentDuration);
                                JTextField director = new JTextField(currentDirector);
                                SpinnerDateModel datamodel = new SpinnerDateModel(currentRelease_date, null, null, java.util.Calendar.DAY_OF_MONTH);
                                JSpinner release_date = new JSpinner(datamodel);
                                release_date.setEditor(new JSpinner.DateEditor(release_date, "yyyy-MM-dd"));
                                JTextField description = new JTextField(currentDescription);


                                Object[] inputFields = {
                                        "Tên Phim: ", title,
                                        "Thể Loại: ", genre,
                                        "Thời Lượng: ", duration,
                                        "Ngày Chiếu Phim: ", release_date,
                                        "Đạo Diễn: ", director,
                                        "Mô Tả: ", new JScrollPane(description)
                                };

                                int option = JOptionPane.showConfirmDialog(this, inputFields, "Sửa thông tin phim", JOptionPane.OK_CANCEL_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    String newTitle = title.getText().trim().isEmpty() ? currentTitle : title.getText();
                                    String newGenre = genre.getText().trim().isEmpty() ? currentGenre : genre.getText();
                                    int newDuration = duration.getText().trim().isEmpty() ? currentDuration : Integer.parseInt(duration.getText());
                                    java.util.Date newRelease_date = (java.util.Date) release_date.getValue();
                                    String newDirector = director.getText().trim().isEmpty() ? currentDirector : director.getText();
                                    String newDescription = description.getText().trim().isEmpty() ? currentDescription : description.getText();

                                    String updatesql = "Update Movie Set Title = ?, Genre = ?, Duration = ?, Director = ?, release_date = ?, Moviedescrip = ? where IDMovie = ?";
                                    try (PreparedStatement stmt2 = connection.prepareStatement(updatesql)){
                                        stmt2.setString(1, newTitle);
                                        stmt2.setString(2, newGenre);
                                        stmt2.setInt(3, newDuration);
                                        stmt2.setString(4, newDirector);
                                        stmt2.setDate(5, new java.sql.Date(newRelease_date.getTime()));
                                        stmt2.setString(6, newDescription);
                                        stmt2.setInt(7, Integer.parseInt(key));
                                        int RowsAffected = stmt2.executeUpdate();
                                        if (RowsAffected > 0 ) {
                                            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
                                            UpdateMovieList(t2);
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy ID muốn cập nhật!");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi sửa phim!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        b7.addActionListener(e -> {
        	sp3.setVisible(true);
        	sp2.setVisible(false);
        	sp1.setVisible(false);
        	ButtonPanel.setVisible(false);
        	ButtonPanelLayout.setVisible(true);
        	TicketButtonPanel.setVisible(true);
        });
        
        vb1.addActionListener(e -> {
        	JTextField idmovie = new JTextField();
        	JTextField idroom = new JTextField();
        	JTextField idseat = new JTextField();
        	JTextField idcustomer = new JTextField();
        	SpinnerDateModel datemodel = new SpinnerDateModel();
        	JSpinner bookdate = new JSpinner(datemodel);
        	bookdate.setEditor(new JSpinner.DateEditor(bookdate, "yyyy-MM-dd"));
        	JTextField ticketstatus = new JTextField();
        	JTextField price = new JTextField();
        	Object[] input = {
        			"ID phim", idmovie,
        			"ID phòng", idroom,
        			"ID chỗ ngồi", idseat,
        			"ID khách hàng", idcustomer,
        			"Ngày đặt", bookdate,
        			"Trạng thái vé", ticketstatus,
        			"Giá", price
        	};
        	int OK_option =JOptionPane.showConfirmDialog(this, input, "Thêm vé mới", JOptionPane.OK_CANCEL_OPTION);
        	if (OK_option == JOptionPane.OK_OPTION) {
        		try (Connection connection = DatabaseOperation.connectToDataBase()){
        			String sql = "Insert into Ticket(IDMovie, IDRoom, IDSeat, IDCustomer, BookDate, TicketStatus, Price) values (?, ?, ?, ?, ?, ?, ?);";
        			try (PreparedStatement stmt = connection.prepareStatement(sql)){
        				stmt.setInt(1, Integer.parseInt(idmovie.getText()));
        				stmt.setInt(2, Integer.parseInt(idroom.getText()));
        				stmt.setInt(3, Integer.parseInt(idseat.getText()));
        				stmt.setInt(4, Integer.parseInt(idcustomer.getText()));
        				java.util.Date date = (java.util.Date) bookdate.getValue();
        				stmt.setDate(5, new java.sql.Date(date.getTime()));
        				stmt.setString(6, ticketstatus.getText());
        				stmt.setDouble(7, Double.parseDouble(price.getText()));
        				stmt.execute();
        				UpdateTicketList(t3);
        			} catch (Exception er) {
        				er.printStackTrace();
        			}
        		} catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm vé mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
