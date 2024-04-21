package tubespbo.View;

import javax.swing.*;
import java.awt.*;

import tubespbo.Controller.Controller;

public class RegistrasiPassanger {

    private JFrame frame;
    private JTextField teleponField;

    public RegistrasiPassanger(String username, String password, String roles) {
        createForm(username, password, roles);
    }

    private void createForm(String username, String password, String roles) {
        frame = new JFrame("Form Registrasi Driver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font heading2 = new Font("Courier", Font.BOLD, 16);
        Font text1 = new Font("Courier", Font.PLAIN, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 12);
        
        JLabel intro = FrameHandler.createLabel("Selamat Datang di Josen!", heading1, 10, 10, 300, 30);
        JLabel intro2 = FrameHandler.createLabel("Silahkan isi nomor telepon", text1, 10, 40, 300, 30);

        JLabel teleponLabel = FrameHandler.createLabel("Nomor Telepon:", heading2, 10, 80, 200, 30);
        teleponField = FrameHandler.createTextField(null, null, null, 200, 80, 250, 30);

        JButton submitButton = FrameHandler.createButton("Submit", fontButton, 260, 300, 150, 30,e -> onSubmit(username, password, roles));

        JButton backButton = FrameHandler.createButton("Back", fontButton, 60, 300, 150, 30, e -> onBack());

        frame.add(intro);
        frame.add(intro2);
        frame.add(teleponLabel);
        frame.add(teleponField);
        frame.add(submitButton);
        frame.add(backButton);

        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onSubmit(String username, String password, String roles) {
        String telepon = teleponField.getText();
        if (telepon.isEmpty()) {
            FrameHandler.showWarningMessage("Nomor telepon tidak boleh kosong", "");
        } else {
            boolean succeed = Controller.getInstance().addUserToDB(username, password, roles);
            if (succeed) {
                int id = Controller.getInstance().getIDUser(username);
                boolean succeedPassenger = Controller.getInstance().inputPassangerDataToDB(id, telepon);
                if (succeedPassenger) {
                    FrameHandler.showMessageDialog("Data berhasil disimpan, Silahkan Login", "");
                    new LogIn();
                    frame.dispose();
                } else {
                    FrameHandler.showWarningMessage("Gagal menyimpan data", "");
                }
            } else {
                FrameHandler.showWarningMessage("Gagal menyimpan data", "");
            }
        }
    }

    private void onBack() {
        frame.dispose();
        new Registrasi();
    }
}

