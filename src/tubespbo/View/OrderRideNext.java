package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;
import tubespbo.Model.Passanger;

public class OrderRideNext {

    public OrderRideNext(String asal, String tujuan, int id, String promo) {
        showDataScreen(asal, tujuan, id, promo);
    }

    JFrame f;
    private JLabel admin, labelPilihVehicle, hasilPromo, labelResult, totalHarga;
    private JLabel detail, biayaA, biayaB, biayaC, biayaD;
    private JComboBox<String> boxPilihVehicle;
    private float promoVal = 0.0f;
    private float totalHargaValue = 0.0f;
    private float finalCost = 0.0f;

    private void showDataScreen(String asal, String tujuan, int id, String promo) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Passanger> pass = Controller.getInstance().getPassangerByID(id);

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 13);
        Font fontLabel = new Font("Courier", Font.BOLD, 16);

        JLabel intro = FrameHandler.createLabel("Halo, " + pass.get(pass.size() - 1).getUser_name() + "!", heading1, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Mau kemana kali ini sayy?", text1, 30, 90, 300, 30);

        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", null, 10, 110, 460, 20);
        JLabel asalLabel = FrameHandler.createLabel("Asal: ", text1, 30, 160, 200, 30);
        JLabel tujuanLabel = FrameHandler.createLabel("Tujuan: ", text1, 30, 190, 200, 30);
        JLabel promoLabel = FrameHandler.createLabel("Kode Promo: ", text1, 30, 220, 200, 30);

        JLabel asalGet = FrameHandler.createLabel(asal, text1, 250, 160, 200, 30);
        asalGet.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel tujuanGet = FrameHandler.createLabel(tujuan, text1, 250, 190, 200, 30);
        tujuanGet.setHorizontalAlignment(SwingConstants.RIGHT);

        String promoField = "";
        if (promo.isEmpty()) {
            promoField = "-";
        } else {
            promoField = promo;
        }

        JLabel promoGet = FrameHandler.createLabel(promoField, text1, 250, 220, 200, 30);
        promoGet.setHorizontalAlignment(SwingConstants.RIGHT);

        f.add(asalLabel);
        f.add(tujuanLabel);
        f.add(promoLabel);
        f.add(asalGet);
        f.add(tujuanGet);
        f.add(promoGet);

        labelPilihVehicle = FrameHandler.createLabel("Pilih Kendaraan ", fontLabel, 30, 280, 200, 30);
        String listKendaraan[] = {"Mobil", "Motor"};
        boxPilihVehicle = new JComboBox(listKendaraan);
        boxPilihVehicle.setSelectedItem(null);
        boxPilihVehicle.setBounds(250, 280, 200, 30);
        boxPilihVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCost(asal, tujuan, promo);
            }
        });

        detail = FrameHandler.createLabel(null, fontLabel, 30, 330, 200, 30);
        biayaA = FrameHandler.createLabel(null, text1, 30, 360, 200, 30);
        biayaB = FrameHandler.createLabel(null, text1, 30, 390, 200, 30);
        biayaC = FrameHandler.createLabel(null, text1, 30, 420, 200, 30);
        biayaD = FrameHandler.createLabel(null, text1, 30, 450, 200, 30);

        labelResult = FrameHandler.createLabel(null, text1, 250, 360, 200, 30);
        labelResult.setHorizontalAlignment(SwingConstants.RIGHT);
        admin = FrameHandler.createLabel(null, text1, 250, 390, 200, 30);
        admin.setHorizontalAlignment(SwingConstants.RIGHT);
        hasilPromo = FrameHandler.createLabel(null, text1, 250, 420, 200, 30);
        hasilPromo.setHorizontalAlignment(SwingConstants.RIGHT);
        totalHarga = FrameHandler.createLabel(null, text1, 250, 450, 200, 30);
        totalHarga.setHorizontalAlignment(SwingConstants.RIGHT);

        //back button
        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 90, 30, e -> onBack(id));

        // check user's wallet -- if (user's wallet < harga akhir) --> order button disable
        // order button
        JButton orderButton = FrameHandler.createButton("Pesan Sekarang", fontButton, 40, 500, 400, 30, e -> order(id, asal, tujuan, promo));

        f.add((intro));
        f.add((intro2));
        f.add(labelPilihVehicle); // add choose vehicle
        f.add(boxPilihVehicle);
        f.add(backButton); // add back button
        f.add(orderButton); // add order button
        f.add(lineDiv);
        f.add(orderButton); // add order button
        f.add(labelResult); // add harga
        f.add(admin);
        f.add(hasilPromo); // add show promo uses
        f.add(totalHarga); // add harga final
        f.add(detail);
        f.add(biayaA);
        f.add(biayaB);
        f.add(biayaC);
        f.add(biayaD);

        f.setLocationRelativeTo(null);
        f.setSize(500, 600);
        f.setLayout(null);

        f.setVisible(true);
    }

    private void calculateCost(String asal, String tujuan, String kodePromo) {
        float disc = Controller.getInstance().getPromoVal(kodePromo);
        char source = asal.charAt(0);
        char destination = tujuan.charAt(0);

        int baseCost = Controller.getInstance().calculateCost(source, destination);
        String selectedVehicle = (String) boxPilihVehicle.getSelectedItem();

        if (baseCost != -1) {
            int hasil = Controller.getInstance().calculateFinalCost(baseCost, selectedVehicle);
            float afterDisc = (hasil + 2000) * disc;
            finalCost = hasil;
            totalHargaValue = (hasil + 2000) - afterDisc;
            hasilPromo.setText("Rp. " + afterDisc);
            labelResult.setText("Rp. " + finalCost);
            admin.setText("Rp. 2000");
        } else {
            labelResult.setText("Biaya tidak dapat dihitung.");
        }

        detail.setText("Detail Pembayaran");
        biayaA.setText("Biaya perjalanan");
        biayaB.setText("Biaya jasa aplikasi");
        biayaC.setText("Diskon Voucher");
        biayaD.setText("Total");
        totalHarga.setText("" + formatCost(totalHargaValue));
    }

    private String formatCost(float cost) {
        DecimalFormat rupiahFormat = new DecimalFormat("Rp #,###.##");
        return rupiahFormat.format(cost);
    }

    public float getPromoVal() {
        return promoVal;
    }

    private void onBack(int id) {
        f.dispose();
        new MainMenuPassanger(id);
    }

    private void order(int id, String asal, String tujuan, String promo) {
        if (isAnyFieldEmpty()) {
            FrameHandler.showErrorDialog("Masih ada bagian yang kosong nih!", "Isi Dulu Datanya");
        } else {
            if (Controller.getInstance().getWallet(id) < totalHargaValue) {
                int choice = FrameHandler.showConfirmationDialog("Saldo kamu ga cukup loh, mau top up?", "Konfirmasi");
                if (choice == JOptionPane.YES_OPTION) {
                    FrameHandler.showInformationMessage("Ke menu top up!", "");
                    f.dispose();
                    new MenuTopUp(id);
                } else {
                    FrameHandler.showInformationMessage("Ke menu utama!", "");
                    f.dispose();
                    new MainMenuPassanger(id);
                }
            } else {
                String jenisKendaraan = boxPilihVehicle.getSelectedItem().toString();
                int idPromo = Controller.getInstance().getPromoIdByCode(promo);

                Driver drv = Controller.getInstance().getDriverAvailable(jenisKendaraan);
                if (drv == null) {
                    FrameHandler.showErrorDialog("Tidak Dapat Menemukan Driver!", "Yahh Maap Yahh");
                } else {
                    boolean status = Controller.getInstance().createUserOrder(id, idPromo, asal, tujuan, finalCost, totalHargaValue, drv);
                    if (status) {
                        FrameHandler.showInformationMessage("Kamu Sudah Dalam Pesanan!", "Yeayy");
                        f.dispose();
                        new OrderBerjalan(id);
                    } else {
                        FrameHandler.showErrorDialog("Pesanan Kamu Gagal DiProses!", "Yahh Maap Yahh");
                    }
                }
            }
        }
    }

    private boolean isAnyFieldEmpty() {
        return boxPilihVehicle.getSelectedItem() == null;
    }

}
