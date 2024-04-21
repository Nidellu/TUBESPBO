package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tubespbo.Controller.Controller;

public class AddPromo {

    public AddPromo() {
        addHere();
    }

    private void addHere() {
        JFrame f = FrameHandler.createFrame("Tambah Promo Baru", 500, 600);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 13);  // buat atur font pada button
        Font fontLabel = new Font("Courier", Font.BOLD, 16);   // buat atur font pada label

        // Create main labels
        JLabel intro = FrameHandler.createLabel("Tambah Promo Baru", font, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Isi dulu datanya", font2, 30, 90, 300, 30);
        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", null, 10, 120, 500, 20);

        // Create input fields
        JLabel codePromoLabel = FrameHandler.createLabel("Input Promo Code ", fontLabel, 30, 160, 200, 30);
        JTextField codePromoField = FrameHandler.createTextField("", Color.WHITE, null, 255, 160, 200, 30);

        JLabel promoValLabel = FrameHandler.createLabel("Input Value ", fontLabel, 30, 190, 200, 30);
        JTextField promoValField = FrameHandler.createTextField("", Color.WHITE, null, 255, 190, 200, 30);

        JLabel expLabel = FrameHandler.createLabel("Berlaku Hingga ", fontLabel, 30, 240, 200, 30);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl expdatePicker = new JDatePickerImpl(datePanel);
        expdatePicker.setBounds(255, 240, 200, 30);

        // Create buttons
        JButton submitPromo = FrameHandler.createButton("Add Promo", fontButton, 40, 515, 400, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isAnyFieldEmpty()) {
                    FrameHandler.showErrorDialog("Masih ada bagian yang kosong nih!", "Isi Dulu Datanya");
                } else {
                    String codePromo = codePromoField.getText();
                    String promoVal = promoValField.getText();
                    float promoValFloat = Float.parseFloat(promoVal);
                    java.util.Date utilDate = (java.util.Date) expdatePicker.getModel().getValue();
                    Date expiredDate = new Date(utilDate.getTime());

                    if (codePromo.length() < 10) {
                        if (!codePromo.contains(" ")) {
                            if (promoValFloat <= 1.0) {
                                boolean valid = Controller.getInstance().addNewPromo(codePromo, promoValFloat, expiredDate);
                                if (valid) {
                                    FrameHandler.showInformationMessage("Promo Berhasil Ditambahkan!", "Yeay");
                                } else {
                                    FrameHandler.showErrorDialog("Gagal menambahkan promo!", "Upss");
                                }
                                f.dispose();
                                new SeeAndDeletePromo();
                            } else {
                                FrameHandler.showInformationMessage("Promo value tidak lebih dari 100%!", "Huff");
                            }
                        } else {
                            FrameHandler.showInformationMessage("Promo ga boleh ada spasi!", "Huff");
                        }
                    } else {
                        FrameHandler.showInformationMessage("Promo Lebih Dari 10 Huruf!", "Huff");
                    }
                }

            }
            private boolean isAnyFieldEmpty() {
                return codePromoField.getText().isEmpty()
                        || promoValField.getText().isEmpty()
                        || !(expdatePicker.getModel().isSelected());
            }
        });

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 100, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });

        // adding all elements into frame
        f.add(intro);
        f.add(intro2);
        f.add(lineDiv);
        f.add(codePromoLabel);
        f.add(codePromoField);
        f.add(promoValLabel);
        f.add(promoValField);
        f.add(expLabel);
        f.add(expdatePicker);
        f.add(backButton);
        f.add(submitPromo);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
