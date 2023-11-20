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
public class Registrasi {

    public Registrasi() {
        form();
    }

    private void form() {
        JFrame f = new JFrame("Form Registrasi");
        Controller con = new Controller();

        JLabel intro = new JLabel("Selamat Datang di Josen!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Silahkan isi data mu ya!");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 300, 30);
        intro2.setBounds(10, 30, 300, 30);

        Font fontLabel = new Font("Courier", Font.BOLD, 16);

        //Nama
        JLabel labelNama = new JLabel("Username ");
        JTextField textNama = new JTextField();
        labelNama.setFont(fontLabel);
        labelNama.setBounds(10, 80, 200, 30);
        textNama.setBounds(200, 80, 250, 30);

        //Tempat lahir
        JLabel labelPassword = new JLabel("Password ");
        JPasswordField textPassword = new JPasswordField();
        labelPassword.setFont(fontLabel);
        labelPassword.setBounds(10, 110, 200, 30);
        textPassword.setBounds(200, 110, 250, 30);

        JLabel labelCategory = new JLabel("Category: ");
        String listCategory[] = {"Driver", "Passanger"};
        JComboBox boxRoles = new JComboBox(listCategory);
        boxRoles.setSelectedItem(null);
        labelCategory.setFont(fontLabel);
        labelCategory.setBounds(10, 140, 200, 30);
        boxRoles.setBounds(200, 140, 250, 30);

        //tombol submit
        JButton next = new JButton("Lanjut");
        next.setBounds(260, 300, 150, 30);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textNama.getText();
                String password = String.valueOf(textPassword.getPassword());
                String roles = boxRoles.getSelectedItem().toString();

                if (nama.isEmpty() || password.isEmpty() || roles.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Data belum lengkap nih", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean check = con.getByUserName(nama);
                    if (!check) {
                        if (roles.equalsIgnoreCase("Passanger")) {
                            new RegistrasiPassanger(nama, password, roles);
                            f.dispose();
                        } else if (roles.equalsIgnoreCase("Driver")) {
                            new RegistrasiDriver(nama, password, roles);
                            f.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Maaf username udah diambil deh", "", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
        f.add(next);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(60, 300, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new StartMenu();
            }
        });

        f.add(intro);
        f.add(intro2);

        f.add(labelNama);
        f.add(textNama);
        f.add(labelPassword);
        f.add(textPassword);
        f.add(labelCategory);
        f.add(boxRoles);
        f.setSize(500, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.add(backButton);
    }

}
