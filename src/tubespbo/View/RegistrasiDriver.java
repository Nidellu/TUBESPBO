package tubespbo.View;

import javax.swing.*;
import java.awt.*;

import tubespbo.Controller.Controller;

public class RegistrasiDriver {
    
    public RegistrasiDriver(String username, String password) {
        createForm(username, password);
    }

    private  JFrame frame;
    private JTextField teleponField;
    private JTextField namaKendaraanField;
    private JTextField platField;
    private JComboBox<String> jenisComboBox;

    private void createForm(String username, String password) {
        frame = new JFrame("Form Registrasi Driver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        Font headingFont = new Font("Courier", Font.BOLD, 20);
        Font labelFont = new Font("Courier", Font.BOLD, 16);
        Font textFont = new Font("Courier", Font.PLAIN, 16);
        Font buttonFont = new Font("Courier", Font.BOLD, 12);

        JLabel intro = FrameHandler.createLabel("Selamat Datang di Josen!", headingFont, 10, 10, 300, 30);
        JLabel intro2 = FrameHandler.createLabel("Silahkan nomor telepon dan data kendaraan", textFont, 10, 40, 350, 30);

        JLabel labelTelepon = FrameHandler.createLabel("Nomor Telepon:", labelFont, 10, 80, 200, 30);
        teleponField = FrameHandler.createTextField(null, null, null, 200, 80, 250, 30);

        JLabel labelNamaKendaraan = FrameHandler.createLabel("Nama Kendaraan:", labelFont, 10, 120, 200, 30);
        namaKendaraanField = FrameHandler.createTextField(null, null, null, 200, 120, 250, 30);

        JLabel labelPlat = FrameHandler.createLabel("Plat Nomor Kendaraan:", labelFont, 10, 160, 200, 30);
        platField = FrameHandler.createTextField(null, null, null, 200, 160, 250, 30);

        JLabel labelJenis = FrameHandler.createLabel("Jenis Kendaraan:", labelFont, 10, 200, 200, 30);
        String[] jenisList = {"Mobil", "Motor"};
        jenisComboBox = new JComboBox<>(jenisList);
        jenisComboBox.setBounds(200, 200, 250, 30);

        JButton insertDataButton = FrameHandler.createButton("Submit", buttonFont, 260, 300, 150, 30, e -> onSubmit(username, password));

        JButton backButton = FrameHandler.createButton("Back", buttonFont, 60, 300, 150, 30, e -> onBack());

        frame.add(intro);
        frame.add(intro2);
        frame.add(labelTelepon);
        frame.add(teleponField);
        frame.add(labelNamaKendaraan);
        frame.add(namaKendaraanField);
        frame.add(labelPlat);
        frame.add(platField);
        frame.add(labelJenis);
        frame.add(jenisComboBox);
        frame.add(insertDataButton);
        frame.add(backButton);

        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onSubmit(String username, String password) {
        String telepon = teleponField.getText();
        String namaKendaraan = namaKendaraanField.getText();
        String plat = platField.getText();
        String jenis = jenisComboBox.getSelectedItem().toString();

        if (telepon.isEmpty() || namaKendaraan.isEmpty() || plat.isEmpty() || jenis.isEmpty()) {
            FrameHandler.showWarningMessage("Data belum lengkap", "");
        } else {
            boolean succeedDriver = Controller.getInstance().inputDriverDataToWaitingList(username, password, telepon, namaKendaraan, jenis, plat);
            if (succeedDriver) {
                FrameHandler.showMessageDialog("Data berhasil ditambahkan. Silahkan kembali ke menu utama.", "");
                new StartMenu();
                frame.dispose();
            } else {
                FrameHandler.showWarningMessage("Data gagal Disimpan", "");
            }
        }
    }

    private void onBack() {
        frame.dispose();
        new Registrasi();
    }
}
