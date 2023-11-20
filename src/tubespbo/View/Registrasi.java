/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

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

        //NIK
        JLabel labelEmail = new JLabel("Email: ");
        JTextField textEmail = new JTextField();
        labelEmail.setBounds(10, 20, 200, 20);
        textEmail.setBounds(200, 20, 200, 20);

        //Nama
        JLabel labelNama = new JLabel("Nama: ");
        JTextField textNama = new JTextField();
        labelNama.setBounds(10, 40, 200, 20);
        textNama.setBounds(200, 40, 200, 20);

        //Tempat lahir
        JLabel labelPassword = new JLabel("Password: ");
        JPasswordField textPassword = new JPasswordField();
        labelPassword.setBounds(10, 60, 200, 20);
        textPassword.setBounds(200, 60, 200, 20);

        //foto
        JLabel labelFoto = new JLabel("Foto: ");
        JButton fotoChooser = new JButton("Choose File:");
        JLabel pathFoto = new JLabel("No file selected");
        labelFoto.setBounds(10, 80, 200, 20);
        fotoChooser.setBounds(200, 80, 200, 20);
        pathFoto.setBounds(200, 100, 300, 20);
        fotoChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                int returnValue = jf.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jf.getSelectedFile();
                    String pathFile = selectedFile.getAbsolutePath();
                    pathFoto.setText(pathFile);
                }
            }
        });

//        JLabel labelCategory = new JLabel("Category: ");
//        String listCategory[] = con.getCategory().toArray(new String [con.getCategory().size()]);
//        JComboBox boxCategory = new JComboBox(con.getCategory().toArray());
//        boxCategory.setSelectedItem(null);
//        labelCategory.setBounds(10, 120, 200, 20);
//        boxCategory.setBounds(200, 120, 200, 20);
//
//        //tombol submit
//        JButton insertData = new JButton("Submit");
//        insertData.setBounds(200, 160, 200, 30);
//        insertData.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String email = textEmail.getText();
//                String nama = textNama.getText();
//                String password = String.valueOf(textPassword.getPassword());
//                String category = boxCategory.getSelectedItem().toString();
//                
//                if (email.isEmpty() || nama.isEmpty() || password.isEmpty() || category.isEmpty() 
//                        || pathFoto.getText().equals("No file selected")) {
//                    JOptionPane.showMessageDialog(f, "Data lom lengkap coy", "Bang isi Bang", JOptionPane.WARNING_MESSAGE);
//                } else {
//                    int categoryInt = con.getIntCategory(category);
//                    boolean succeed = con.inputDataToDB(nama, email, password, pathFoto.getText(), categoryInt);
//                    f.dispose();
//                    if (succeed) {
//                        JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
//                        new MainMenu();
//                        f.dispose();
//                    }
//                }
//
//            }
//        });
//        f.add(insertData);
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(160, 350, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new StartMenu();
            }
        });

        f.add(labelEmail);
        f.add(textEmail);
        f.add(labelNama);
        f.add(textNama);
        f.add(labelPassword);
        f.add(textPassword);
//        f.add(labelCategory);
//        f.add(boxCategory);
        f.add(labelFoto);
        f.add(fotoChooser);
        f.add(pathFoto);

        f.setSize(500, 300);
        f.setLayout(null);
        f.setVisible(true);
    }

}
