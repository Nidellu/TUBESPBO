
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tubespbo.Contoller.Controller;

public class MainMenuPassanger {

    public MainMenuPassanger(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        
        String nameDisplay = con.getUsername(id);

        JLabel intro = new JLabel("Selamat Datang Kembali, " + nameDisplay + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Kita jalan kemana yuk!");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 400, 30);
        intro2.setBounds(10, 30, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD,12);
        
        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 50, 500, 20);
        
        JButton profileButton = new JButton("Check Profil");
        profileButton.setFont(fontButton);
        profileButton.setBounds(10, 80, 470, 30);
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new PassangerProfile(id);
            }
        });
        
        JButton buttonCari = new JButton("Pesan JoRide");
        buttonCari.setFont(fontButton);
        buttonCari.setBounds(215, 300, 200, 30);
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
        new MainMenuPassanger(5);
    }
}
