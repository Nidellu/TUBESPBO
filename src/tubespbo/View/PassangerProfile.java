package tubespbo.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;

public class PassangerProfile {

    JFrame frame;

    public PassangerProfile(int id) {
        showProfileScreen(id);
    }

    private void showProfileScreen(int id) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);

        ArrayList<Passanger> passangerList = Controller.getInstance().getPassangerByID(id);
        Passanger passanger = passangerList.get(passangerList.size() - 1);

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 20);
        Font text2 = new Font("Courier", Font.PLAIN, 16);
        Font text3 = new Font("Courier", Font.PLAIN, 14);
        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel intro = FrameHandler.createLabel("Halo, " + passanger.getUser_name() + "!", heading1, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Mau update apa nih?", text1, 30, 90, 300, 30);

        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", null, 10, 120, 500, 20);

        JLabel labelNama = FrameHandler.createLabel("Username ", text2, 30, 160, 100, 30);
        JTextField textNama = FrameHandler.createTextField(passanger.getUser_name(), null, null, 260, 160, 200, 30);

        JLabel labelTelepon = FrameHandler.createLabel("Nomor Telepon ", text2, 30, 190, 200, 30);
        JTextField textTelepon = FrameHandler.createTextField(passanger.getPhone_number(), null, null, 260, 190, 200, 30);

        JButton changeButton = FrameHandler.createButton("Ganti Password", fontButton, 40, 480, 400, 30, e -> changeButton(id, passanger.getUser_pass()));

        JButton keepButton = FrameHandler.createButton("Simpan", fontButton, 40, 515, 400, 30, e -> keepButton(id, textNama.getText(), textTelepon.getText()));

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 90, 30, e -> onBack(id));

        frame.add(keepButton);
        frame.add(changeButton);
        frame.add(intro);
        frame.add(intro2);
        frame.add(labelNama);
        frame.add(textNama);
        frame.add(labelTelepon);
        frame.add(textTelepon);
        frame.add(backButton);
        frame.add(lineDiv);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onBack(int id) {
        frame.dispose();
        new MainMenuPassanger(id);
    }

    private void keepButton(int id, String nama, String telepon) {
        boolean succeed = Controller.getInstance().updateUserNameDataPassangerToDB(id, nama);
        boolean succeed2 = Controller.getInstance().updatePhoneNumDataPassangerToDB(id, telepon);
        if (succeed && succeed2) {
            FrameHandler.showMessageDialog("Data berhasil disimpan", "");
        } else {
            FrameHandler.showWarningMessage("Gagal di Update", "");
        }
    }

    private void changeButton(int id, String password) {
        new GantiPassword(id, password);
        frame.dispose();
    }
}
