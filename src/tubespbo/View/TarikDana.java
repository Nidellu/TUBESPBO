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
import tubespbo.Model.Driver;

public class TarikDana {

    public TarikDana(int id) {
        Controller con = new Controller();
        Driver currDriver = con.getDriverByID(id).get(0);

        Font headerFont = new Font("ARIAL", Font.BOLD, 24);
        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = new JLabel(currDriver.getUser_name() + "'s Wallet!");
        intro.setFont(new Font("Courier", Font.BOLD, 24));
        intro.setBounds(10, 10, 500, 40);
        f.add(intro);

        JPanel frameWallet = new JPanel(null);
        frameWallet.setSize(400, 100);
        frameWallet.setBorder(new LineBorder(Color.GRAY, 5));
        frameWallet.setBackground(null);
        frameWallet.setBounds(15, 60, 400, 100);

        JLabel saldoText = new JLabel("SALDO: ");
        saldoText.setFont(headerFont);
        saldoText.setBounds(10, 10, 200, 85);
        frameWallet.add(saldoText);
        // frameWallet.setBorder();

        JButton backButton = new JButton("Kembali");
        backButton.setBounds(30, 370, 100, 25);
        backButton.addActionListener(e -> {
            new MainMenuDriver(id);
            f.dispose();
        });
        f.add(backButton);

        JTextField jumlahSaldoField = new JTextField("Rp " + con.getWallet(id));
        // JTextField jumlahSaldoField = new JTextField("Rp " + "9,999,999.0");
        jumlahSaldoField.setBorder(null);
        jumlahSaldoField.setEditable(false);
        jumlahSaldoField.setBackground(null);
        jumlahSaldoField.setFont(headerFont);
        jumlahSaldoField.setBounds(180, 10, 200, 85);
        frameWallet.add(jumlahSaldoField);
        f.add(frameWallet);

        JLabel labelJumlahTarik = new JLabel("JUMLAH TARIK: -RP");
        labelJumlahTarik.setFont(headerFont);
        labelJumlahTarik.setBounds(25, 200, 250, 85);
        f.add(labelJumlahTarik);

        JTextField jumlahTarikField = new JTextField(20);
        Font redFont = new Font("Courier", Font.BOLD, 20);
        jumlahTarikField.setFont(redFont);
        jumlahTarikField.setForeground(Color.RED);
        jumlahTarikField.setBounds(270, 220, 150, 40);
        jumlahTarikField.setBorder(new LineBorder(Color.GRAY, 4));
        jumlahTarikField.setBackground(null);
        f.add(jumlahTarikField);

        JButton tarikButton = new JButton("TARIK");
        tarikButton.setBounds(280, 270, 100, 25);
        tarikButton.addActionListener(e -> {
            String nominal = jumlahTarikField.getText();
            try {
                double nominalTarik = Double.parseDouble(nominal);
                if (nominalTarik < 10000) {
                    JOptionPane.showMessageDialog(f, "Minimal penarikan adalah Rp 10000", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                } else if (nominalTarik > con.getWallet(id)) {
                    JOptionPane.showMessageDialog(f, "Saldo anda tidak mencukupi", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    int result = JOptionPane.showOptionDialog(
                            f,
                            "Anda yakin ingin menarik saldo?",
                            "Konfirmasi Penarikan Saldo",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "Ya", "Tidak" },
                            "Ya");

                    if (result == JOptionPane.YES_OPTION) {
                        if (con.updateJoPay(id, con.getWallet(id) - nominalTarik)) {
                            JOptionPane.showMessageDialog(f, "Penarikan berhasil dilakukan!", "",
                                    JOptionPane.DEFAULT_OPTION);
                        } else {
                            JOptionPane.showMessageDialog(f, "Penarikan gagal dilakukan!", "WARNING",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        new MainMenuDriver(id);
                        f.dispose();
                    } else {
                        JOptionPane.showMessageDialog(f, "Penarikan saldo telah dibatalkan", "",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(f, "Pastikan nominal yang anda masukan Valid", "WARNING",
                        JOptionPane.WARNING_MESSAGE);
                e1.printStackTrace();
            }
        });
        f.add(tarikButton);

        f.setSize(450, 500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // public static void main(String[] args) {
    // new TarikDana(9);
    // }

}
