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
