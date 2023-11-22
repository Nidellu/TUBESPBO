package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        ArrayList<Promo> promos = new ArrayList<>();
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
        JTextField codePromoField = new JTextField();

        /// input promo value
        JLabel promoValLabel = new JLabel("Input Value ");
        promoValLabel.setFont(fontLabel);
        JTextField promoValField = new JTextField();
        

        // input promo expired date
        JLabel expLabel = new JLabel("Berlaku Hingga ");
        expLabel.setFont(fontLabel);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl expdatePicker = new JDatePickerImpl(datePanel);
        expdatePicker.setBounds(130, 100, 160, 30);

        // submit button
        JButton submitPromo = new JButton("Add Promo");
        submitPromo.setFont(fontButton);
        submitPromo.setBounds(100, 50, 160, 30);
        submitPromo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codePromo = codePromoField.getText();
                String promoVal = promoValField.getText();
                float promoValFloat = Float.parseFloat(promoVal);                
                Date expiredDate = (Date) expdatePicker.getModel().getValue();
                // Promo inpPromo = new Promo(codePromo, promoValFloat, expiredDate);
                // promos.add(inpPromo);
                cntrl.addNewPromo(promoVal, promoValFloat, expiredDate);
                f.dispose();
            }
        });

        // back to promo menu button
        JButton backButton = new JButton("Back to Admin Menu");
        backButton.setFont(fontButton);
        backButton.setBounds(170, 50, 160, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin(id);
            }
        });
    }
}
