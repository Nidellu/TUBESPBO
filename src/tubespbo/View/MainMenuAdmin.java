<<<<<<< HEAD
package tubespbo.View;

import javax.swing.*;
import java.awt.*;

public class MainMenuAdmin {

    private final JFrame frame;

    public MainMenuAdmin() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        displayWelcomeMessage();
        addButtons();
        frame.setVisible(true);
    }

    private void displayWelcomeMessage() {
        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 16);

        JLabel intro = FrameHandler.createLabel("Selamat Datang Admin.", heading1, 10, 10, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Jangan Menyerah, tetap Semangat!", text1, 10, 30, 300, 30);
        frame.add(intro);
        frame.add(intro2);
    }

    private void addButtons() {
        Font buttonFont = new Font("Courier", Font.BOLD, 12);

        JButton logOutButton = FrameHandler.createButton("Log out", buttonFont, 340, 500, 100, 30, e -> onLogout());
        JButton verifButton = FrameHandler.createButton("Verifikasi Driver", buttonFont, 60, 195, 350, 30, e -> openVerificationDriverScreen());
        JButton findOrderButton = FrameHandler.createButton("Find Order", buttonFont, 60, 235, 350, 30, e -> openFindOrderScreen());
        JButton checkIncomeButton = FrameHandler.createButton("Cek Pendapatan", buttonFont, 60, 275, 350, 30, e -> openCheckIncomeScreen());
        JButton addPromoButton = FrameHandler.createButton("Tambah Promo", buttonFont, 60, 315, 350, 30, e -> openAddPromoScreen());
        JButton seePromoButton = FrameHandler.createButton("Lihat Semua Promo", buttonFont, 60, 355, 350, 30, e -> openSeePromoScreen());

        frame.add(logOutButton);
        frame.add(verifButton);
        frame.add(findOrderButton);
        frame.add(checkIncomeButton);
        frame.add(addPromoButton);
        frame.add(seePromoButton);
    }

    private void onLogout() {
        frame.dispose();
        new StartMenu();
    }

    private void openVerificationDriverScreen() {
        frame.dispose();
        new ListVerifikasiDriver();
    }

    private void openFindOrderScreen() {
        frame.dispose();
        new FindOrder();
    }

    private void openCheckIncomeScreen() {
        frame.dispose();
        new CekPendapatanAdmin();
    }

    private void openAddPromoScreen() {
        frame.dispose();
        new AddPromo();
    }

    private void openSeePromoScreen() {
        frame.dispose();
        new SeeAndDeletePromo();
    }
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainMenuAdmin {

    public MainMenuAdmin() {
        showDataScreen();
    }

    private void showDataScreen() {

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
       
    // button buat verif driver yang bari regis
        JButton verifDriver = new JButton("Verifikasi Driver");
        verifDriver.setFont(fontButton);
        verifDriver.setBounds(60, 195, 350, 30);
        verifDriver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new ListVerifikasiDriver();
            }
        });
        
    // button buat cari order
        JButton findOrder = new JButton("Find Order");
        findOrder.setBounds(60, 235, 350, 30);
        findOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new FindOrder();
            }
        });
        
    //button buat check income
        JButton checkIncome = new JButton("Cek Pendapatan");
        checkIncome.setFont(fontButton);
        checkIncome.setBounds(60, 275, 350, 30);
        checkIncome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new CekPendapatanAdmin();

            }
        });
        
    // button buat nambah promo
        JButton addPromoBtn = new JButton("Tambah Promo");
        addPromoBtn.setBounds(60, 315, 350, 30);
        addPromoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new AddPromo();
            }
        });
    
    //button buat liat daftar promo
        JButton seePromoBtn = new JButton("Lihat Semua Promo");
        seePromoBtn.setBounds(60, 355, 350, 30);
        seePromoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new SeeAndDeletePromo();
            }
        });
      
        f.add(intro);
        f.add(intro2);
        f.add(lineDiv);
        f.add(logOut);
        f.add(verifDriver);
        f.add(findOrder);
        f.add(checkIncome);
        f.add(addPromoBtn);
        f.add(seePromoBtn);
        
        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
>>>>>>> master
