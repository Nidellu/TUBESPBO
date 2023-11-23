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
import tubespbo.Model.Driver;

public class DriverProfile {
    public DriverProfile (int id) {
        showDataScreen(id);
    }

    //bertahap yaa sayy kalemm
    private void showDataScreen (int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Driver> driver = con.getDriverByID(id);

        JLabel intro = new JLabel("Halo, " + driver.get(driver.size() - 1).getUser_name() + "!");
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
        JTextField textNama = new JTextField(driver.get(driver.size() - 1).getUser_name());
        labelNama.setFont(fontLabel);
        labelNama.setBounds(30, 160, 100, 30);
        textNama.setBounds(260, 160, 200, 30);

        JLabel labelTelepon = new JLabel("Nomor  Telepon ");
        JTextField textTelepon = new JTextField(driver.get(driver.size() - 1).getDriver_phonNum());
        labelTelepon.setFont(fontLabel);
        labelTelepon.setBounds(30, 190, 200, 30);
        textTelepon.setBounds(260, 190, 200, 30);

        JLabel labelNamaVehicle = new JLabel("Nama Vehicle ");
        JTextField textNamaVehicle = new JTextField(driver.get(driver.size() - 1).getVehicle_name());
        labelNamaVehicle.setFont(fontLabel);
        labelNamaVehicle.setBounds(30, 220, 200, 30);
        textNamaVehicle.setBounds(260, 220, 200, 30);

        JLabel labelPlat = new JLabel("Plat Nomor Kendaraan ");
        JTextField textPlat = new JTextField(driver.get(driver.size() - 1).getVehicle_plate());
        labelPlat.setFont(fontLabel);
        labelPlat.setBounds(30, 190, 200, 30);
        textPlat.setBounds(260, 190, 200, 30);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(40, 515, 400, 30);
        // kalem yahh, segini duluu gw commitnya, ni otw tambahin yg di con DB
        // buttonSimpan.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         boolean succeed = con.;
        //         boolean succeed2 = con.updatePhoneNumDataPassangerToDB(id, textTelepon.getText());
        //         if (succeed && succeed2) {
        //             JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
        //         } else {
        //             JOptionPane.showMessageDialog(f, "Gagal di Update", "", JOptionPane.WARNING_MESSAGE);
        //         }
        //     }
        // });
        JButton backButton = new JButton("Kembali");

        backButton.setFont(fontButton);

        backButton.setBounds(
                10, 10, 90, 30);
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuDriver(id);
            }
        });
        
        f.add(intro);
        f.add(intro2);
        f.add(labelNama);
        f.add(textNama);
        f.add(labelTelepon);
        f.add(textTelepon);
        f.add(labelNamaVehicle);
        f.add(textNamaVehicle);
        f.add(labelPlat);
        f.add(textPlat);
        f.add(backButton);


        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    
}
