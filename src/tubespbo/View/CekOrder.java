package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tubespbo.Contoller.Controller;

public class CekOrder {

    public CekOrder(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();

        String nameDisplay = con.getUsername(id);

        JLabel intro = new JLabel("Pesanan.");
        Font font = new Font("Courier", Font.BOLD, 20);

        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 90, 500, 20);

        JButton berjalan = new JButton("Dalam Proses");
        berjalan.setFont(fontButton);
        berjalan.setBounds(70, 260, 350, 30);
        berjalan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                
            }
        });

        JButton riwayat = new JButton("Riwayat");
        riwayat.setFont(fontButton);
        riwayat.setBounds(70, 310, 350, 30);
        riwayat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                
            }
        });

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuPassanger(id);
            }
        });

        f.add((backButton));

        f.add((intro));
        f.add(lineDiv);

        f.add(berjalan);
        f.add(riwayat);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new CekOrder(5);
    }
}
