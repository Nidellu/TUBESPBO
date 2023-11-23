package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tubespbo.Controller.Controller;
import tubespbo.Model.Order;

public class OrderRiwayat {

    public OrderRiwayat(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);
        // JLabel intro = new JLabel("Orderan Selesai.");
        JLabel intro = new JLabel("Riwayat Orderan.");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");

        lineDiv.setBounds(10, 100, 968, 20);

        ArrayList<Order> listOrder = con.getOrderCancelFinish(id);
        lineDiv.setBounds(10, 100, 450, 20);

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

        System.out.println(listOrder.size());

        // if (height > 370) {
        // height = 370;
        // }

        JPanel containerOrders = new JPanel();
        containerOrders.setLayout(new BoxLayout(containerOrders, BoxLayout.Y_AXIS));
        containerOrders.setBounds(5, 120, 425, 370);

        int orderHeight = 10;

        for (Order order : listOrder) {
            // JPanel indivOrder = new JPanel(null);
            // indivOrder.setSize(300, 60);
            // indivOrder.setBounds(5, orderHeight, 400, 60);
            // indivOrder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            int idOrder = order.getOrder_id();

            JTextField nameField = new JTextField("Tujuan: " + order.getOrder_destination());
            nameField.setBounds(10, 5, 100, 25);
            nameField.setBackground(null);
            nameField.setBorder(null);
            nameField.setEditable(false);
            containerOrders.add(nameField);

            JTextField dateField = new JTextField(order.getOrder_date() + "");
            dateField.setLayout(null);
            dateField.setBounds(150, 5, 150, 25);
            dateField.setBorder(null);
            dateField.setEditable(false);
            containerOrders.add(dateField);

            JTextField priceField = new JTextField("\t\t\t\tRp. " + order.getOrder_final_price() + "");
            priceField.setBorder(null);
            priceField.setBounds(300, 5, 70, 25);
            priceField.setEditable(false);
            containerOrders.add(priceField);

            JTextField status = new JTextField(order.getOrder_status().toString() + "\t\t            ");
            status.setBorder(null);
            status.setBounds(10, 30, 80, 25);
            status.setEditable(false);
            containerOrders.add(status);

            JButton buyButton = new JButton("Details");
            buyButton.setBounds(300, 30, 90, 25);
            buyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new DetailOrder(id, idOrder);
                }
            });
            containerOrders.add(buyButton);

            containerOrders.setOpaque(true);

            containerOrders.setVisible(true);
            // containerOrders.add(buyButton);
            // containerOrders.add(indivOrder);
            containerOrders.setVisible(true);
            // orderHeight += 0;
            System.out.println("Hello");
        }

        JLabel lineDiv2 = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv2.setBounds(10, 500, 968, 20);

        lineDiv2.setBounds(10, 510, 450, 20);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new CekOrder(id);
            }
        });

        // f.add(containerOrders);
        f.add((intro));

        JScrollPane scrollPaneOrder = new JScrollPane(containerOrders, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollPaneOrder.setVisible(false);
        scrollPaneOrder.setBorder(null);
        // scrollPaneOrder.setBackground(Color.CYAN);
        scrollPaneOrder.setBounds(30, 120, 415, 400);
        // scrollPaneOrder.setPreferredSize(new Dimension(415, height));

        // scrollPaneOrder.add(containerOrders);

        f.getContentPane().add(scrollPaneOrder);

        // f.add(containerOrders);
        f.add((intro));
        // f.add(scrollPaneOrder);

        f.add(backButton);
        f.add(lineDiv);
        f.add(lineDiv2);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new OrderRiwayat(5);
    }

}
