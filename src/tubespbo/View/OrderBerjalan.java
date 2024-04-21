package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.Order;

public class OrderBerjalan {

    JFrame f;

    public OrderBerjalan(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);

        JLabel intro = FrameHandler.createLabel("Orderan saat Ini.", font, 30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 100, 460, 20);

        ArrayList<Order> listOrder = Controller.getInstance().getOrderNow(id);

        if (listOrder.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... Order masih kosong nih :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(110, 300, 400, 30);
            f.add(ingpo);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 445, 420);
        f.getContentPane().add(scrollPane);

        JPanel containerOrder = new JPanel();
        scrollPane.setViewportView(containerOrder);
        containerOrder.setLayout(new BorderLayout(0, 0));

        JPanel orderListCont = new JPanel();
        containerOrder.add(orderListCont, BorderLayout.NORTH);
        orderListCont.setLayout(new GridLayout(0, 1, 0, 1));
        orderListCont.setBackground(Color.gray);

        for (Order order : listOrder) {
            JPanel indivOrder = createOrderBerjalanPanel(id, order);
            orderListCont.add(indivOrder);
        }
        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 12), 10, 10, 85, 30, e -> onBack(id));

        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 600);
        f.getContentPane().setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private JPanel createOrderBerjalanPanel(int id, Order order) {
        Font font4 = new Font("Courier", Font.BOLD, 14);

        JPanel indivOrder = new JPanel(null);
        indivOrder.setPreferredSize(new Dimension(300, 60));
        indivOrder.setLayout(null);

        indivOrder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        int idOrder = order.getOrder_id();

        JLabel nameField = FrameHandler.createLabel("Tujuan: " + order.getOrder_destination(), new Font("Courier", Font.BOLD, 12), 10, 5, 100, 25);
        nameField.setBorder(null);
        indivOrder.add(nameField);

        JLabel dateField = FrameHandler.createLabel(order.getOrder_date() + "", new Font("Courier", Font.BOLD, 12), 353, 5, 70, 20);
        dateField.setBorder(null);
        indivOrder.add(dateField);

        JLabel priceField = FrameHandler.createLabel("Rp. " + order.getOrder_final_price() + "", new Font("Courier", Font.BOLD, 12), 10, 30, 70, 25);
        priceField.setBorder(null);
        indivOrder.add(priceField);

        JLabel status = FrameHandler.createLabel(order.getOrder_status().toString() + "", font4, 150, 3, 150, 25);
        status.setBorder(null);
        indivOrder.add(status);

        JButton detailsButton = FrameHandler.createButton("Details", new Font("Courier", Font.BOLD, 12), 340, 28, 90, 25, e -> {
            f.dispose();
            String find = Controller.getInstance().getRolesUser(id);
            if (find.equalsIgnoreCase("Passanger")) {
                new DetailOrderPassanger(id, idOrder, 1);
            } else if (find.equalsIgnoreCase("Driver")) {
                new DetailOrderDriver(id, idOrder, 1);
            } else if (find.equalsIgnoreCase("Admin")) {
                new DetailOrderAdmin(id, idOrder, 1);
            }
        });

        indivOrder.add(detailsButton);
        return indivOrder;
    }

    private void onBack(int id) {
        f.dispose();
        new CekOrder(id);
    }

}
