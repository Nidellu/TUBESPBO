<<<<<<< HEAD
package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.Promo;

public class SeeAndDeletePromo {

    JFrame frame;

    public SeeAndDeletePromo() {
        showResult();
    }

    private void showResult() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Promo> promolList = Controller.getInstance().getPromoList();

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 18);
        JLabel intro = FrameHandler.createLabel("Daftar List Promo.", heading1, 30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");

        lineDiv.setBounds(10, 100, 460, 20);

        if (promolList.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... promo masih kosong nih :'(");
            ingpo.setFont(text1);
            ingpo.setBounds(110, 300, 400, 30);
            frame.add(ingpo);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 445, 420);
        frame.getContentPane().add(scrollPane);

        JPanel containerOrder = new JPanel();
        scrollPane.setViewportView(containerOrder);
        containerOrder.setLayout(new BorderLayout(0, 0));

        JPanel promoContainer = new JPanel();
        containerOrder.add(promoContainer, BorderLayout.NORTH);
        promoContainer.setLayout(new GridLayout(0, 1, 0, 1));

        for (Promo prm : promolList) {
            JPanel promoPanel = createPromoPanel(prm);
            promoContainer.add(promoPanel);
        }

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 85, 30, e -> onBack());

        frame.add((intro));
        frame.add(backButton);
        frame.add(lineDiv);

        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void delete(int idPromo) {
        int choice = FrameHandler.showConfirmationDialog("Yakin mau dihapus?", "Konfirmasi");

        if (choice == JOptionPane.YES_OPTION) {
            if (Controller.getInstance().deletePromo(idPromo)) {
                FrameHandler.showInformationMessage("Promo berhasil dihapus!", "Yeay");
                frame.dispose();
                new SeeAndDeletePromo();
            } else {
                FrameHandler.showErrorDialog("Promo gagal dihapus!", "Upss");
            }
        } else {
            FrameHandler.showInformationMessage("Promo batal dihapus!", "");
        }
    }

    private void onBack() {
        frame.dispose();
        new MainMenuAdmin();
    }

    private JPanel createPromoPanel(Promo prm) {
        Font heading2 = new Font("Courier", Font.BOLD, 14);
        Font heading3 = new Font("Courier", Font.BOLD, 13);
        Font heading4 = new Font("Courier", Font.BOLD, 11);

        String statPromo = "";
        JPanel promoPanel = new JPanel();
        promoPanel.setPreferredSize(new Dimension(300, 60));
        promoPanel.setLayout(null);

        promoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        int idPromo = prm.getPromoID();

        JLabel promoCode = FrameHandler.createLabel("Kode Promo : " + prm.getPromoCode(), heading3, 10, 5, 200, 25);
        promoCode.setBorder(null);
        promoPanel.add(promoCode);

        JLabel promoVaField = FrameHandler.createLabel(prm.getExpired() + "", heading4, 343, 5, 70, 20);
        promoVaField.setBorder(null);
        promoPanel.add(promoVaField);

        JLabel expField = FrameHandler.createLabel(" Nilai Promo " + prm.getPromoValue(), heading4, 10, 30, 100, 25);
        expField.setBorder(null);
        promoPanel.add(expField);

        // buat status
        if (Controller.getInstance().checkPromoValidation(idPromo) == true) {
            statPromo = "Masih Berlaku";
        } else {
            statPromo = "Expired";
        }
        JLabel status = FrameHandler.createLabel(statPromo, heading3, 200, 27, 150, 25);
        status.setBorder(null);
        promoPanel.add(status);

        JButton deleteBtn = FrameHandler.createButton("Delete", heading2, 330, 28, 90, 25, e -> delete(idPromo));
        promoPanel.add(deleteBtn);
        return promoPanel;
    }
}
=======
package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.Promo;

public class SeeAndDeletePromo {

    public SeeAndDeletePromo() {
        showResult();
    }

    private void showResult() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // list of promo yang di get pakai controller
        ArrayList<Promo> promolList = Controller.getInstance().getPromoList();

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);
        Font font4 = new Font("Courier", Font.BOLD, 14);

        JLabel intro = new JLabel("Daftar List Promo.");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");

        lineDiv.setBounds(10, 100, 460, 20);

        if (promolList.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... promo masih kosong nih :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(110, 300, 400, 30);
            f.add(ingpo);
        }

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 445, 420);
        f.getContentPane().add(scrollPane);


        JPanel containerOrder = new JPanel();
        scrollPane.setViewportView(containerOrder);
        containerOrder.setLayout(new BorderLayout(0, 0));

        JPanel promoContainer = new JPanel();
        containerOrder.add(promoContainer, BorderLayout.NORTH);
        promoContainer.setLayout(new GridLayout(0, 1, 0, 1));
        promoContainer.setBackground(Color.gray);

        for (Promo prm : promolList) {
            String statPromo ="";
            JPanel promoPanel = new JPanel();
            promoPanel.setFont(font2);
            promoPanel.setPreferredSize(new Dimension(300,60));
            promoContainer.add(promoPanel);
            promoPanel.setLayout(null);

            promoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            int idPromo = prm.getPromoID();

            JLabel promoCode = new JLabel("Kode Promo : " + prm.getPromoCode());
            promoCode.setBounds(10, 5, 200, 25);
            promoCode.setBorder(null); 
            promoPanel.add(promoCode);

            JLabel promoVaField = new JLabel(prm.getExpired() + "");
            promoVaField.setBounds(343, 5, 70, 20);
            promoVaField.setBorder(null);
            promoPanel.add(promoVaField);

            JLabel expField = new JLabel(" Nilai Promo " + prm.getPromoValue());
            expField.setBounds(10, 30, 100, 25);
            expField.setBorder(null);
            promoPanel.add(expField);

            // buat status
            if (Controller.getInstance().checkPromoValidation(idPromo) == true) {
                statPromo = "Masih Berlaku";
            } else {
                statPromo = "Expired";
            }
            JLabel status = new JLabel(statPromo);
            status.setFont(font4);
            status.setBounds(200, 27, 150, 25);
            status.setBorder(null);
            promoPanel.add(status);

            JButton deleteBtn = new JButton("Delete");
            deleteBtn.setBounds(330, 28, 90, 25);
            deleteBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "Yakin mau dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    
                    if (choice == JOptionPane.YES_OPTION) {
                        if (Controller.getInstance().deletePromo(idPromo) == true) {
                            JOptionPane.showMessageDialog(null, "Promo berhasil dihapus!", "Yeay", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Promo gagal dihapus!", "Upss", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } else {
                       JOptionPane.showMessageDialog(null, "Promo batal dihapus!", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            promoPanel.add(deleteBtn);
        }

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });

        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
>>>>>>> master
