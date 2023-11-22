package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tubespbo.Controller.Controller;
import tubespbo.Model.Promo;

public class AddPromo {
    public AddPromo(int id) {
        addHere(id);
    } 

    private void addHere(int id) {
        Controller cntrl = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // buat label utama
        JLabel intro = new JLabel("Tambah Promo Baru");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Isi dulu datanya");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(30, 70, 400, 30);
        intro2.setBounds(30, 90, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);  // buat atur font pada button
        Font fontLabel = new Font("Courier", Font.BOLD, 16);   // buat atur font pada label
        JLabel lineDiv = new JLabel("_______________________________" // buat separator
                + "__________________________________________");
        lineDiv.setBounds(10, 120, 500, 20);


        // input promo code
        JLabel codePromoLabel = new JLabel("Input Promo Code ");
        codePromoLabel.setFont(fontLabel);
        codePromoLabel.setBounds(30, 130, 200, 30);
        JTextField codePromoField = new JTextField();
        codePromoField.setBounds(260, 130, 200, 30);

        /// input promo value
        JLabel promoValLabel = new JLabel("Input Value ");
        promoValLabel.setFont(fontLabel);
        promoValLabel.setBounds(30, 170, 200, 30);
        JTextField promoValField = new JTextField();
        promoValField.setBounds(260, 170, 200, 30);
        

        // input promo expired date
        JLabel expLabel = new JLabel("Berlaku Hingga ");
        expLabel.setFont(fontLabel);
        expLabel.setBounds(30, 210, 200, 30);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl expdatePicker = new JDatePickerImpl(datePanel);
        expdatePicker.setBounds(260, 210, 200, 30);

        // submit button
        JButton submitPromo = new JButton("Add Promo");
        submitPromo.setFont(fontButton);
        submitPromo.setBounds(150, 280, 200, 30);
        submitPromo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isAnyFieldEmpty()) {
                    JOptionPane.showMessageDialog(null, "Masih ada bagian yang kosong nih!", "Isi Dulu Datanya", JOptionPane.ERROR_MESSAGE);
                } else {
                    String codePromo = codePromoField.getText();
                    String promoVal = promoValField.getText();
                    float promoValFloat = Float.parseFloat(promoVal);                
                    java.util.Date utilDate = (java.util.Date) expdatePicker.getModel().getValue();
                    Date expiredDate = new Date(utilDate.getTime());

                    boolean valid = cntrl.addNewPromo(codePromo, promoValFloat, expiredDate);
                    if (valid == true) {
                        JOptionPane.showMessageDialog(null, "PROMO BERHASIL DITAMBAHKAN!", "Yeay", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "GAGAL MENAMBAHKAN PROMO!", "Upss", JOptionPane.ERROR_MESSAGE);
                    }
                    f.dispose();
                    new SeeAndDeletePromo(id);
                }
            }

            private boolean isAnyFieldEmpty() {
                return codePromoField.getText().isEmpty() || 
                       promoValField.getText().isEmpty() ||
                       !(expdatePicker.getModel().isSelected());
            }
        });

        // back to promo menu button
        JButton backButton = new JButton("Back to Admin Menu");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin(id);
            }
        });

        // adding all elements into frame
        f.add(intro);
        f.add(intro2);
        f.add(codePromoLabel);
        f.add(codePromoField);
        f.add(promoValLabel);;
        f.add(promoValField);
        f.add(expLabel);
        f.add(expdatePicker);
        f.add(backButton);
        f.add(submitPromo);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }
}
