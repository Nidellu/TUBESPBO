package tubespbo.View;

import javax.swing.*;
import java.awt.*;

public class StartMenu {
    public StartMenu() {
        createMainMenu();
    }

    private void createMainMenu() {
        JFrame mainMenu = FrameHandler.createFrame("Selamat Datang Di Josen", 400, 600);
        mainMenu.setLayout(null);

        JLabel logoGojek = FrameHandler.createImageLabel("Picture Source\\Logo\\gojek-icon-512x512-dyy6mlv4.png", 130, 25, 120, 120);
        mainMenu.add(logoGojek);

        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font heading2 = new Font("Courier", Font.BOLD, 14);

        JLabel hello = FrameHandler.createLabel("Selamat Datang di Josen!", heading1, 70, 140, 300, 80);
        mainMenu.add(hello);

        JLabel intro = FrameHandler.createLabel("Mau kemana sayang? ", heading2, 118, 200, 250, 30);
        JLabel intro2 = FrameHandler.createLabel("Sini abang Josen yang anter", heading2, 98, 220, 250, 30);
        mainMenu.add(intro);
        mainMenu.add(intro2);

        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JButton buttonLogin = FrameHandler.createButton("Masuk", fontButton, 90, 320, 200, 30, e -> handleLogin(mainMenu));
        mainMenu.add(buttonLogin);

        JButton buttonRegis = FrameHandler.createButton("Belum ada akun? Daftar dulu", fontButton, 90, 360, 200, 30, e -> handleRegistration(mainMenu));
        mainMenu.add(buttonRegis);

        JButton buttonExit = FrameHandler.createButton("Exit", fontButton, 140, 480, 100, 30, e -> System.exit(0));
        mainMenu.add(buttonExit);

        mainMenu.setVisible(true);
    }

    private void handleLogin(JFrame mainMenu) {
        mainMenu.dispose();
        new LogIn();
    }

    private void handleRegistration(JFrame mainMenu) {
        mainMenu.dispose();
        new Registrasi();
    }
}

