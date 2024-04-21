<<<<<<< HEAD
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;

public class DriverProfile {

    public DriverProfile(Driver driver) {
        showDataScreen(driver);
    }
    protected JFrame f = new JFrame();
    JLabel labelNamaVehicle, labelPlat;
    protected JTextField textPlat, textNamaVehicle;

    //bertahap yaa sayy kalemm
    private void showDataScreen(Driver driver) {
        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        Font fontLabel = new Font("Courier", Font.BOLD, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 13);

        f = FrameHandler.createFrame("Your Title", 500, 600);

        JLabel intro = FrameHandler.createLabel("Halo, " + driver.getUser_name() + "!", font, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Mau update apa nih?", font2, 30, 90, 300, 30);
        JLabel lineDiv = FrameHandler.createLabel("________________________________________________", null, 10, 120, 500, 20);

        JLabel labelNama = FrameHandler.createLabel("Username ", fontLabel, 30, 160, 100, 30);
        JTextField textNama = FrameHandler.createTextField(driver.getUser_name(), null, null, 260, 160, 200, 30);

        JLabel labelTelepon = FrameHandler.createLabel("Nomor Telepon ", fontLabel, 30, 190, 200, 30);
        JTextField textTelepon = FrameHandler.createTextField(driver.getDriver_phonNum(), null, null, 260, 190, 200, 30);

        JLabel labelJenis = FrameHandler.createLabel("Ganti jenis Kendaraan ", fontLabel, 30, 230, 200, 30);

        String listKendaraan[] = {"Mobil", "Motor"};
        JComboBox boxPilihVehicle = new JComboBox(listKendaraan);
        boxPilihVehicle.setSelectedItem(null);
        boxPilihVehicle.setBounds(260, 230, 200, 30);

        labelNamaVehicle = FrameHandler.createLabel("Nama Vehicle ", fontLabel, 30, 270, 200, 30);
        textNamaVehicle = FrameHandler.createTextField("", null, null, 260, 270, 200, 30);

        labelPlat = FrameHandler.createLabel("Plat Nomor Kendaraan ", fontLabel, 30, 310, 250, 30);
        textPlat = FrameHandler.createTextField("", null, null, 260, 310, 200, 30);

        boxPilihVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNamaVehicle.setText("Nama Vehicle ");
                labelNamaVehicle.setBounds(30, 270, 200, 30);
                textNamaVehicle.setBounds(260, 270, 200, 30);

                labelPlat.setText("Plat Nomor Kendaraan ");
                labelPlat.setBounds(30, 310, 250, 30);
                textPlat.setBounds(260, 310, 200, 30);
            }
        });

        JButton buttonGanti = FrameHandler.createButton("Ganti Password", fontButton, 40, 480, 400, 30,
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GantiPassword(driver.getDriver_id(), driver.getUser_pass());
                f.dispose();
            }
        });
        f.add(buttonGanti);

        JButton buttonSimpan = FrameHandler.createButton("Simpan", fontButton, 40, 515, 400, 30,
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean succeed = Controller.getInstance().updateUsernameDataDriverToDB(driver.getDriver_id(), textNama.getText());
                boolean succeed2 = Controller.getInstance().updateDataDriverToDB(driver.getDriver_id(), textTelepon.getText(), textNamaVehicle.getText(), textPlat.getText(), boxPilihVehicle.getSelectedItem().toString());
                if (succeed && succeed2) {
                    FrameHandler.showInformationMessage("Data berhasil disimpan", "");
                } else {
                    FrameHandler.showErrorDialog("Gagal di Update", "");
                }
            }
        });
        f.add(buttonSimpan);

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 90, 30,
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuDriver(driver.getDriver_id());
            }
        });
        f.add(backButton);

        f.add(intro);
        f.add(intro2);
        f.add(lineDiv);
        f.add(labelNama);
        f.add(textNama);
        f.add(labelTelepon);
        f.add(textTelepon);
        f.add(labelJenis);
        f.add(boxPilihVehicle);

        f.setLayout(null);
        f.setVisible(true);
    }

}
=======
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;

public class DriverProfile {

    public DriverProfile(Driver driver) {
        showDataScreen(driver);
    }
    protected JFrame f = new JFrame();
    JLabel labelNamaVehicle, labelPlat;
    protected JTextField textPlat, textNamaVehicle;

    //bertahap yaa sayy kalemm
    private void showDataScreen(Driver driver) {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel intro = new JLabel("Halo, " + driver.getUser_name() + "!");
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
        JTextField textNama = new JTextField(driver.getUser_name());
        labelNama.setFont(fontLabel);
        labelNama.setBounds(30, 160, 100, 30);
        textNama.setBounds(260, 160, 200, 30);

        JLabel labelTelepon = new JLabel("Nomor Telepon ");
        JTextField textTelepon = new JTextField(driver.getDriver_phonNum());
        labelTelepon.setFont(fontLabel);
        labelTelepon.setBounds(30, 190, 200, 30);
        textTelepon.setBounds(260, 190, 200, 30);

        JLabel labelJenis = new JLabel("Ganti jenis Kendaraan ");
        labelJenis.setFont(fontLabel);
        labelJenis.setBounds(30, 230, 200, 30);

        String listKendaraan[] = {"Mobil", "Motor"};
        JComboBox boxPilihVehicle = new JComboBox(listKendaraan);
        boxPilihVehicle.setSelectedItem(null);
        boxPilihVehicle.setBounds(260, 230, 200, 30);

        labelNamaVehicle = new JLabel();
        labelNamaVehicle.setFont(fontLabel);
        f.add(labelNamaVehicle);
        labelPlat = new JLabel();
        f.add(labelPlat);
        textNamaVehicle = new JTextField();
        f.add(textNamaVehicle);
        textPlat = new JTextField();
        labelPlat.setFont(fontLabel);
        textPlat = new JTextField();
        f.add(textPlat);

        boxPilihVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNamaVehicle.setText("Nama Vehicle ");
                labelNamaVehicle.setBounds(30, 270, 200, 30);
                textNamaVehicle.setBounds(260, 270, 200, 30);

                labelPlat.setText("Plat Nomor Kendaraan ");

                labelPlat.setBounds(30, 310, 250, 30);
                textPlat.setBounds(260, 310, 200, 30);

            }
        });

        JButton buttonGanti = new JButton("Ganti Password");
        buttonGanti.setBounds(40, 480, 400, 30);
        buttonGanti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GantiPassword(driver.getDriver_id(), driver.getUser_pass());
                f.dispose();
            }
        });

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(40, 515, 400, 30);
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean succeed = Controller.getInstance().updateUsernameDataDriverToDB(driver.getDriver_id(), textNama.getText());
                boolean succeed2 = Controller.getInstance().updateDataDriverToDB(driver.getDriver_id(), textTelepon.getText(), textNamaVehicle.getText(), textPlat.getText(), boxPilihVehicle.getSelectedItem().toString());
                if (succeed && succeed2) {
                    JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
                } else {
                    JOptionPane.showMessageDialog(f, "Gagal di Update", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(
                10, 10, 90, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuDriver(driver.getDriver_id());
            }
        });

        f.add(intro);
        f.add(intro2);
        f.add(labelNama);
        f.add(textNama);
        f.add(labelTelepon);
        f.add(textTelepon);

        f.add(labelJenis);
        f.add(boxPilihVehicle);
        f.add(backButton);
        f.add(buttonGanti);
        f.add(buttonSimpan);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

}
>>>>>>> master
