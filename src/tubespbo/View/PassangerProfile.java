
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tubespbo.Contoller.Controller;
import tubespbo.Model.Passanger;

public class PassangerProfile {

    public PassangerProfile(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        
        ArrayList<Passanger> pass = con.getUserByID(id);

        JLabel intro = new JLabel("Hai Kamu, " + pass.get(0).getUser_name() + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Mau update apa nih?");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 400, 30);
        intro2.setBounds(10, 30, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD,12);
        
        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 50, 500, 20);
        
        Font fontLabel = new Font("Courier", Font.BOLD, 16);
        
        JLabel labelNama = new JLabel("Username ");
        JTextField textNama = new JTextField(pass.get(0).getUser_name());
        labelNama.setFont(fontLabel);
        labelNama.setBounds(10, 80, 200, 30);
        textNama.setBounds(200, 80, 250, 30);

        JLabel labelTelepon = new JLabel("Nomor  Telepon ");
        JTextField textTelepon = new JTextField(pass.get(0).getPhone_number());
        labelTelepon.setFont(fontLabel);
        labelTelepon.setBounds(10, 80, 200, 30);
        textTelepon.setBounds(200, 80, 250, 30);
        
        JButton buttonCari = new JButton("Pesan Order");
        buttonCari.setFont(fontButton);
        buttonCari.setBounds(215, 300, 100, 30);
        buttonCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                
            }
        });

        
        f.add(buttonCari);
        f.add((intro));
        f.add((intro2));
        f.add(profileButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        new PassangerProfile(5);
    }
}
