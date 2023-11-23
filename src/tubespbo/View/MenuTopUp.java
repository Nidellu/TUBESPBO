package tubespbo.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import tubespbo.Controller.Controller;
import tubespbo.Model.User;

public class MenuTopUp {

    public MenuTopUp(int id) {
        Controller con = new Controller();
        User currUser = con.getUserByID(id);
        System.out.println();
        Font headerFont = new Font("ARIAL", Font.BOLD, 24);

        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 450);

        JLabel intro = new JLabel(currUser.getUser_name() + "'s Wallet!");
        intro.setFont(new Font("Courier", Font.BOLD, 24));
        intro.setBounds(10, 10, 500, 40);
        f.add(intro);

        JPanel frameWallet = new JPanel(null);
        frameWallet.setSize(400, 100);
        frameWallet.setBorder(new LineBorder(Color.GRAY, 5));
        frameWallet.setBackground(null);
        frameWallet.setBounds(45, 60, 400, 100);

        JLabel saldoText = new JLabel("SALDO: ");
        saldoText.setFont(headerFont);
        saldoText.setBounds(10, 10, 200, 85);
        frameWallet.add(saldoText);
        // frameWallet.setBorder();

        JButton backButton = new JButton("Kembali");
        backButton.setBounds(30, 370, 100, 25);
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
        jumlahSaldoField.setBounds(220, 10, 170, 85);
        frameWallet.add(jumlahSaldoField);
        f.add(frameWallet);

        JLabel topUpText = new JLabel("TAMBAH SALDO: RP");
        topUpText.setFont(headerFont);
        topUpText.setBounds(25, 200, 250, 85);
        f.add(topUpText);

        JTextField topUpField = new JTextField(20);
        topUpField.setFont(new Font("Courier", Font.BOLD, 20));
        topUpField.setBounds(270, 220, 200, 40);
        topUpField.setBorder(new LineBorder(Color.GRAY, 4));
        topUpField.setBackground(null);
        f.add(topUpField);

        JButton addTopUp = new JButton("TOP UP");
        addTopUp.setBounds(280, 270, 100, 25);
        addTopUp.addActionListener(e -> {
            String inputField = topUpField.getText();
            if (inputField.isBlank()) {
                JOptionPane.showMessageDialog(f, "Masukan Dana sebelum Top Up", "Top Up Jo-Pay",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double saldoTambahan = Double.parseDouble(inputField);
                    double currSaldo = con.getWallet(id);
                    if (saldoTambahan < 2000) {
                        JOptionPane.showMessageDialog(f, "Minimal Top Up saldo adalah 2000", "WARNING",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int result = JOptionPane.showOptionDialog(
                                f,
                                "Anda yakin ingin menambahkan saldo?",
                                "Konfirmasi Top Up",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new Object[] { "Ya", "Tidak" },
                                "Ya");

                        if (result == JOptionPane.YES_OPTION) {
                            con.updateJoPay(id, currSaldo + saldoTambahan);
                            JOptionPane.showMessageDialog(f, "Top Up berhasil dilakukan!", "WARNING",
                                    JOptionPane.DEFAULT_OPTION);
                            if (currUser.getUser_role().equalsIgnoreCase("DRIVER")) {
                                new MainMenuDriver(id);
                            } else if (currUser.getUser_role().equalsIgnoreCase("PASSANGER")) {
                                new MainMenuPassanger(id);
                            }
                            f.dispose();
                        } else {
                            JOptionPane.showMessageDialog(f, "Top Up saldo telah dibatalkan", "",
                                    JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(f, "Pastikan Nominal yang anda masukan valid!", "Top Up",
                            JOptionPane.ERROR_MESSAGE);
                    topUpField.setText(null);
                    e1.printStackTrace();
                }
            }
        });
        f.add(addTopUp);

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // public static void main(String[] args) {
    // new MenuWalletPassanger(5);
    // }

}
