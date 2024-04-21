<<<<<<< HEAD
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

=======
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;

public class RegistrasiPassanger {

    public RegistrasiPassanger(String username, String password, String roles) {
        form(username, password, roles);
    }

    private void form(String username,String password, String roles) {
        JFrame f = new JFrame("Form Registrasi Driver");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = new JLabel("Selamat Datang di Josen!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Silahkan nomor telepon dan data kendaraan");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 300, 30);
        intro2.setBounds(10, 30, 350, 30);

        Font fontLabel = new Font("Courier", Font.BOLD, 16);
        
        JLabel labelTelepon = new JLabel("Nomor  Telepon ");
        JTextField textTelepon = new JTextField();
        labelTelepon.setFont(fontLabel);
        labelTelepon.setBounds(10, 80, 200, 30);
        textTelepon.setBounds(200, 80, 250, 30);

        //tombol submit
        JButton insertData = new JButton("Submit");
        insertData.setBounds(260, 300, 150, 30);
        insertData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String telepon = textTelepon.getText();

                if (telepon.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Data belum lengkap nih", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean succeed = Controller.getInstance().inputUserDataToDB(username, password, roles);
                    if (succeed) {
                        int id = Controller.getInstance().getIDUser(username);
                        boolean succeedDriver = Controller.getInstance().inputPassangerDataToDB(id, telepon);
                        if (succeedDriver) {
                            JOptionPane.showMessageDialog(f, "Data berhasil disimpan,  Silahkan Login");
                            new LogIn();
                            f.dispose();
                        } else {
                            JOptionPane.showMessageDialog(f, "Data gagal Disimpan", "", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Data gagal Disimpan", "", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
        f.add(insertData);

        JButton backButton = new JButton("Back");

        backButton.setBounds(
                60, 300, 150, 30);
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Registrasi();
            }
        }
        );

        f.add(intro);
        f.add(intro2);

        f.add(labelTelepon);
        f.add(textTelepon);

        f.setLocationRelativeTo(null);
        f.setSize(500, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.add(backButton);
    }

}
>>>>>>> master
