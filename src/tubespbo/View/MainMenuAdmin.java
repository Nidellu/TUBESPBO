/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tubespbo.Controller.Controller;
import tubespbo.Model.User;

public class MainMenuAdmin {

    public MainMenuAdmin(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        ArrayList<User> listUser;

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel intro = new JLabel("Selamat Datang Admin.");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Jangan Menyerah, tetap Semangat!");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 400, 30);
        intro2.setBounds(10, 30, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 50, 500, 20);
        
        JButton logOut = new JButton("Log out");
        logOut.setFont(fontButton);
        logOut.setBounds(340, 500, 100, 30);
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new StartMenu();
            }
        });
        
        JButton verifDriver = new JButton("Verifikasi Driver");
        verifDriver.setFont(fontButton);
        verifDriver.setBounds(60, 215, 350, 30);
        verifDriver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        
        JButton checkIncome = new JButton("Cek Pendapatan");
        checkIncome.setFont(fontButton);
        checkIncome.setBounds(60, 255, 350, 30);
        checkIncome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        
    // button buat nambah promo
        JButton addPromoBtn = new JButton("Add New Promo");
        addPromoBtn.setBounds(60, 295, 350, 30);
        addPromoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new AddPromo(id);
            }
        });
    
    //button buat liat daftar promo
        JButton seePromoBtn = new JButton("See All Promo");
        seePromoBtn.setBounds(60, 335, 350, 30);
        seePromoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new SeeAndDeletePromo(id);
            }
        });

        f.add(intro);
        f.add(intro2);
        f.add(lineDiv);
        f.add(logOut);
        f.add(verifDriver);
        f.add(checkIncome);
        f.add(addPromoBtn);
        f.add(seePromoBtn);
        
        f.setLocationRelativeTo(null);
        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    // public static void main(String[] args) {
    //     new MainMenuAdmin(1);
    // }
}
