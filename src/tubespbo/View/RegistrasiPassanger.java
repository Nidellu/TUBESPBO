/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import tubespbo.Contoller.Controller;

/**
 *
 * @author brian
 */
public class RegistrasiPassanger {

    public RegistrasiPassanger(String username, String password, String roles) {
        form(username, password, roles);
    }

    private void form(String username,String password, String roles) {
        JFrame f = new JFrame("Form Registrasi Driver");
        Controller con = new Controller();

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
                    boolean succeed = con.inputUserDataToDB(username, password, roles);
                    if (succeed) {
                        int id = con.getIDUser(username);
                        boolean succeedDriver = con.inputPassangerDataToDB(id, telepon);
                        if (succeedDriver) {
                            JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
                            new MainMenuPassanger(id);
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

        f.setSize(500, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.add(backButton);
    }

    public static void main(String[] args) {
        new RegistrasiPassanger("", "", "");
    }
}
