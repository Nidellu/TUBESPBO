<<<<<<< HEAD
package tubespbo.View;

import javax.swing.*;
import java.awt.*;

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;

public class TarikDana {

    public TarikDana(Driver driver) {
        createWithdrawalUI(driver);
    }

    private void createWithdrawalUI(Driver driver) {
        JFrame frame = new JFrame();
        frame.setTitle("Tarik Dana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 20);
        Font text2 = new Font("Courier", Font.PLAIN, 16);
        Font text3 = new Font("Courier", Font.PLAIN, 14);
        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel intro = FrameHandler.createLabel("Dompet " + driver.getUser_name() + ".", heading1, 30, 70, 400, 30);
        frame.add(intro);

        JLabel intro2 = FrameHandler.createLabel("Kalau dompet tipis jangan nangis yah!", text3, 30, 90, 300, 30);
        frame.add(intro2);

        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 110, 460, 20);
        frame.add(lineDiv);

        JLabel walletLabel = FrameHandler.createLabel("Saldo:", heading1, 30, 150, 800, 30);
        frame.add(walletLabel);

        JLabel saldoLabel = FrameHandler.createLabel("Rp. " + getFormattedSaldo(driver.getDriver_id()), text1, 250, 150, 200, 30);
        saldoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(saldoLabel);

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 85, 30,
                (e -> {
                    frame.dispose();
                    new MainMenuDriver(driver.getDriver_id());
                })
        );

        frame.add(backButton);

        JLabel labelJumlahTarik = FrameHandler.createLabel("Jumlah Tarik: ", text2, 30, 170, 250, 85);
        frame.add(labelJumlahTarik);

        JTextField jumlahTarikField = FrameHandler.createTextField("0", Color.WHITE, text2, 30, 240, 420, 40);
        frame.add(jumlahTarikField);

        JButton tarikButton = FrameHandler.createButton("Tarik", fontButton, 40, 490, 400, 30, e -> handleWithdrawal(driver, jumlahTarikField.getText(), frame));
        frame.add(tarikButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String getFormattedSaldo(int driverId) {
        float saldo = Controller.getInstance().getWallet(driverId);
        return saldo > 9999999 ? "9999999+" : String.valueOf(saldo);
    }

    private void handleWithdrawal(Driver driver, String amountStr, JFrame frame) {
        try {
            float amount = Float.parseFloat(amountStr);
            if (amount < 10000) {
                FrameHandler.showWarningMessage("Minimal penarikan adalah Rp 10000", "WARNING");
            } else if (amount > Controller.getInstance().getWallet(driver.getDriver_id())) {
                FrameHandler.showWarningMessage("Saldo anda tidak mencukupi", "WARNING");
            } else {
                int result = FrameHandler.showConfirmationDialog("Anda yakin ingin menarik saldo?", "Konfirmasi Penarikan Saldo");
                if (result == JOptionPane.YES_OPTION) {
                    if (Controller.getInstance().updateJoPay(driver.getDriver_id(), Controller.getInstance().getWallet(driver.getDriver_id()) - amount)) {
                        FrameHandler.showMessageDialog("Penarikan berhasil dilakukan!", "");
                    } else {
                        FrameHandler.showWarningMessage("Penarikan gagal dilakukan!", "WARNING");
                    }
                    new MainMenuDriver(driver.getDriver_id());
                    frame.dispose();
                } else {
                    FrameHandler.showMessageDialog("Penarikan saldo telah dibatalkan", "");
                }
            }
        } catch (NumberFormatException e) {
            FrameHandler.showWarningMessage("Pastikan nominal yang anda masukan Valid", "WARNING");
            e.printStackTrace();
        }
    }
}
=======
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

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;

public class TarikDana {
    
    public TarikDana(Driver driver) {
        Withdraw(driver);
    }
  
    private void Withdraw(Driver driver) {

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 16);
        
        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = new JLabel("Dompet " + driver.getUser_name() + ".");
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
        String strSaldo = String.valueOf(Controller.getInstance().getWallet(driver.getDriver_id()));
        if (Controller.getInstance().getWallet(driver.getDriver_id()) > 9999999) {
            strSaldo = "9999999+";
        }
        JLabel saldo = new JLabel("Rp.  "  + strSaldo);
        saldo.setHorizontalAlignment(SwingConstants.RIGHT);
        saldo.setFont(font);
        saldo.setBounds(250, 150, 200, 30);
        saldo.setBackground(null);
        f.add(saldo);
        
        JButton backButton = new JButton("Kembali");
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(e -> {
            new MainMenuDriver(driver.getDriver_id());
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
                } else if (nominalTarik > Controller.getInstance().getWallet(driver.getDriver_id())) {
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
                        if (Controller.getInstance().updateJoPay(driver.getDriver_id(), Controller.getInstance().getWallet(driver.getDriver_id()) - nominalTarik)) {
                            JOptionPane.showMessageDialog(f, "Penarikan berhasil dilakukan!", "",
                                    JOptionPane.DEFAULT_OPTION);
                        } else {
                            JOptionPane.showMessageDialog(f, "Penarikan gagal dilakukan!", "WARNING",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        new MainMenuDriver(driver.getDriver_id());
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
>>>>>>> master
