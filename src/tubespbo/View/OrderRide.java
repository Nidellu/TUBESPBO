package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;

public class OrderRide {
    public OrderRide(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Passanger> pass = con.getPassangerByID(id);

        JLabel intro = new JLabel("Halo, " + pass.get(pass.size() - 1).getUser_name() + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Mau kemana kali ini sayy?");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(160, 50, 400, 30);
        intro2.setBounds(120, 70, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 90, 500, 20);

        Font fontLabel = new Font("Courier", Font.BOLD, 16);

        JLabel labelAsal = new JLabel("Lokasi jemput ");
        labelAsal.setFont(fontLabel);
        labelAsal.setBounds(30, 130, 150, 30);
        JTextField textAsal = new JTextField();
        textAsal.setFont(fontLabel);
        textAsal.setBounds(260, 133, 200, 30);

        JLabel labelTujuan = new JLabel("Tujuan ");
        labelTujuan.setFont(fontLabel);
        labelTujuan.setBounds(30, 170, 150, 30);
        JTextField textTujuan = new JTextField();
        textTujuan.setFont(fontLabel);
        textTujuan.setBounds(260, 173, 200, 30);

        JLabel labelPilihVehicle = new JLabel("Pilih Kendaraan ");
        String listCategory[] = {"Mobil", "Motor"};
        JComboBox boxPilihVehicle = new JComboBox(listCategory);
        boxPilihVehicle.setSelectedItem(null);
        labelPilihVehicle.setFont(fontLabel);
        labelPilihVehicle.setBounds(30, 210, 200, 30);
        boxPilihVehicle.setBounds(260, 213, 200, 30);

        JButton backButton = new JButton("Kembali");

        backButton.setFont(fontButton);

        backButton.setBounds(
                10, 10, 90, 30);
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuPassanger(id);
            }
        });

        f.add((intro));
        f.add((intro2));
        f.add(labelAsal);
        f.add(textAsal);
        f.add(labelTujuan);
        f.add(textTujuan);
        f.add(labelPilihVehicle);
        f.add(boxPilihVehicle);
        f.add(backButton);
        f.add(lineDiv);


        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    

}
