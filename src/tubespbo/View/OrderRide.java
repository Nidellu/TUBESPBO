package tubespbo.View;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;

public class OrderRide {
    public OrderRide(int id) {
        showDataScreen(id);
    }

    private Map<String, Integer> vehicleCosts;
    private JLabel labelAsal, labelTujuan, labelPilihVehicle, labelResult, totalHarga;
    private JTextField textAsal, textTujuan;
    private JComboBox<String> boxPilihVehicle;
    private float hargaAwalValue = 0.0f;
    private float promoVal = 0.0f;
    private float totalHargaValue = 0.0f;
    private Controller con = new Controller();

    private void showDataScreen(int id) {
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
        labelAsal = new JLabel("Lokasi jemput ");
        labelAsal.setFont(fontLabel);
        labelAsal.setBounds(30, 130, 150, 30);
        textAsal = new JTextField();
        textAsal.setFont(fontLabel);
        textAsal.setBounds(260, 133, 200, 30);

        // lokasi tujuan
        labelTujuan = new JLabel("Tujuan ");
        labelTujuan.setFont(fontLabel);
        labelTujuan.setBounds(30, 170, 150, 30);
        textTujuan = new JTextField();
        textTujuan.setFont(fontLabel);
        textTujuan.setBounds(260, 173, 200, 30);
        
        // jenis kendaraan
        labelPilihVehicle = new JLabel("Pilih Kendaraan ");
        String listKendaraan[] = {"Mobil", "Motor"};
        boxPilihVehicle = new JComboBox(listKendaraan);
        boxPilihVehicle.setSelectedItem(null);
        labelPilihVehicle.setFont(fontLabel);
        labelPilihVehicle.setBounds(30, 210, 200, 30);
        boxPilihVehicle.setBounds(260, 213, 200, 30);

        // Add action listeners
        boxPilihVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCost();
            }
        });
       

        // input kode promo
        JLabel labelKodePromo = new JLabel("Masukan Kode Promo");
        labelKodePromo.setFont(fontLabel);
        labelKodePromo.setBounds(30, 250, 200, 30);
        JTextField kodePromoField = new JTextField();
        kodePromoField.setBounds(260, 250, 200, 30);
        
        JButton usePromo = new JButton("✔");
        usePromo.setFont(new Font("Arial", Font.PLAIN, 20));
        usePromo.setBounds(464, 250, 40, 30);
        // detail informasi promo yang digunakan
        usePromo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String kodePromo = kodePromoField.getText();
                if (kodePromo.isEmpty())  {
                    JOptionPane.showMessageDialog(null, "Input dulu kode promonya!", "Diisi Dulu Yaa", JOptionPane.ERROR_MESSAGE);
                } else {
                    float disc = con.getPromoVal(kodePromo);

                    String costString = labelResult.getText();
                    float costs;

                    try {
                        costs = Float.parseFloat(costString.replace("Rp. ", "").replace(",", ""));
                    } catch (NumberFormatException ex) {
                        costs = 0.0f;
                    }

                    float discVal = disc * costs;
                    promoVal = discVal;
                    DecimalFormat rupiahFormat = new DecimalFormat("Rp #,###.##");
                    String formattedDiscVal = rupiahFormat.format(discVal);
                    
                    JOptionPane.showMessageDialog(null, "Yeay Kamu Dapat Potongan Harga Sebesar " + formattedDiscVal, "Berhasil Menggunakan Kode Promo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // recap harga
            // Label to display the result -- harga awal
        labelResult = new JLabel();
        labelResult.setFont(fontLabel);
        labelResult.setBounds(260, 280, 300, 30);
        float promoValue = getPromoVal();
        String promoDigunakan = "Promo Digunakan \t" + formatCost(promoValue);
        JLabel promoValLabel = new JLabel(promoDigunakan);
        promoValLabel.setBounds(260, 310, 300, 30);
        totalHarga = new JLabel();
        totalHarga.setBounds(260, 340, 300, 30);

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
        orderButton.setBounds(260, 500, 200, 30);
        orderButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (isAnyFieldEmpty()) {
                    JOptionPane.showMessageDialog(null, "Masih ada bagian yang kosong nih!", "Isi Dulu Datanya", JOptionPane.ERROR_MESSAGE);
                } else {
                    int userId = id;
                    char source = textAsal.getText().toUpperCase().charAt(0);
                    char destination = textTujuan.getText().toUpperCase().charAt(0);
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
        f.add(labelKodePromo);
        f.add(kodePromoField);
        f.add(usePromo);
        f.add(orderButton);
        f.add(labelResult);
        f.add(promoValLabel);
        f.add(totalHarga);
        
        f.setSize(500, 600);
        f.setLayout(null);

        
        f.setVisible(true);
    }

    private void calculateCost() {
        char source = textAsal.getText().toUpperCase().charAt(0);
        char destination = textTujuan.getText().toUpperCase().charAt(0);

        int baseCost = con.calculateCost(source, destination);
        String selectedVehicle = (String) boxPilihVehicle.getSelectedItem();

        if (baseCost != -1) {
            int finalCost = con.calculateFinalCost(baseCost, selectedVehicle);
            totalHargaValue = finalCost - promoVal;
            labelResult.setText("Rp. " + finalCost);
        } else {
            labelResult.setText("Biaya tidak dapat dihitung.");
        }
        totalHarga.setText("Tarif Total \t" + formatCost(totalHargaValue));
    }

    // format cost
    private String formatCost(float cost) {
        DecimalFormat rupiahFormat = new DecimalFormat("Rp #,###.##");
        return rupiahFormat.format(cost);
    }

     public float getPromoVal() {
        return promoVal;
    }
}
