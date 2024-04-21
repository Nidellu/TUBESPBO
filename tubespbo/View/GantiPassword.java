package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import tubespbo.Controller.Controller;

public class GantiPassword {

    public GantiPassword(int id, String password) {
        showDataScreen(id, password);
    }

    private void showDataScreen(int id, String passwordCheck) {
        JFrame frame = FrameHandler.createFrame("Mengubah Kata Sandi", 500, 600);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel intro = FrameHandler.createLabel("Mengubah Kata Sandi.", new Font("Courier", Font.BOLD, 20), 30, 70, 400, 30);
        frame.add(intro);

        JLabel lineDiv = FrameHandler.createLabel("_______________________________"
                + "__________________________________________", null, 10, 100, 500, 20);
        frame.add(lineDiv);

        JLabel password = FrameHandler.createLabel("Password Lama", new Font("Courier", Font.BOLD, 16), 30, 130, 200, 30);
        frame.add(password);

        JPasswordField inputPassword = FrameHandler.createPasswordField(260, 130, 200, 30);
        frame.add(inputPassword);

        JLabel passwordBaru = FrameHandler.createLabel("Password Baru", new Font("Courier", Font.BOLD, 16), 30, 170, 200, 30);
        frame.add(passwordBaru);

        JPasswordField inputPasswordBaru = FrameHandler.createPasswordField(260, 170, 200, 30);
        frame.add(inputPasswordBaru);

        JButton buttonSimpan = FrameHandler.createButton("Ubah Password", new Font("Courier", Font.BOLD, 13), 40, 515, 400, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(inputPassword.getPassword());
                String passwordBaru = String.valueOf(inputPasswordBaru.getPassword());
                if (passwordCheck.equals(password)) {
                    boolean succeed = Controller.getInstance().updatePasswordDataPassangerToDB(id, passwordBaru);
                    if (succeed) {
                        FrameHandler.showMessageDialog("Password Berhasil diubah", "");
                        frame.dispose();
                        String find = Controller.getInstance().getRolesUser(id);
                        if (find.equalsIgnoreCase("Passanger")) {
                            new MainMenuPassanger(id);
                        } else if (find.equalsIgnoreCase("Driver")) {
                            new MainMenuDriver(id);
                        }
                    }
                } else {
                    FrameHandler.showWarningMessage("Password Lama Tidak Sama!", "");
                }
            }
        });
        frame.add(buttonSimpan);

        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 13), 10, 10, 85, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String role = Controller.getInstance().getRolesUser(id);
                if (role.equalsIgnoreCase("Passanger")) {
                    new MainMenuPassanger(id);
                } else if (role.equalsIgnoreCase("Driver")) {
                    new MainMenuDriver(id);
                }
            }
        });
        frame.add(backButton);

        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(buttonSimpan);
        frame.add((intro));
        frame.add((password));
        frame.add(inputPassword);
        frame.add(passwordBaru);
        frame.add(inputPasswordBaru);
        frame.add(backButton);
        frame.add(lineDiv);

        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
