package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JScrollBar;

public class DatVe extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final boolean False = false;
	private JPanel contentPane;
	private String customerID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatVe frame = new DatVe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DatVe(String customerID) {
		this.customerID = customerID;
	}
	
	
	public DatVe() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Now Showing");
		lblNewLabel.setBounds(300, 22, 203, 30);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		contentPane.add(lblNewLabel);
		
		ImageIcon originalIcon = new ImageIcon("D:\\MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_FMjpg_UX1000_.jpg");
        int imageWidth = 250; // Desired image width
        int imageHeight = 300; // Desired image height
        ImageIcon resizedIcon = resizeImage(originalIcon, imageWidth, imageHeight);
        
		
		ImageIcon originalIcon1 = new ImageIcon("D:\\riot-chi-6-nghin-ty-lam-arcane.jpg");
        int imageWidth1 = 250; // Desired image width
        int imageHeight1 = 300; // Desired image height
        ImageIcon resizedIcon1 = resizeImage1(originalIcon1, imageWidth1, imageHeight1);
        
        
        ImageIcon originalIcon2 = new ImageIcon("D:\\Áp_phích_chính_thức_Lật_mặt_7.jpg");
        int imageWidth2 = 250; // Desired image width
        int imageHeight2 = 300; // Desired image height
        ImageIcon resizedIcon2 = resizeImage2(originalIcon2, imageWidth2, imageHeight2);
        
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 72, 836, 600);
        contentPane.add(panel);
        panel.setLayout(null);
        // Display resized image
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(10, 5, 250, 300);
        panel.add(imageLabel);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        JLabel imageLabel1 = new JLabel(resizedIcon1);
        imageLabel1.setBounds(558, 5, 250, 300);
        panel.add(imageLabel1);
        imageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        imageLabel2.setBounds(284, 5, 250, 300);
        panel.add(imageLabel2);
        imageLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnNewButton = new JButton("Avengers : Endgame");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(10, 315, 250, 47);
        btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AvengerInfo(customerID);
				new AvengerInfo().setVisible(true);
			}

			
			
        });
        panel.add(btnNewButton);
        
        JButton btnLtMt = new JButton("Lật mặt 7");
        btnLtMt.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLtMt.setBounds(284, 315, 250, 47);
        panel.add(btnLtMt);
        btnLtMt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LatMatInfo(customerID);
				new LatMatInfo().setVisible(true);
				
			}

			
			
        });
        
        JButton btnArcane = new JButton("Arcane");
        btnArcane.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnArcane.setBounds(558, 315, 250, 47);
        panel.add(btnArcane);
        btnArcane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ArcaneInfo(customerID);
				new ArcaneInfo().setVisible(true);
				
			}
		
			
			
        });
        JButton btnCancel = new JButton("Đăng xuất");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCancel.setBounds(304, 430, 200, 40);
        panel.add(btnCancel);
        btnCancel.addActionListener(e -> {
        	int reply = JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        	if (reply == JOptionPane.OK_OPTION) {
        		dispose();
        		new Login().setVisible(true);
        	}
        });
        
    }

    private ImageIcon resizeImage(ImageIcon originalIcon, int width, int height) {
        return new ImageIcon(originalIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }
    
    
       private ImageIcon resizeImage1(ImageIcon originalIcon1, int width1, int height1) {
            return new ImageIcon(originalIcon1.getImage().getScaledInstance(width1, height1, java.awt.Image.SCALE_SMOOTH));
	}
       
       private ImageIcon resizeImage2(ImageIcon originalIcon2, int width2, int height2) {
           return new ImageIcon(originalIcon2.getImage().getScaledInstance(width2, height2, java.awt.Image.SCALE_SMOOTH));
	}
}

