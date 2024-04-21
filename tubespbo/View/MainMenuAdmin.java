package tubespbo.View;

import javax.swing.*;
import java.awt.*;

public class MainMenuAdmin {

    private final JFrame frame;

    public MainMenuAdmin() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        displayWelcomeMessage();
        addButtons();
        frame.setVisible(true);
    }

    private void displayWelcomeMessage() {
        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font text1 = new Font("Courier", Font.PLAIN, 16);

        JLabel intro = FrameHandler.createLabel("Selamat Datang Admin.", heading1, 10, 10, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Jangan Menyerah, tetap Semangat!", text1, 10, 30, 300, 30);
        frame.add(intro);
        frame.add(intro2);
    }

    private void addButtons() {
        Font buttonFont = new Font("Courier", Font.BOLD, 12);

        JButton logOutButton = FrameHandler.createButton("Log out", buttonFont, 340, 500, 100, 30, e -> onLogout());
        JButton verifButton = FrameHandler.createButton("Verifikasi Driver", buttonFont, 60, 195, 350, 30, e -> openVerificationDriverScreen());
        JButton findOrderButton = FrameHandler.createButton("Find Order", buttonFont, 60, 235, 350, 30, e -> openFindOrderScreen());
        JButton checkIncomeButton = FrameHandler.createButton("Cek Pendapatan", buttonFont, 60, 275, 350, 30, e -> openCheckIncomeScreen());
        JButton addPromoButton = FrameHandler.createButton("Tambah Promo", buttonFont, 60, 315, 350, 30, e -> openAddPromoScreen());
        JButton seePromoButton = FrameHandler.createButton("Lihat Semua Promo", buttonFont, 60, 355, 350, 30, e -> openSeePromoScreen());

        frame.add(logOutButton);
        frame.add(verifButton);
        frame.add(findOrderButton);
        frame.add(checkIncomeButton);
        frame.add(addPromoButton);
        frame.add(seePromoButton);
    }

    private void onLogout() {
        frame.dispose();
        new StartMenu();
    }

    private void openVerificationDriverScreen() {
        frame.dispose();
        new ListVerifikasiDriver();
    }

    private void openFindOrderScreen() {
        frame.dispose();
        new FindOrder();
    }

    private void openCheckIncomeScreen() {
        frame.dispose();
        new CekPendapatanAdmin();
    }

    private void openAddPromoScreen() {
        frame.dispose();
        new AddPromo();
    }

    private void openSeePromoScreen() {
        frame.dispose();
        new SeeAndDeletePromo();
    }
}
