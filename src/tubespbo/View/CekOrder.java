package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tubespbo.Controller.Controller;

public class CekOrder {

    public CekOrder(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        JFrame f = FrameHandler.createFrame("Your Frame Title", 500, 400);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);

        JLabel intro = FrameHandler.createLabel("Pesanan.", font, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Yang kamu order ada disini kok", font2, 30, 95, 300, 30);

        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", null, 10, 120, 500, 20);

        JButton berjalan = FrameHandler.createButton("Dalam Proses", new Font("Courier", Font.BOLD, 13), 60, 280, 350, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new OrderBerjalan(id);
            }
        });

        JButton riwayat = FrameHandler.createButton("Riwayat", new Font("Courier", Font.BOLD, 13), 60, 330, 350, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new OrderRiwayat(id);
            }
        });

        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 13), 10, 10, 85, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                String role = Controller.getInstance().getRolesUser(id);
                if (role.equalsIgnoreCase("Passanger")) {
                    new MainMenuPassanger(id);
                } else if (role.equalsIgnoreCase("Driver")) {
                    new MainMenuDriver(id);
                }
            }
        });

        f.add((backButton));

        f.add((intro));
        f.add((intro2));
        f.add(lineDiv);

        f.add(berjalan);
        f.add(riwayat);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
