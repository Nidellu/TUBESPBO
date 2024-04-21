<<<<<<< HEAD
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

public class MenuTopUp {

    JFrame f;

    public MenuTopUp(int id) {
        createForm(id);
    }

    private void createForm(int id) {
        User currUser = Controller.getInstance().getUserByID(id);
        System.out.println();

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 16);

        f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = FrameHandler.createLabel("Dompet " + currUser.getUser_name() + ".", font, 30, 70, 400, 30);
        f.add(intro);

        JLabel intro2 = FrameHandler.createLabel("Kalau dompet tipis jangan nangis yah!", font2, 30, 90, 300, 30);
        f.add(intro2);

        JLabel lineDiv = FrameHandler.createLabel("____________________________________________________________"
                + "__________________________________________________________", null, 10, 110, 460, 20);
        f.add(lineDiv);

        JPanel frameWallet = new JPanel(null);
        frameWallet.setSize(400, 100);
        frameWallet.setBounds(15, 60, 400, 100);

        JLabel wallet = FrameHandler.createLabel("Saldo:", font, 30, 150, 800, 30);
        wallet.setBackground(null);
        f.add(wallet);

        String strSaldo = String.valueOf(Controller.getInstance().getWallet(id));
        if (Controller.getInstance().getWallet(id) > 9999999) {
            strSaldo = "9999999+";
        }

        JLabel saldo = FrameHandler.createLabel("Rp.  " + strSaldo, font, 250, 150, 200, 30);
        saldo.setHorizontalAlignment(SwingConstants.RIGHT);
        saldo.setBackground(null);
        f.add(saldo);

        JLabel labelJumlahTarik = FrameHandler.createLabel("Jumlah Top Up: ", font2, 30, 170, 250, 85);
        f.add(labelJumlahTarik);

        JTextField topUpField = FrameHandler.createTextField("0", Color.WHITE, font3, 30, 240, 420, 40);
        f.add(topUpField);

        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 13), 10, 10, 90, 30, e -> onBack(id, currUser.getUser_role()));

        JButton addTopUp = FrameHandler.createButton("Top Up", new Font("Courier", Font.BOLD, 13), 40, 490, 400, 30, e -> onTopUp(id, topUpField, currUser.getUser_role()));

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

    private void onTopUp(int id, JTextField topUpField, String role) {
        String inputField = topUpField.getText();
        if (inputField.isBlank()) {
            FrameHandler.showErrorDialog("Masukan Dana sebelum Top Up", "Top Up Jo-Pay");
        } else {
            try {
                float saldoTambahan = Float.parseFloat(inputField);
                float currSaldo = Controller.getInstance().getWallet(id);
                if (saldoTambahan < 2000) {
                    FrameHandler.showErrorDialog("Minimal Top Up saldo adalah 2000", "WARNING");
                } else {
                    int result = FrameHandler.showConfirmationDialog("Anda yakin ingin menambahkan saldo?", "Konfirmasi Top Up");
                    if (result == JOptionPane.YES_OPTION) {
                        Controller.getInstance().updateJoPay(id, currSaldo + saldoTambahan);
                        FrameHandler.showMessageDialog("Top Up berhasil dilakukan!", "WARNING");
                        onBack(id, role);
                    } else {
                        FrameHandler.showInformationMessage("Top Up saldo telah dibatalkan", "");

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
import tubespbo.Model.User;

public class MenuTopUp {

    public MenuTopUp(int id) {
        User currUser = Controller.getInstance().getUserByID(id);
        System.out.println();

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 16);

        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = new JLabel("Dompet " + currUser.getUser_name() + ".");
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

        String strSaldo = String.valueOf(Controller.getInstance().getWallet(id));
        if (Controller.getInstance().getWallet(id) > 9999999) {
            strSaldo = "9999999+";
        }

        JLabel saldo = new JLabel("Rp.  " + strSaldo);
        saldo.setHorizontalAlignment(SwingConstants.RIGHT);
        saldo.setFont(font);
        saldo.setBounds(250, 150, 200, 30);
        saldo.setBackground(null);
        f.add(saldo);

        JButton backButton = new JButton("Kembali");
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(e -> {
            if (currUser.getUser_role().equalsIgnoreCase("DRIVER")) {
                new MainMenuDriver(id);
            } else if (currUser.getUser_role().equalsIgnoreCase("PASSANGER")) {
                new MainMenuPassanger(id);
            }
            f.dispose();
        });
        f.add(backButton);

        JLabel labelJumlahTarik = new JLabel("Jumlah Top Up: ");
        labelJumlahTarik.setFont(font2);
        labelJumlahTarik.setBounds(30, 170, 250, 85);
        f.add(labelJumlahTarik);

        JTextField topUpField = new JTextField("0");
        topUpField.setBackground(Color.WHITE);
        topUpField.setFont(font3);
        topUpField.setBounds(30, 240, 420, 40);
        f.add(topUpField);

        JButton addTopUp = new JButton("Top Up");
        addTopUp.setBounds(40, 490, 400, 30);
        addTopUp.addActionListener(e -> {
            String inputField = topUpField.getText();
            if (inputField.isBlank()) {
                JOptionPane.showMessageDialog(f, "Masukan Dana sebelum Top Up", "Top Up Jo-Pay",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    float saldoTambahan = Float.parseFloat(inputField);
                    float currSaldo = Controller.getInstance().getWallet(id);
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
                            Controller.getInstance().updateJoPay(id, currSaldo + saldoTambahan);
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
        f.setSize(500, 600);

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
>>>>>>> master
