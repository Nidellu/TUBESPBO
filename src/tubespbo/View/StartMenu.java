<<<<<<< HEAD
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

=======

package tubespbo.View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class StartMenu {
    public StartMenu(){
        pilihOpsi();
    }

    private void pilihOpsi() {
        JFrame mainMenu = new JFrame("Selamat Datang Di Josen");
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon originalFotoIcon = new ImageIcon("Picture Source\\Logo\\gojek-icon-512x512-dyy6mlv4.png");
        Image originalFoto = originalFotoIcon.getImage();
        Image resizedFoto = originalFoto.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon resizedFotoIcon = new ImageIcon(resizedFoto);
        JLabel logoGojek = new JLabel(resizedFotoIcon);
        logoGojek.setBounds(40, -60, 300, 300);
        
        JLabel hello = new JLabel("Selamat Datang di Josen!");
        Font font = new Font("Courier", Font.BOLD,20);
        hello.setFont(font);
        hello.setBounds(70, 140, 300, 80);
        
        JLabel intro = new JLabel("Mau kemana sayang? ");
        JLabel intro2 = new JLabel("Sini abang Josen yang anter");
        Font font2 = new Font("Courier", Font.PLAIN,14);
        intro.setFont(font2);
        intro2.setFont(font2);
        intro.setBounds(118, 200, 250, 30);
        intro2.setBounds(98, 220, 250, 30);
        
        Font fontButton = new Font("Courier", Font.BOLD,12);
        
        JButton buttonLogin = new JButton("Masuk");
        buttonLogin.setFont(fontButton);
        buttonLogin.setBounds(90, 320, 200, 30);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.dispose();
                new LogIn();
            }
        });
        
        JButton buttonRegis = new JButton("Belum ada akun? Daftar dulu");
        buttonRegis.setFont(fontButton);
        buttonRegis.setBounds(90, 360, 200, 30);
        buttonRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registrasi();
                mainMenu.dispose();
            }
        });
        
        JButton buttonExit = new JButton("Exit");
        buttonExit.setFont(fontButton);
        buttonExit.setBounds(140, 480, 100, 30); 
        buttonExit.addActionListener((event) -> System.exit(0));

        mainMenu.add(logoGojek);
        mainMenu.add(hello);
        mainMenu.add(intro);
        mainMenu.add(intro2);
        
        mainMenu.add(buttonLogin);
        mainMenu.add(buttonRegis);
        mainMenu.add(buttonExit);

        mainMenu.setSize(400, 600);
        mainMenu.setLayout(null);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
    }

}
>>>>>>> master
