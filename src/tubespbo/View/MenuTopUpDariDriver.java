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
import tubespbo.Model.User;

public class MenuTopUpDariDriver {
    
    JFrame f;

    public MenuTopUpDariDriver(int id, int idDriver, String driverName) {
        showMenuTopUp(id, idDriver, driverName);
    }

    private void showMenuTopUp(int id, int idDriver, String driverName) {
        User currUser = Controller.getInstance().getUserByID(id);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 16);

        f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JLabel intro
        JLabel intro = FrameHandler.createLabel("Top Up dari " + driverName + ".", font, 30, 70, 400, 30);
        f.add(intro);

        // JLabel intro2
        JLabel intro2 = FrameHandler.createLabel("Top up uang yah jangan hatinya eaaa", font2, 30, 90, 300, 30);
        f.add(intro2);

        // JLabel lineDiv
        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 110, 460, 20);
        f.add(lineDiv);

        // JPanel frameWallet
        JPanel frameWallet = new JPanel(null);
        frameWallet.setSize(400, 100);
        frameWallet.setBounds(15, 60, 400, 100);
        f.add(frameWallet);

        // JLabel wallet
        JLabel wallet = FrameHandler.createLabel("Saldo:", font, 30, 150, 800, 30);
        wallet.setBackground(null);
        f.add(wallet);

        // Determining saldo value
        String strSaldo = String.valueOf(Controller.getInstance().getWallet(id));
        if (Controller.getInstance().getWallet(id) > 9999999) {
            strSaldo = "9999999+";
        }

        // JLabel saldo
        JLabel saldo = FrameHandler.createLabel("Rp.  " + strSaldo, font, 250, 150, 200, 30);
        saldo.setHorizontalAlignment(SwingConstants.RIGHT);
        saldo.setBackground(null);
        f.add(saldo);

        // JLabel labelJumlahTarik
        JLabel labelJumlahTarik = FrameHandler.createLabel("Jumlah Top Up: ", font2, 30, 170, 250, 85);
        f.add(labelJumlahTarik);

        // JTextField topUpField
        JTextField topUpField = FrameHandler.createTextField("0", Color.WHITE, font3, 30, 240, 420, 40);
        f.add(topUpField);

        JButton backButton = FrameHandler.createButton("Kembali", null, 10, 10, 90, 30, e -> onBack(id, currUser.getUser_role()));

        JButton addTopUp = FrameHandler.createButton("Top Up", null, 40, 490, 400, 30, e -> onTopUp(id, idDriver, topUpField));

        f.add(backButton);
        f.add(addTopUp);
        f.setSize(500, 600);

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    private void onBack(int id, String role) {
        f.dispose();
        if (role.equalsIgnoreCase("DRIVER")) {
            new MainMenuDriver(id);
        } else if (role.equalsIgnoreCase("PASSANGER")) {
            new MainMenuPassanger(id);
        }
    }

    private void onTopUp(int id, int idDriver, JTextField topUpField) {
        String inputField = topUpField.getText();
        if (inputField.isBlank()) {
            FrameHandler.showErrorDialog("Masukan Dana sebelum Top Up", "Top Up Jo-Pay");
        } else {
            try {
                float saldoTambahan = Float.parseFloat(inputField);
                if (saldoTambahan < 2000) {
                    FrameHandler.showErrorDialog("Minimal Top Up saldo adalah 2000", "WARNING");
                } else {
                    int result = FrameHandler.showConfirmationDialog("Anda yakin ingin menambahkan saldo?", "Konfirmasi Top Up");
                    if (result == JOptionPane.YES_OPTION) {
                        boolean succeed = Controller.getInstance().inputJopayList(id, idDriver, saldoTambahan);
                        if (succeed) {
                            FrameHandler.showMessageDialog("Tunggu Konfirmasi yah!", "Selamat");
                            new MainMenuPassanger(id);
                            f.dispose();
                        } else {
                            FrameHandler.showMessageDialog("Gagal top up", "Yahhh");
                        }
                    } else {
                        FrameHandler.showInformationMessage("Top Up saldo telah dibatalkan", "Yahhh :'(");
                    }
                }
            } catch (NumberFormatException e1) {
                FrameHandler.showErrorDialog("Pastikan Nominal yang anda masukan valid!", "Top Up");
                topUpField.setText(null);
                e1.printStackTrace();
            }
        }

    }
}
