<<<<<<< HEAD
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
=======
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;

public class PassangerProfile {

    public PassangerProfile(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Passanger> pass = Controller.getInstance().getPassangerByID(id);

        JLabel intro = new JLabel("Halo, " + pass.get(pass.size() - 1).getUser_name() + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Mau update apa nih?");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(30, 70, 400, 30);
        intro2.setBounds(30, 90, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 120, 500, 20);

        Font fontLabel = new Font("Courier", Font.BOLD, 16);

        JLabel labelNama = new JLabel("Username ");
        JTextField textNama = new JTextField(pass.get(pass.size() - 1).getUser_name());
        labelNama.setFont(fontLabel);
        labelNama.setBounds(30, 160, 100, 30);
        textNama.setBounds(260, 160, 200, 30);

        JLabel labelTelepon = new JLabel("Nomor  Telepon ");
        JTextField textTelepon = new JTextField(pass.get(pass.size() - 1).getPhone_number());
        labelTelepon.setFont(fontLabel);
        labelTelepon.setBounds(30, 190, 200, 30);
        textTelepon.setBounds(260, 190, 200, 30);

        JButton buttonGanti = new JButton("Ganti Password");
        buttonGanti.setBounds(40, 480, 400, 30);
        buttonGanti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GantiPassword(id, pass.get(pass.size() - 1).getUser_pass());
                f.dispose();
            }
        });

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(40, 515, 400, 30);
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean succeed = Controller.getInstance().updateUserNameDataPassangerToDB(id, textNama.getText());
                boolean succeed2 = Controller.getInstance().updatePhoneNumDataPassangerToDB(id, textTelepon.getText());
                if (succeed && succeed2) {
                    JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
                } else {
                    JOptionPane.showMessageDialog(f, "Gagal di Update", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuPassanger(id);
            }
        });

        f.add(buttonSimpan);
        f.add(buttonGanti);
        f.add((intro));
        f.add((intro2));
        f.add(labelNama);
        f.add(textNama);
        f.add(labelTelepon);
        f.add(textTelepon);
        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
>>>>>>> master
