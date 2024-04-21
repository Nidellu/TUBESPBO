package tubespbo.View;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tubespbo.Controller.Controller;

public class CekPendapatanAdmin {

    JFrame f;

    public CekPendapatanAdmin() {
        showDataScreen();
    }

    private void showDataScreen() {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        Font font3 = new Font("Courier", Font.BOLD, 72);
        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel intro = FrameHandler.createLabel("Total Pendapatan.", font, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Selalu bersyukur ya!", font2, 30, 95, 400, 30);
        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 120, 465, 20);
        JLabel time = FrameHandler.createLabel("Total Order Josen: ", font2, 30, 150, 300, 30);
        JLabel idForShow = FrameHandler.createLabel(Controller.getInstance().getOrderCount() + "", font3, 30, 180, 300, 90);
        JLabel payDetail = FrameHandler.createLabel("Dengan total pendapatan sebanyak:", font2, 30, 270, 300, 30);
        JLabel payRaw = FrameHandler.createLabel("Rp. " + Controller.getInstance().getOrderCount() * 2000, font, 30, 300, 300, 30);

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 85, 30, e -> onBack());

        f.add((intro));
        f.add(intro2);

        f.add(time);
        f.add(idForShow);

        f.add(payDetail);
        f.add(payRaw);

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void onBack() {
        f.dispose();
        new MainMenuAdmin();
    }
}
