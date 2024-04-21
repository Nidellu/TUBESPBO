package tubespbo.View;

import javax.swing.*;
import java.awt.*;

import tubespbo.Controller.Controller;

public class Registrasi {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> categoryComboBox;

    public Registrasi() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame("Form Registrasi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        Font heading1 = new Font("Courier", Font.BOLD, 20);
        Font heading2 = new Font("Courier", Font.BOLD, 16);
        Font text1 = new Font("Courier", Font.PLAIN, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel intro = FrameHandler.createLabel("Selamat Datang di Josen!", heading1, 10, 10, 300, 30);
        JLabel intro2 = FrameHandler.createLabel("Silahkan isi data mu ya!", text1, 10, 40, 300, 30);

        JLabel labelNama = FrameHandler.createLabel("Username:", heading2, 10, 80, 200, 30);
        usernameField = FrameHandler.createTextField(null, Color.WHITE, null, 200, 80, 250, 30);

        JLabel labelPassword = FrameHandler.createLabel("Password:", heading2, 10, 120, 200, 30);
        passwordField = FrameHandler.createPasswordField(200, 120, 250, 30);

        JLabel labelCategory = FrameHandler.createLabel("Category:", heading2, 10, 160, 200, 30);
        String[] categories = {"Driver", "Passanger"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setBounds(200, 160, 250, 30);

        JButton nextButton = FrameHandler.createButton("Lanjut", fontButton, 260, 300, 150, 30, e -> onNext());

        JButton backButton = FrameHandler.createButton("Back to Main Menu", fontButton, 60, 300, 150, 30, e -> onBack());

        frame.add(intro);
        frame.add(intro2);
        frame.add(labelNama);
        frame.add(usernameField);
        frame.add(labelPassword);
        frame.add(passwordField);
        frame.add(labelCategory);
        frame.add(categoryComboBox);
        frame.add(nextButton);
        frame.add(backButton);

        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onNext() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String category = categoryComboBox.getSelectedItem().toString();

        if (username.isEmpty() || password.isEmpty() || category.isEmpty()) {
            FrameHandler.showWarningMessage("Data belum lengkap", "");
        } else {
            boolean isUsernameTaken = Controller.getInstance().getByUserName(username);
            if (!isUsernameTaken) {
                if (category.equalsIgnoreCase("Passanger")) {
                    new RegistrasiPassanger(username, password, category);
                } else if (category.equalsIgnoreCase("Driver")) {
                    new RegistrasiDriver(username, password);
                }
                frame.dispose();
            } else {
                FrameHandler.showWarningMessage("Username sudah digunakan", "");
            }
        }
    }

    private void onBack() {
        frame.dispose();
        new StartMenu();
    }
}

