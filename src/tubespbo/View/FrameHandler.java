package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrameHandler {
    
    static JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    static JLabel createImageLabel(String imagePath, int x, int y, int width, int height) {
        ImageIcon originalFotoIcon = new ImageIcon(imagePath);
        Image originalFoto = originalFotoIcon.getImage();
        Image resizedFoto = originalFoto.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedFotoIcon = new ImageIcon(resizedFoto);
        JLabel label = new JLabel(resizedFotoIcon);
        label.setBounds(x, y, width, height);
        return label;
    }

    static JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }

    static JButton createButton(String text, Font font, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        return button;
    }
    
    static JTextField createTextField(String value, Color color, Font font, int x, int y, int width, int height) {
        JTextField textField = new JTextField(value);
        textField.setBackground(color);
        textField.setBounds(x, y, width, height);
        textField.setFont(font);
        return textField;
    }
    
    static JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, height);
        return passwordField;
    }
    
    static void showMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
    }
    
    static void showErrorDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    static void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    static void showInformationMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    static int showConfirmationDialog(String message, String title) {
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Ya", "Tidak"}, null);
    }
}
