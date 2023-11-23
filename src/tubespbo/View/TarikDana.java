package tubespbo.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;

public class TarikDana {

    public TarikDana(int id) {
        Controller con = new Controller();
        Driver currDriver = con.getDriverByID(id).get(0);

        Font headerFont = new Font("Courier", Font.BOLD, 24);
        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 16);
        Font font4 = new Font("Courier", Font.BOLD, 14);
        Font fontBack = new Font("Courier", Font.BOLD, 12);
        
        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = new JLabel("Dompet " + currDriver.getUser_name() + ".");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);
        f.add(intro);
        
        JLabel intro2 = new JLabel("Kalau dompet tipis jangan nangis yah!");
        intro2.setFont(font2);
        intro2.setBounds(30, 90, 300, 30);
        f.add(intro2);
        
        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");

        lineDiv.setBounds(10, 110, 460, 20);
        f.add(lineDiv);

        JPanel frameWallet = new JPanel(null);
        frameWallet.setSize(400, 100);
        frameWallet.setBounds(15, 60, 400, 100);

        JLabel wallet = new JLabel("Saldo:");
        wallet.setFont(font);
        wallet.setBounds(30, 150, 800, 30);
        wallet.setBackground(null);
        f.add(wallet);
        
        JLabel saldo = new JLabel("Rp.  "  + con.getWallet(id));
        saldo.setHorizontalAlignment(SwingConstants.RIGHT);
        saldo.setFont(font);
        saldo.setBounds(250, 150, 200, 30);
        saldo.setBackground(null);
        f.add(saldo);
        
        JButton backButton = new JButton("Kembali");
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(e -> {
            new MainMenuDriver(id);
            f.dispose();
        });
        f.add(backButton);

        JLabel labelJumlahTarik = new JLabel("Jumlah Tarik: ");
        labelJumlahTarik.setFont(font2);
        labelJumlahTarik.setBounds(30, 170, 250, 85);
        f.add(labelJumlahTarik);

        JTextField jumlahTarikField = new JTextField("0");
        jumlahTarikField.setBackground(Color.WHITE);
        jumlahTarikField.setFont(font3);
        jumlahTarikField.setBounds(30, 240, 420, 40);
        f.add(jumlahTarikField);

        JButton tarikButton = new JButton("Tarik");
        tarikButton.setBounds(40, 490, 400, 30);
        tarikButton.addActionListener(e -> {
            String nominal = jumlahTarikField.getText();
            try {
                float nominalTarik = Float.parseFloat(nominal);
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
                            new Object[]{"Ya", "Tidak"},
                            "Ya");

                    if (result == JOptionPane.YES_OPTION) {
                        con.updateJoPay(id, con.getWallet(id) - nominalTarik);
                        JOptionPane.showMessageDialog(f, "Penarikan berhasil dilakukan!", "WARNING",
                                JOptionPane.DEFAULT_OPTION);
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

        f.setSize(500, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }


}
