package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;

public class OrderRide {
    public OrderRide(int id) {
        showDataScreen(id);
    }

    private Map<String, Integer> vehicleCosts;
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

        // lokasi jemput
        JLabel labelAsal = new JLabel("Lokasi jemput ");
        labelAsal.setFont(fontLabel);
        labelAsal.setBounds(30, 130, 150, 30);
        JTextField textAsal = new JTextField();
        textAsal.setFont(fontLabel);
        textAsal.setBounds(260, 133, 200, 30);
        textAsal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCost();
            }
        });

        // lokasi tujuan
        JLabel labelTujuan = new JLabel("Tujuan ");
        labelTujuan.setFont(fontLabel);
        labelTujuan.setBounds(30, 170, 150, 30);
        JTextField textTujuan = new JTextField();
        textTujuan.setFont(fontLabel);
        textTujuan.setBounds(260, 173, 200, 30);
        textTujuan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCost();
            }
        });
        
        // buat menampilkan tarif


        // jenis kendaraan
        JLabel labelPilihVehicle = new JLabel("Pilih Kendaraan ");
        String listKendaraan[] = {"Mobil", "Motor"};
        // JComboBox boxPilihVehicle = new JComboBox(listKendaraan);
        // boxPilihVehicle.setSelectedItem(null);
        // labelPilihVehicle.setFont(fontLabel);
        // labelPilihVehicle.setBounds(30, 210, 200, 30);
        // boxPilihVehicle.setBounds(260, 213, 200, 30);
        labelPilihVehicle = new JLabel("Pilih Kendaraan ");
        String[] listCategory = {"Mobil", "Motor"};
        vehicleCosts = new HashMap<>();
        vehicleCosts.put("Mobil", 10);
        vehicleCosts.put("Motor", 20);
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(listCategory);
        JComboBox boxPilihVehicle = new JComboBox<>(comboBoxModel);
        boxPilihVehicle.setSelectedItem(null);
        labelPilihVehicle.setFont(fontLabel);
        labelPilihVehicle.setBounds(30, 110, 200, 30);
        boxPilihVehicle.setBounds(260, 113, 200, 30);


        // input kode promo
        JLabel labelKodePromo = new JLabel("Masukan Kode Promo");
        labelKodePromo.setBounds(30, 250, 200, 30);
        JTextField kodePromoField = new JTextField();
        kodePromoField.setBounds(260, 253, 200, 30);

        //back button
        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 90, 30);
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuPassanger(id);
            }
        });

        // order button
        JButton orderButton = new JButton("Pesan Sekarang");
        orderButton.setFont(fontButton);
        orderButton.setBounds(260, 295, 200, 30);
        orderButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (isAnyFieldEmpty()) {
                    JOptionPane.showMessageDialog(null, "Masih ada bagian yang kosong nih!", "Isi Dulu Datanya", JOptionPane.ERROR_MESSAGE);
                } else {
                    int userId = id;
                    String asal = textAsal.getText();
                    String tujuan = textTujuan.getText();
                    String promo = kodePromoField.getText();
                    String jenisKendaraan = boxPilihVehicle.getSelectedItem().toString();
                    f.dispose();
                }
            }

            private boolean isAnyFieldEmpty() {
                return textAsal.getText().isEmpty()
                        || textTujuan.getText().isEmpty()
                        || boxPilihVehicle.getSelectedItem() == null;
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
        f.add(backButton); // add back button
        f.add(orderButton); // add order button
        f.add(lineDiv);


        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }
    private JLabel labelAsal, labelTujuan, labelPilihVehicle, labelResult;
    private void calculateCost() {
        char source = textAsal.getText().toUpperCase().charAt(0);
        char destination = textTujuan.getText().toUpperCase().charAt(0);
        int cost = con.calculateCost(source, destination);
        String selectedVehicle = (String) boxPilihVehicle.getSelectedItem();

        if (cost != -1) {
            labelResult.setText(selectedVehicle + " --- Rp. " + cost);
        } else {
            labelResult.setText("Biaya tidak dapat dihitung.");
        }
    }

}
