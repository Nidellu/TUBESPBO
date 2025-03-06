package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tubespbo.Controller.Controller;

public class MainMenuPassanger {

    JFrame f;

    public MainMenuPassanger(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String nameDisplay = Controller.getInstance().getUsername(id);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JButton profileButton = FrameHandler.createButton("Check Profil", fontButton, 10, 80, 470, 30, e -> onProfile(id));

        JLabel intro = FrameHandler.createLabel("Selamat Datang di Josen " + nameDisplay + "!", font, 10, 10, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Kita jalan kemana yuk!", font2, 10, 30, 300, 30);

        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", null, 10, 50, 500, 20);

        JLabel border = FrameHandler.createLabel("", null, 30, 135, 425, 60);
        border.setBorder(BorderFactory.createLineBorder(Color.black));

        String strSaldo = String.valueOf(Controller.getInstance().getWallet(id));
        if (Controller.getInstance().getWallet(id) > 9999999) {
            strSaldo = "9999999+";
        }

        JLabel wallet = FrameHandler.createLabel("JOPAY: Rp. " + strSaldo, font2, 50, 150, 400, 30);

        // Create and configure buttons
        JButton topUp = FrameHandler.createButton("Top Up", fontButton, 340, 150, 100, 30, e -> onTopUp(id));
        JButton pesanRide = FrameHandler.createButton("Pesan JoRide", fontButton, 70, 220, 350, 30, e -> onOrderRide(id));
        JButton cekOrder = FrameHandler.createButton("Lihat Pesanan", fontButton, 70, 270, 350, 30, e -> onCekOrder(id));
        JButton logOut = FrameHandler.createButton("Log out", fontButton, 340, 500, 100, 30, e -> onLogOut());

        f.add((intro));
        f.add((intro2));
        f.add(border);
        f.add(wallet);
        f.add(topUp);
        f.add(profileButton);
        f.add(lineDiv);

        f.add(pesanRide);
        f.add(cekOrder);
        f.add(logOut);

        f.setLocationRelativeTo(null);
        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // Button actions

    private void onProfile(int id) {
        f.dispose();
        new PassangerProfile(id);
    }

    private void onTopUp(int id) {
        new MenuTopUp(id);
        f.dispose();
    }

    private void onOrderRide(int id) {
        f.dispose();
        new OrderRide(id);
    }

    private void onCekOrder(int id) {
        new CekOrder(id);
        f.dispose();
    }

    private void onLogOut() {
        f.dispose();
        new StartMenu();
    }
}
