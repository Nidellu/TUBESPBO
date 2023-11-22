package tubespbo.View;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Order;
import tubespbo.Model.Passanger;
import tubespbo.Model.Promo;

public class SeeAndDeletePromo {

    public SeeAndDeletePromo(int id) {
        showResult(id);
    }

    private void showResult(int id) {
        Controller cntrl = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // list of promo yang di get pakai controller
        ArrayList<Promo> promolList = cntrl.getPromoList();

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);

        JLabel intro = new JLabel("DAFTAR PROMO");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv.setBounds(10, 100, 968, 20);

        if (promolList.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... promo masih kosong nih :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(360, 280, 400, 30);
            f.add(ingpo);
        }

        int height = (promolList.size()) * 65;

        if (height > 370) {
            height = 370;
        }

        JLayeredPane promoContainer = new JLayeredPane();
        promoContainer.setLayout(new BoxLayout(promoContainer, BoxLayout.Y_AXIS));
        promoContainer.setBounds(30, 130, 415, height);

        for (Promo prm : promolList) {
            String statPromo ="";
            JPanel promoPanel = new JPanel();

            promoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            int idPromo = prm.getPromoID();

            JTextField promoCode = new JTextField("Kode Promo : " + prm.getPromoCode() + "\t\t");
            promoCode.setBorder(null);
            promoCode.setEditable(false);
            promoPanel.add(promoCode);

            JTextField promoVaField = new JTextField(prm.getExpired() + "\t               ");
            promoVaField.setBorder(null);
            promoVaField.setEditable(false);
            promoPanel.add(promoVaField);

            JTextField expField = new JTextField(" Nilai Promo " + prm.getPromoValue() + "\t");
            expField.setBorder(null);
            expField.setEditable(false);
            promoPanel.add(expField);

            // buat status
            if (cntrl.checkPromoValidation(idPromo) == true) {
                statPromo = "Masih Berlaku";
            } else {
                statPromo = "Expired";
            }
            JTextField status = new JTextField(statPromo + "\t\t            ");
            status.setBorder(null);
            status.setEditable(false);
            promoPanel.add(status);

            JButton deleteBtn = new JButton("Delete");
            deleteBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "YAKIN MAU HAPUS PRMO INI?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    
                    if (choice == JOptionPane.YES_OPTION) {
                        
                        JOptionPane.showMessageDialog(null, "PROMO BERHASIL DIHAPUS!", "Yeay", JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                       JOptionPane.showMessageDialog(null, "PROMO BATAL DIHAPUS!", "Yeay", JOptionPane.INFORMATION_MESSAGE);
                    }
                    // f.dispose();
                }
            });
            promoPanel.add(deleteBtn);

            promoPanel.setOpaque(true);

            promoContainer.add(promoPanel);
        }

        JLabel lineDiv2 = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv2.setBounds(10, 500, 968, 20);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new CekOrder(id);
            }
        });

        f.add(promoContainer);
        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);
        f.add(lineDiv2);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }
}
