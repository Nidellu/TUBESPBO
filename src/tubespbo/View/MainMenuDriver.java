/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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

public class MainMenuDriver {

    public MainMenuDriver(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        ArrayList<User> listUser; // ini buat apa

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String nameDisplay = con.getUsername(id);

        JLabel intro = new JLabel("Selamat Datang di Josen " + nameDisplay + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Mau antar penumpang kemana hari ini ges?");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 400, 30);
        intro2.setBounds(10, 30, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 50, 500, 20);

        JButton profileButton = new JButton("Check Profil");
        profileButton.setFont(fontButton);
        profileButton.setBounds(10, 80, 470, 30);
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new DriverProfile(id);
            }
        });

        double walletDisplay = con.getWallet(id);
        JLabel border = new JLabel();
        border.setBorder(BorderFactory.createLineBorder(Color.black));
        border.setBounds(30, 135, 425, 60);

        JLabel wallet = new JLabel("JOPAY: Rp. " + Double.toString(walletDisplay));
        wallet.setFont(font2);
        wallet.setBounds(50, 150, 400, 30);

        JButton topUp = new JButton("Top Up");
        topUp.setFont(fontButton);
        topUp.setBounds(340, 150, 100, 30);
        topUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
//                
            }
        });
        JButton historyOrder = new JButton("Riwayat Pesanan");
        historyOrder.setFont(fontButton);
        historyOrder.setBounds(70, 270, 350, 30);
        historyOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                // new historyOrder(id);
            }
        });

//         JButton takeOrder = new JButton("Take/Cancel Order");
//         takeOrder.setFont(fontButton);
//         takeOrder.setBounds(70, 220, 350, 30);
//         takeOrder.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
// //                
//             }
//         });

        JButton inbox = new JButton("Inbox");
        inbox.setFont(fontButton);
        inbox.setBounds(70, 220, 350, 30);
        inbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                
            }
        });


        JButton logOut = new JButton("Log out");
        logOut.setFont(fontButton);
        logOut.setBounds(340, 500, 100, 30);
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new StartMenu();
            }
        });

        f.add((intro));
        f.add((intro2));
        f.add(border);
        f.add(wallet);
        f.add(topUp);
        f.add(profileButton);
        f.add(lineDiv);

        f.add(inbox);
        f.add(historyOrder);
        f.add(logOut);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }



    public static void main(String[] args) {
        new MainMenuDriver(11);
    }

}
