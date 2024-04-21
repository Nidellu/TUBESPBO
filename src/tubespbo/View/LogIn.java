package tubespbo.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;

public class LogIn {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogIn() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        Font headingFont = new Font("Courier", Font.BOLD, 20);
        Font labelFont = new Font("Courier", Font.BOLD, 16);
        Font textFont = new Font("Courier", Font.PLAIN, 16);
        Font buttonFont = new Font("Courier", Font.BOLD, 12);
        
        JLabel intro = FrameHandler.createLabel("Selamat Datang di Josen!", headingFont, 10, 10, 300, 30);
        JLabel intro2 = FrameHandler.createLabel("Satu langkah lagi untuk masuk", textFont, 10, 30, 250, 30);
        
        JLabel userLabel = FrameHandler.createLabel("Username", labelFont, 20, 100, 100, 30);
        usernameField = FrameHandler.createTextField(null, Color.white, null, 160, 100, 200, 30);

        JLabel passLabel = FrameHandler.createLabel("Password", labelFont, 20, 130, 100, 30);
        passwordField = FrameHandler.createPasswordField(160, 130, 200, 30);

        frame.add(intro);
        frame.add(intro2);
        frame.add(userLabel);
        frame.add(usernameField);
        frame.add(passLabel);
        frame.add(passwordField);

        JButton loginButton = FrameHandler.createButton("Masuk", new Font("Courier", Font.BOLD, 12), 225, 300, 100, 30, e -> onLogin());
        frame.add(loginButton);

        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 12), 55, 300, 100, 30, e -> onBack());
        frame.add(backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onLogin() {
        String password = String.valueOf(passwordField.getPassword());
        boolean found = Controller.getInstance().logIn(usernameField.getText(), password);
        if (found) {
            frame.dispose();
            int id = Controller.getInstance().getIDUser(usernameField.getText());
            String role = Controller.getInstance().getRolesUser(id);
            switch (role.toLowerCase()) {
                case "admin":
                    new MainMenuAdmin();
                    break;
                case "passanger":
                    new MainMenuPassanger(id);
                    break;
                case "driver":
                    new MainMenuDriver(id);
                    break;
                default:
                    FrameHandler.showWarningMessage("Peran pengguna tidak valid.", "");
                    new LogIn();
            }
        } else {
            FrameHandler.showWarningMessage("Maaf Akun tidak ditemukan", "");
        }
    }

    private void onBack() {
        frame.dispose();
        new StartMenu();
    }
}

