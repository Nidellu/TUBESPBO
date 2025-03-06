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
