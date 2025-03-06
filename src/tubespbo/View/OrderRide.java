package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;

public class OrderRide {

    JFrame f;

    public OrderRide(int id) {
        createForm(id);
    }

    private JLabel labelAsal, labelTujuan;
    private JTextField textAsal, textTujuan;

    private void createForm(int id) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 13);
        Font fontLabel = new Font("Courier", Font.BOLD, 16);

        ArrayList<Passanger> pass = Controller.getInstance().getPassangerByID(id);

        JLabel intro = FrameHandler.createLabel("Halo, " + pass.get(pass.size() - 1).getUser_name() + "!", heading1, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Mau kemana kali ini sayy?", text1, 30, 90, 300, 30);

        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", heading1, 10, 110, 460, 20);

        labelAsal = FrameHandler.createLabel("Lokasi jemput ", fontLabel, 30, 150, 200, 30);
        textAsal = FrameHandler.createTextField(null, Color.WHITE, fontLabel, 250, 150, 200, 30);
        labelTujuan = FrameHandler.createLabel("Tujuan ", fontLabel, 30, 190, 150, 30);
        textTujuan = FrameHandler.createTextField(null, Color.WHITE, fontLabel, 250, 190, 200, 30);

        JLabel labelKodePromo = FrameHandler.createLabel("Masukan Kode Promo", fontLabel, 30, 230, 200, 30);
        JTextField kodePromoField = FrameHandler.createTextField(null, Color.WHITE, fontLabel, 250, 230, 150, 30);

        JButton usePromo = FrameHandler.createButton(">", new Font("Arial", Font.PLAIN, 12), 400, 230, 50, 30, e -> usePromo(kodePromoField.getText()));
        JButton nextButton = FrameHandler.createButton("Next", new Font("Courier", Font.BOLD, 13), 40, 495, 400, 30, e -> next(id, kodePromoField.getText()));
        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 90, 30, e -> onBack(id));

        f.add((intro));
        f.add((intro2));
        f.add(labelAsal); // add asal
        f.add(textAsal);
        f.add(labelTujuan); // add tujuan
        f.add(textTujuan);

        f.add(labelKodePromo); // add promo
        f.add(kodePromoField);
        f.add(usePromo); // use promo
        f.add(backButton); // add back button
        f.add(lineDiv);
        f.add(nextButton);

        f.setSize(500, 600);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void usePromo(String kodePromo) {
        if (kodePromo.isEmpty()) {
            FrameHandler.showErrorDialog("Input dulu kode promonya!", "Diisi Dulu Yaa");
        } else {
            if (Controller.getInstance().findPromo(kodePromo)) {
                FrameHandler.showInformationMessage("Promo berhasil ditemukan", "Diisi Dulu Yaa");
            } else {
                FrameHandler.showInformationMessage("Kode belum bisa ditemukan", "Berhasil Menggunakan Kode Promo");
            }
        }
    }

    private void next(int id, String kodePromo) {
        if (!textTujuan.getText().isEmpty() || !textAsal.getText().isEmpty()) {
            if (textTujuan.getText().length() <= 1 || textAsal.getText().length() <= 1) {
                String source = textAsal.getText().toUpperCase();
                String destination = textTujuan.getText().toUpperCase();
                if (!kodePromo.isEmpty()) {
                    if (Controller.getInstance().findPromo(kodePromo)) {
                        f.dispose();
                        new OrderRideNext(source, destination, id, kodePromo);
                    } else {
                        FrameHandler.showInformationMessage("Kode belum di cek kayaknya nih", "Berhasil Menggunakan Kode Promo");
                    }
                } else {
                    f.dispose();
                    new OrderRideNext(source, destination, id, "");
                }
            } else {
                FrameHandler.showWarningMessage("Pilihan kota cuma satu huruf loh", "");
            }
        } else {
            FrameHandler.showWarningMessage("Tujuan atau Asal tidak boleh kosong!", "");
        }
    }

    private void onBack(int id) {
        f.dispose();
        new MainMenuPassanger(id);
    }
}
