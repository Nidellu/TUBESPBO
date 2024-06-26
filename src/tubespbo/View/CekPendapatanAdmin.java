<<<<<<< HEAD
package tubespbo.View;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tubespbo.Controller.Controller;

public class CekPendapatanAdmin {

    JFrame f;

    public CekPendapatanAdmin() {
        showDataScreen();
    }

    private void showDataScreen() {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        Font font3 = new Font("Courier", Font.BOLD, 72);
        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel intro = FrameHandler.createLabel("Total Pendapatan.", font, 30, 70, 400, 30);
        JLabel intro2 = FrameHandler.createLabel("Selalu bersyukur ya!", font2, 30, 95, 400, 30);
        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 120, 465, 20);
        JLabel time = FrameHandler.createLabel("Total Order Josen: ", font2, 30, 150, 300, 30);
        JLabel idForShow = FrameHandler.createLabel(Controller.getInstance().getOrderCount() + "", font3, 30, 180, 300, 90);
        JLabel payDetail = FrameHandler.createLabel("Dengan total pendapatan sebanyak:", font2, 30, 270, 300, 30);
        JLabel payRaw = FrameHandler.createLabel("Rp. " + Controller.getInstance().getOrderCount() * 2000, font, 30, 300, 300, 30);

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 85, 30, e -> onBack());

        f.add((intro));
        f.add(intro2);

        f.add(time);
        f.add(idForShow);

        f.add(payDetail);
        f.add(payRaw);

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void onBack() {
        f.dispose();
        new MainMenuAdmin();
    }
}
=======
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tubespbo.Controller.Controller;


public class CekPendapatanAdmin {

    public CekPendapatanAdmin() {
        showDataScreen();
    }

    private void showDataScreen() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        Font font3 = new Font("Courier", Font.BOLD, 72);
        Font fontButton = new Font("Courier", Font.BOLD, 13);


        JLabel intro = new JLabel("Total Pendapatan.");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        JLabel intro2 = new JLabel("Selalu bersyukur ya!");
        intro2.setFont(font2);
        intro2.setBounds(30, 95, 400, 30);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv.setBounds(10, 120, 465, 20);

        JLabel time = new JLabel("Total Order Josen: ");
        time.setFont(font2);
        time.setBounds(30, 150, 300, 30);

        JLabel idForShow = new JLabel(Controller.getInstance().getOrderCount() + "");
        idForShow.setFont(font3);
        idForShow.setBounds(30, 180, 300, 90);

        JLabel payDetail = new JLabel("Dengan total pendapatan sebanyak:");
        payDetail.setFont(font2);
        payDetail.setBounds(30, 270, 300, 30);

        JLabel payRaw = new JLabel("Rp. " + Controller.getInstance().getOrderCount()*2000);
        payRaw.setFont(font);
        payRaw.setBounds(30, 300, 300, 30);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });

        f.add((intro));
        f.add(intro2);

        f.add(time);
        f.add(idForShow);

        f.add(payDetail);
        f.add(payRaw);

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }


}
>>>>>>> master
