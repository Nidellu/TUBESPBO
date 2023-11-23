package tubespbo.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import tubespbo.Controller.Controller;
import tubespbo.Model.User;

public class MenuWalletPassanger {

    public MenuWalletPassanger(int id) {
        Controller con = new Controller();
        User currUser = con.getPassangerByID(id).get(0);
        System.out.println();
        Font headerFont = new Font("ARIAL", Font.BOLD, 24);

        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 650);

        JLabel intro = new JLabel(currUser.getUser_name() + "'s Wallet!");
        intro.setFont(new Font("Courier", Font.BOLD, 24));
        intro.setBounds(10, 10, 500, 40);
        f.add(intro);

        JPanel frameWallet = new JPanel(null);
        frameWallet.setSize(400,100);
        frameWallet.setBorder(new LineBorder(Color.GRAY, 5));
        frameWallet.setBackground(null);
        frameWallet.setBounds(45, 60, 400, 100);

        JLabel saldoText = new JLabel("SALDO: ");
        saldoText.setFont(headerFont);
        saldoText.setBounds(10,10, 200, 85);
        frameWallet.add(saldoText);
        // frameWallet.setBorder();

        JLabel topUpText = new JLabel("TAMBAH SALDO: ");
        topUpText.setFont(headerFont);
        topUpText.setBounds(150,200, 200, 85);
        frameWallet.add(topUpText);
        // frameWallet.setBorder();

        JButton backButton = new JButton("Kembali");
        backButton.setBounds(30, 550, 100, 25);
        backButton.addActionListener(e -> {
            new MainMenuPassanger(id);
            f.dispose();
        });
        f.add(backButton);

        JTextField jumlahSaldoField = new JTextField("Rp " + String.valueOf(currUser.getUser_wallet()));
        // JTextField jumlahSaldoField = new JTextField("Rp " + "9,999,999.0");
        jumlahSaldoField.setBorder(null);
        jumlahSaldoField.setEditable(false);
        jumlahSaldoField.setBackground(null);
        jumlahSaldoField.setFont(headerFont);
        jumlahSaldoField.setBounds(220,10, 170, 85);
        frameWallet.add(jumlahSaldoField);

        f.add(frameWallet);

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuWalletPassanger(8);
    }

}
