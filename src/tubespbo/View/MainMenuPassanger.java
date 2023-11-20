/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tubespbo.Contoller.Controller;
import tubespbo.Model.User;

public class MainMenuPassanger {

    public MainMenuPassanger(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        ArrayList<User> listUser;
        JFrame f = new JFrame();
        
        String nameDisplay = con.getUsername(id);

        JLabel intro = new JLabel("Selamat Datang Kembali, " + nameDisplay + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Kita jalan kemana yuk!");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 300, 30);
        intro2.setBounds(10, 30, 300, 30);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(170, 350, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        
        f.add((intro));
        f.add((intro2));
        f.add((backButton));

        f.setSize(800, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainMenuPassanger(5);
    }
}
