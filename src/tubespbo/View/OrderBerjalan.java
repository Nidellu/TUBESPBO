package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import tubespbo.Controller.Controller;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;

public class OrderBerjalan {

    public OrderBerjalan(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);

        JLabel intro = new JLabel("Orderan saat Ini.");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv.setBounds(10, 100, 968, 20);

        ArrayList<Order> listOrder = con.getOrderNow(id);

        if (listOrder.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... Order masih kosong nih :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(360, 280, 400, 30);
            f.add(ingpo);
        }

        int height = (listOrder.size()) * 65;

        if (height > 370) {
            height = 370;
        }

        JLayeredPane gamePanelContainer = new JLayeredPane();
        gamePanelContainer.setLayout(new BoxLayout(gamePanelContainer, BoxLayout.Y_AXIS));
        gamePanelContainer.setBounds(30, 130, 415, height);

        for (Order order : listOrder) {
            JPanel gamePanel = new JPanel();

            gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            int idOrder = order.getOrder_id();

            JTextField nameField = new JTextField("    Tujuan: " + order.getOrder_destination() + "\t\t");
            nameField.setBorder(null);
            nameField.setEditable(false);
            gamePanel.add(nameField);

            JTextField priceField = new JTextField(order.getOrder_date() + "\t               ");
            priceField.setBorder(null);
            priceField.setEditable(false);
            gamePanel.add(priceField);

            JTextField genreField = new JTextField("Rp. " + order.getOrder_final_price() + "\t");
            genreField.setBorder(null);
            genreField.setEditable(false);
            gamePanel.add(genreField);

            JTextField status = new JTextField(order.getOrder_status().toString() + "\t\t            ");
            status.setBorder(null);
            status.setEditable(false);
            gamePanel.add(status);

            JButton buyButton = new JButton("Details");
            buyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new DetailOrder(id, idOrder);
                }
            });
            gamePanel.add(buyButton);

            gamePanel.setOpaque(true);

            gamePanelContainer.add(gamePanel);
        }

        JLabel lineDiv2 = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv2.setBounds(10, 500, 968, 20);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new CekOrder(id);
            }
        });

        f.add(gamePanelContainer);
        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);
        f.add(lineDiv2);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new OrderBerjalan(5);
    }

}
