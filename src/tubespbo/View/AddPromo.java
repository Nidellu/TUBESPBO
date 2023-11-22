package tubespbo.View;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import tubespbo.Controller.Controller;
import tubespbo.Model.Promo;

public class AddPromo {
    public AddPromo() {

    } 

    private void addHere() {
        Controller cntrl = new Controller();
        ArrayList<Promo> promos = new ArrayList<>();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // buat label utama
        JLabel intro = new JLabel("Tambah Promo Baru");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Isi dulu datanya");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(30, 70, 400, 30);
        intro2.setBounds(30, 90, 300, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);  // buat atur font pada button

        JLabel lineDiv = new JLabel("_______________________________" // buat separator
                + "__________________________________________");
        lineDiv.setBounds(10, 120, 500, 20);

        Font fontLabel = new Font("Courier", Font.BOLD, 16);   // buat atur font pada label


    }
}
