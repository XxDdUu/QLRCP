package ui;
import model.Phim;
import model.ThanhVien;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public final class NhanVienUI extends JFrame {
    public NhanVienUI() {
        super("Nhân Viên Quản Lí");
        initUI();
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
        ArrayList<ThanhVien> ThanhViendata= new ArrayList<>();
        ThanhViendata.add(new ThanhVien(1, "Bach", "bachnd2006@outlook.com", "Nguoi Lon"));
        ThanhViendata.add(new ThanhVien(2, "Duy", "Duy2006.24ai@vku.udn.vn", "Nguoi Lon"));
        ArrayList<Phim> phimdata= new ArrayList<>();
        phimdata.add(new Phim(1, "Avenger: Endgame", "Hanh Dong", 121, Year.parse("2002"), "Thomas", "bối cảnh vũ trụ League of Legends, kể về \n" +
                "cuộc xung đột giữa hai thành phố Piltover \n" +
                "và Zaun, cùng hành trình bi kịch của hai chị \n" +
                "em Vi và Jinx. Bộ phim khai thác sâu về công \n" +
                "nghệ Hextech, mối quan hệ gia đình, và sự \n" +
                "đối đầu giữa lý tưởng và tham vọng."));
        phimdata.add(new Phim(2, "3", "Gia Tuong", 122, Year.parse("2002"), "Andree", "ádasdwasdwasd"));
        Object[][] khdatatab= new Object[ThanhViendata.size()][4];
        Object[][] phimdatatab= new Object[phimdata.size()][7];
        for (int i=0; i<ThanhViendata.size(); i++) {
            khdatatab[i][0]=ThanhViendata.get(i).getMATv();
            khdatatab[i][1]=ThanhViendata.get(i).getHoTen();
            khdatatab[i][2]=ThanhViendata.get(i).getemail();
            khdatatab[i][3]=ThanhViendata.get(i).getLoaiDoiTuong();
        }
        for (int i=0; i<phimdata.size(); i++) {
            phimdatatab[i][0]=phimdata.get(i).getMaP();
            phimdatatab[i][1]=phimdata.get(i).getTenP();
            phimdatatab[i][2]=phimdata.get(i).getTheloai();
            phimdatatab[i][3]=phimdata.get(i).getThoiluong();
            phimdatatab[i][4]=phimdata.get(i).getDaodien();
            phimdatatab[i][5]=phimdata.get(i).getNamSX();
            phimdatatab[i][6]=phimdata.get(i).getMota();
        }
        String[] cotkh= {"ID", "Tên", "Email", "Loại khách"};
        String[] cotph= {"ID", "Tiêu đề", "Thể loại phim", "Thời hạn phim",  "Ngày phát hành", "Đạo diễn", "Miêu tả"};
        JTable t1= new JTable(khdatatab, cotkh);
        JTable t2= new JTable(phimdatatab, cotph);
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
        });
        b2.addActionListener((ActionEvent e)-> {
            sp1.setVisible(false);
            sp2.setVisible(true);
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
