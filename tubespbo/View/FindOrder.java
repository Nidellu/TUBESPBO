package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;

public class FindOrder {

    public FindOrder() {
        showDataScreen();
    }

    private void showDataScreen() {
        JFrame f = FrameHandler.createFrame("Your Frame Title", 600, 600);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);

        JLabel intro = FrameHandler.createLabel("Cari Order.", font, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Cari semua orderan user", font2, 30, 95, 300, 30);

        JLabel lineDiv = FrameHandler.createLabel("__________________________________________________", null, 10, 120, 500, 20);

        JLabel idOrder = FrameHandler.createLabel("Masukkan ID Order : ", null, 30, 160, 200, 30);
        JTextField inputOrder = FrameHandler.createTextField("", Color.WHITE, null, 255, 160, 200, 30);

        JButton buttonCari = FrameHandler.createButton("Cari", new Font("Courier", Font.BOLD, 12), 40, 515, 400, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String thisID = inputOrder.getText();
                int getID = Integer.parseInt(thisID);
                boolean found = Controller.getInstance().getOrder(getID);
                if (found) {
                    f.dispose();
                    new DetailOrderAdmin(1, getID, 0);
                } else {
                    FrameHandler.showWarningMessage("Order tidak ditemukan", "Huff");
                }
            }
        });

        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 12), 10, 10, 85, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });
        f.add(backButton);

        f.add((intro));
        f.add((intro2));
        f.add(lineDiv);

        f.add(idOrder);
        f.add(inputOrder);
        f.add(buttonCari);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
