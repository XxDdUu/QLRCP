package ui;
import dao.DatabaseOperation;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;


public final class NhanVienUI extends JFrame {
    public NhanVienUI() {
        super("Quản Lí");
        setBackground(new Color(255, 255, 255));
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
    			DecimalFormat dec= new DecimalFormat("#,##0");
    			String price = dec.format(rs.getDouble("Price"))+" đ";
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
    	DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        t3.setModel(tblmodel3);
        for (int i=0; i<t3.getColumnCount(); i++) {
            t3.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
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
        DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        t2.setModel(tblmodel2);
        for (int i=0; i<t2.getColumnCount(); i++) {
            t2.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
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
    private void UpdateCustomerList(JTable table) {
        Object[][] khachdatatab = loadCustomerFromDatabase();
        DefaultTableModel tblmodel2 = new DefaultTableModel(khachdatatab, new String[]{"ID", "Tên", "Số điện thoại", "Loại đối tượng"});
        DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table.setModel(tblmodel2);
        for (int i=0; i<table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }
    
    public void initUI() {
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        JPanel p1= new JPanel();
        p1.setBackground(new Color(0, 255, 128));
        p1.setLayout(new GridLayout(1,3));

        JButton KhachHangButton= new JButton("Khách hàng");
        KhachHangButton.setBackground(new Color(255, 255, 128));
        KhachHangButton.setForeground(new Color(0, 0, 0));

        JButton PhimButton= new JButton("Phim");
        PhimButton.setBackground(new Color(255, 255, 128));

        JButton VeButton= new JButton("Vé");
        VeButton.setBackground(new Color(255, 255, 128));

        JButton ExitButton = new JButton("Đăng xuất");
        ExitButton.setBackground(new Color(255, 66, 66));
        p1.add(KhachHangButton);
        p1.add(PhimButton);
        p1.add(VeButton);
        p1.add(ExitButton);

        getContentPane().add(p1, BorderLayout.NORTH);
        JPanel ButtonPanelLayout = new JPanel();
        ButtonPanelLayout.setLayout(new CardLayout());
        JPanel ButtonPanel = new JPanel();
        JPanel TicketButtonPanel = new JPanel();
        JPanel CustomerButtonPanel = new JPanel();
        CustomerButtonPanel.setLayout(new GridLayout(1, 1));
        
        TicketButtonPanel.setLayout(new GridLayout(2,1));
        JPanel TicketButtonPanelSub= new JPanel();
        TicketButtonPanelSub.setLayout(new GridLayout(1,2));
        
        JButton TimVaSuaThongTinKhach = new JButton("Tìm và Sửa thông tin khách hàng");
        TimVaSuaThongTinKhach.setBackground(new Color(192, 192, 192));
        CustomerButtonPanel.add(TimVaSuaThongTinKhach);
        ButtonPanelLayout.add(CustomerButtonPanel);
        CustomerButtonPanel.setVisible(true);
        
        JButton vb2 = new JButton("Tìm vé");
        vb2.setBackground(new Color(192, 192, 192));
        JButton vb3 = new JButton("Xoá vé");
        vb3.setBackground(new Color(192, 192, 192));
        JButton vb4 = new JButton("Sửa vé");
        vb4.setBackground(new Color(192, 192, 192));
        
        TicketButtonPanelSub.add(vb2);
        TicketButtonPanelSub.add(vb3);
        TicketButtonPanel.add(TicketButtonPanelSub);
        
        TicketButtonPanel.add(vb4);
        ButtonPanelLayout.add(TicketButtonPanel);
        
        ButtonPanel.setLayout(new GridLayout(1, 1));
        JButton b1 = new JButton("Tìm phim");
        b1.setBackground(new Color(192, 192, 192));
        ButtonPanel.add(b1);
        ButtonPanelLayout.add(ButtonPanel);
        getContentPane().add(ButtonPanelLayout, BorderLayout.SOUTH);
        ButtonPanelLayout.setVisible(true);
        ButtonPanel.setVisible(false);
        TicketButtonPanel.setVisible(false);

        Object[][] vedatatab = loadTicketDataFromDatabase();
        String[] cotve = {"ID vé", "ID phim", "ID phòng", "ID chỗ ngồi", "ID khách hàng", "Ngày đặt", "Trạng thái vé", "Giá"};
        Object[][] khdatatab = loadCustomerFromDatabase();
        String[] cotkh= {"ID", "Tên", "Số điện thoại", "Loại đối tượng"};
        Object[][] phimdatatab= loadMovieDataFromDatabase();
        String[] cotph = {"ID", "Tiêu đề", "Thể loại phim", "Thời lượng phim",  "Đạo diễn", "Ngày phát hành", "Miêu tả"};

        DefaultTableModel tblmodel1= new DefaultTableModel(khdatatab, cotkh);
        DefaultTableModel tblmodel2= new DefaultTableModel(phimdatatab, cotph);
        DefaultTableModel tblmodel3= new DefaultTableModel(vedatatab, cotve);
        DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();

        JTable t1= new JTable(tblmodel1);
        JTable t2= new JTable(tblmodel2);
        JTable t3= new JTable(tblmodel3);
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
        getContentPane().add(p2, BorderLayout.CENTER);

        KhachHangButton.addActionListener((ActionEvent e)-> {
            sp1.setVisible(true);
            sp2.setVisible(false);
            sp3.setVisible(false);
            CustomerButtonPanel.setVisible(true);
            ButtonPanel.setVisible(false);
            TicketButtonPanel.setVisible(false);
            ButtonPanelLayout.setVisible(true);
        });

        PhimButton.addActionListener((ActionEvent e)-> {
            sp1.setVisible(false);
            sp2.setVisible(true);
            sp3.setVisible(false);
            CustomerButtonPanel.setVisible(false);
            ButtonPanel.setVisible(true);
            TicketButtonPanel.setVisible(false);
            ButtonPanelLayout.setVisible(true);
        });

        VeButton.addActionListener(e -> {
            sp3.setVisible(true);
            sp2.setVisible(false);
            sp1.setVisible(false);
            CustomerButtonPanel.setVisible(false);
            ButtonPanel.setVisible(false);
            ButtonPanelLayout.setVisible(true);
            TicketButtonPanel.setVisible(true);
        });

        b1.addActionListener(e -> {
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

        vb2.addActionListener(e -> {
            String key = JOptionPane.showInputDialog(this, "Nhập ID vé muốn tìm" );
            if (key != null && !key.trim().isEmpty()) {
                try (Connection connection = DatabaseOperation.connectToDataBase()){
                    String sql = "Select * from Ticket INNER JOIN Customer ON Ticket.IDCustomer = Customer.IDCustomer INNER JOIN Movie ON Movie.IDMovie = Ticket.IDMovie INNER JOIN Seat ON Seat.IDSeat = Ticket.IDSeat INNER JOIN Room ON Room.IDRoom = Ticket.IDRoom WHERE IDTicket = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setInt(1, Integer.parseInt(key));
                        try (ResultSet rs = stmt.executeQuery()){
                            if (rs.next()) {
                            	StringBuilder result = new StringBuilder("Thông tin vé: \n");
                            	result.append(String.format("ID vé: %d\n", rs.getInt("IDTicket")))
                            	      .append(String.format("ID phim: %-18d Tên phim: %s\n", rs.getInt("IDMovie"), rs.getString("Title")))
                            	      .append(String.format("ID phòng: %-17d Tên phòng: %s\n", rs.getInt("IDRoom"), rs.getString("RoomName")))
                            	      .append(String.format("ID chỗ ngồi: %-11d Tên chỗ ngồi: %s\n", rs.getInt("IDSeat"), rs.getString("SeatName")))
                            	      .append(String.format("ID khách hàng: %-7d Tên khách hàng: %s\n", rs.getInt("IDCustomer"), rs.getString("CustomerName")))
                            	      .append(String.format("Ngày đặt: %s\n", rs.getDate("BookDate")))
                            	      .append(String.format("Trạng thái: %s\n", rs.getString("TicketStatus")))
                            	      .append(String.format("Giá: %,d đ\n", rs.getInt("Price")));

                                JOptionPane.showMessageDialog(this, result.toString());
                            } else {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy ID vé!");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        vb3.addActionListener(e -> {
            String key = JOptionPane.showInputDialog(this, "Nhập ID vé muốn xóa: ");
            if (key != null && !key.trim().isEmpty()) {
                try (Connection connection = DatabaseOperation.connectToDataBase()){
                    String sql = "Delete from Ticket where IDTicket = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setInt(1, Integer.parseInt(key));
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "Xóa thành công!");
                            UpdateTicketList(t3);
                        } else {
                            JOptionPane.showMessageDialog(this, "Xóa không thành công!");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xoá vé!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        vb4.addActionListener(e -> {
            String key = JOptionPane.showInputDialog(this, "Nhập ID vé muốn sửa: ");
            if (key != null && !key.trim().isEmpty()) {
                try (Connection connection = DatabaseOperation.connectToDataBase()){
                    String sql = "Select * from Ticket where IDTicket =?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setInt(1, Integer.parseInt(key));
                        try (ResultSet rs = stmt.executeQuery()){
                            if (rs.next()) {
                                int currentIDMovie = rs.getInt("IDMovie");
                                int currentIDRoom = rs.getInt("IDRoom");
                                int currentIDSeat = rs.getInt("IDSeat");
                                int currentIDCustomer = rs.getInt("IDCustomer");
                                Date currentBookDate = rs.getDate("BookDate");
                                String currentTicketStatus = rs.getString("TicketStatus");
                                double currentPrice = rs.getDouble("Price");

                                JTextField idMovie = new JTextField(String.valueOf(currentIDMovie));
                                JTextField idRoom = new JTextField(String.valueOf(currentIDRoom));
                                JTextField idSeat = new JTextField(String.valueOf(currentIDSeat));
                                JTextField idCustomer = new JTextField(String.valueOf(currentIDCustomer));
                                JSpinner bookDate = new JSpinner(new SpinnerDateModel(currentBookDate, null, null, java.util.Calendar.DAY_OF_MONTH));
                                bookDate.setEditor(new JSpinner.DateEditor(bookDate, "yyyy-MM-dd"));
                                JTextField ticketStatus = new JTextField(currentTicketStatus);
                                JTextField price = new JTextField(String.valueOf(currentPrice));

                                Object[] inputFields = {
                                        "ID Phim:", idMovie,
                                        "ID Phòng:", idRoom,
                                        "ID Chỗ Ngồi:", idSeat,
                                        "ID Khách Hàng:", idCustomer,
                                        "Ngày Đặt Vé:", bookDate,
                                        "Trạng Thái Vé:", ticketStatus,
                                        "Giá Vé:", price
                                };

                                int option = JOptionPane.showConfirmDialog(this, inputFields, "Sua thong tin ve: ", JOptionPane.OK_CANCEL_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    String UpdateSql = "Update Ticket set IDMovie = ?, IDRoom = ?, IDSeat = ?, IDCustomer = ?, BookDate = ?, TicketStatus = ?, Price = ? where IDTicket =?";
                                    try (PreparedStatement stmt2 = connection.prepareStatement(UpdateSql)){
                                        stmt2.setInt(1, Integer.parseInt(idMovie.getText()));
                                        stmt2.setInt(2, Integer.parseInt(idRoom.getText()));
                                        stmt2.setInt(3, Integer.parseInt(idSeat.getText()));
                                        stmt2.setInt(4, Integer.parseInt(idCustomer.getText()));
                                        java.util.Date date = (java.util.Date) bookDate.getValue();
                                        stmt2.setDate(5, new java.sql.Date(date.getTime()));
                                        stmt2.setString(6, ticketStatus.getText());
                                        stmt2.setDouble(7, Double.parseDouble(price.getText()));
                                        stmt2.setInt(8, Integer.parseInt(key));
                                        int rowsAffected = stmt2.executeUpdate();
                                        if (rowsAffected > 0) {
                                            JOptionPane.showMessageDialog(this, "Sửa vé thành công!");
                                            UpdateTicketList(t3); // Cập nhật lại danh sách vé
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Không tìm thấy vé để sửa!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Loi khi sua ve", "Loi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ExitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thoát chứ?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                dispose();
                new Login();
            }

        });
        TimVaSuaThongTinKhach.addActionListener(e -> {
        	String key = JOptionPane.showInputDialog(this, "Nhập ID khách hàng muốn tìm & sửa: ");
        	if (key != null && !key.trim().isEmpty()) {
        		String query = "SELECT * FROM CUSTOMER WHERE IDCustomer = ?";
        		try (Connection connection = DatabaseOperation.connectToDataBase();
        			 PreparedStatement preparedStatement = connection.prepareStatement(query)){
        				preparedStatement.setInt(1, Integer.parseInt(key));
        				ResultSet resultSet = preparedStatement.executeQuery();
        				if (resultSet.next()) {
        						 int currentCustomerID = resultSet.getInt("IDCustomer");
        						 String currentCustomerName = resultSet.getString("CustomerName");
        						 String currentCustomerPhoneNumber = resultSet.getString("CustomerPhoneNumber");
        						 String currentCustomerType = resultSet.getString("CustomerType");
        					
        					
                                 JTextField idCustomer = new JTextField(String.valueOf(currentCustomerID));
                                 idCustomer.setEditable(false);
                                 JTextField CustomerName = new JTextField(String.valueOf(currentCustomerName));
                                 JTextField customerPhoneNumber = new JTextField(String.valueOf(currentCustomerPhoneNumber));
                                 customerPhoneNumber.setEditable(false);
                                 
                                 JFrame frame = new JFrame("Thông tin khách hàng");
                                 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                 frame.setLocationRelativeTo(this);
                                 frame.setSize(325, 250);
                                 frame.getContentPane().setLayout(null);
                                 
                                 JLabel idCustomerLabel = new JLabel("ID khách hàng:");
                                 idCustomerLabel.setBounds(10, 10, 150, 20);
                                 idCustomer.setBounds(150, 10, 150, 20);

                                 JLabel customerNameLabel = new JLabel("Tên khách hàng:");
                                 customerNameLabel.setBounds(10, 50, 150, 20);
                                 CustomerName.setBounds(150, 50, 150, 20);

                                 JLabel customerPhoneNumberLabel = new JLabel("Số điện thoại:");
                                 customerPhoneNumberLabel.setBounds(10, 90, 150, 20);
                                 customerPhoneNumber.setBounds(150, 90, 150, 20);

                                 JLabel customerTypeLabel = new JLabel("Loại khách hàng:");
                                 customerTypeLabel.setBounds(10, 130, 150, 20);
                                 String[] customerTypes = {"Nguoi lon", "Tre em"};
                                 JComboBox<String> customerTypeComboBox = new JComboBox<>(customerTypes);
                                 customerTypeComboBox.setSelectedItem(currentCustomerType);
                                 customerTypeComboBox.setBounds(150, 130, 150, 20);
                                 
                                 JButton ButtonXacNhan = new JButton("Thay đổi");
                                 ButtonXacNhan.setBounds(100, 170, 100, 20);
                                 
                                 frame.getContentPane().add(ButtonXacNhan);
                                 frame.getContentPane().add(idCustomerLabel);
                                 frame.getContentPane().add(idCustomer);
                                 frame.getContentPane().add(customerNameLabel);
                                 frame.getContentPane().add(CustomerName);
                                 frame.getContentPane().add(customerPhoneNumberLabel);
                                 frame.getContentPane().add(customerPhoneNumber);
                                 frame.getContentPane().add(customerTypeLabel);
                                 frame.getContentPane().add(customerTypeComboBox);
                                 frame.setVisible(true);
                                 
                                 ButtonXacNhan.addActionListener(ex -> {
                                	 int result = JOptionPane.showConfirmDialog(frame, "Xác nhận sửa thông tin?", "xác nhận", JOptionPane.OK_CANCEL_OPTION);
                                	 if (result == JOptionPane.OK_OPTION) {
                                		 String UpdateSql = "UPDATE Customer SET CustomerName = ?, CustomerType = ? WHERE IDCustomer = ?";
                                		 try (Connection connectionToUpdate = DatabaseOperation.connectToDataBase();
                                				 PreparedStatement preparedStatementUpdate = connectionToUpdate.prepareStatement(UpdateSql)){
                                			 preparedStatementUpdate.setString(1, CustomerName.getText());
                                			 preparedStatementUpdate.setString(2, (String)customerTypeComboBox.getSelectedItem());
                                			 preparedStatementUpdate.setInt(3, currentCustomerID);
                                			 
                                			 int rowAffected = preparedStatementUpdate.executeUpdate();
                                			 if (rowAffected > 0) {
                                				 JOptionPane.showMessageDialog(frame, "Sửa thông tin khách thành công.");
                                				 UpdateCustomerList(t1);
                                               }
                                		 } catch (SQLException sqlex) {
                                 			JOptionPane.showMessageDialog(this, "Database Error: " + sqlex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                                		 }
                                		 
                                	 }
                                 });
        				}
        				else {
        					JOptionPane.showMessageDialog(this, "Không tìm thấy ID khách.", "Error", JOptionPane.ERROR_MESSAGE);
        				}
        		} catch (SQLException sqlex) {
        			JOptionPane.showMessageDialog(this, "Database Error: " + sqlex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
    }
}
