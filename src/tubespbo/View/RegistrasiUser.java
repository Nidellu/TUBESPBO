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
public class RegistrasiUser {

    public RegistrasiUser(String username, String password, String roles) {
        form(username, password, roles);
    }

    private void form(String username,String password, String roles) {
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

        //Email
        JLabel labelEmail = new JLabel("Email ");
        JTextField textEmail = new JTextField();
        labelEmail.setFont(fontLabel);
        labelEmail.setBounds(10, 80, 200, 30);
        textEmail.setBounds(200, 80, 250, 30);

        //Nama
        JLabel labelNama = new JLabel("Username ");
        JTextField textNama = new JTextField();
        labelNama.setFont(fontLabel);
        labelNama.setBounds(10, 110, 200, 30);
        textNama.setBounds(200, 110, 250, 30);

        //Tempat lahir
        JLabel labelPassword = new JLabel("Password ");
        JPasswordField textPassword = new JPasswordField();
        labelPassword.setFont(fontLabel);
        labelPassword.setBounds(10, 140, 200, 30);
        textPassword.setBounds(200, 140, 250, 30);

        JLabel labelCategory = new JLabel("Category: ");
        String listCategory[] = {"Driver", "Status"};
        JComboBox boxRoles = new JComboBox(listCategory);
        boxRoles.setSelectedItem(null);
        labelCategory.setFont(fontLabel);
        labelCategory.setBounds(10, 170, 200, 30);
        boxRoles.setBounds(200, 170, 250, 30);

        //tombol submit
        JButton insertData = new JButton("Submit");
        insertData.setBounds(260, 300, 150, 30);
        insertData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textEmail.getText();
                String nama = textNama.getText();
                String password = String.valueOf(textPassword.getPassword());
                String roles = boxRoles.getSelectedItem().toString();

                if (email.isEmpty() || nama.isEmpty() || password.isEmpty() || roles.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Data belum lengkap nih", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean find = con.getUserName(nama);
                    f.dispose();
                    if (!find) {
                        boolean succeed = true;
                        if(succeed){
                            JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
                            String id = con.getIDUser(nama);
                            new MainMenuDriver(id);
                            
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Maaf username udah dipake nih", "", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
        f.add(insertData);

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

        f.add(labelEmail);
        f.add(textEmail);
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

    public static void main(String[] args) {
        new RegistrasiUser();
    }
}
