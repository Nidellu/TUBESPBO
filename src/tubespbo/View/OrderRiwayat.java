<<<<<<< HEAD
package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.Order;

import java.util.ArrayList;

public class OrderRiwayat {

    JFrame frame;

    public OrderRiwayat(int id) {
        createForm(id);
    }

    private void createForm(int id) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Order> orderHistory = Controller.getInstance().getOrderHistory(id);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);

        JLabel intro = FrameHandler.createLabel("Riwayat Orderan.", font, 30, 70, 400, 30);

        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", font2, 10, 100, 460, 20);

        if (orderHistory.isEmpty()) {
            JLabel messageLabel = FrameHandler.createLabel("Yah... Order masih kosong nih :'(", font3, 110, 300, 400, 30);
            frame.add(messageLabel);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 445, 420);
        frame.getContentPane().add(scrollPane);

        JPanel containerOrder = new JPanel();
        scrollPane.setViewportView(containerOrder);
        containerOrder.setLayout(new BorderLayout(0, 0));


        JPanel orderListCont = new JPanel();
        containerOrder.add(orderListCont, BorderLayout.NORTH);
        orderListCont.setLayout(new GridLayout(0, 1, 0, 1));
        orderListCont.setBackground(Color.gray);

        for (Order order : orderHistory) {
            JPanel indivOrder = createIndivOrderPanel(id, order);
            orderListCont.add(indivOrder);
        }

        JButton backButton = FrameHandler.createButton("Kembali", new Font("Courier", Font.BOLD, 12), 10, 10, 85, 30, e -> {
            frame.dispose();
            new CekOrder(id);
        });

        frame.add(intro);
        frame.add(backButton);
        frame.add(lineDiv);

        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createIndivOrderPanel(int id, Order order) {
        Font font4 = new Font("Courier", Font.BOLD, 14);
        Font font5 = new Font("Courier", Font.BOLD, 12);
        
        JPanel indivOrder = new JPanel(null);
        indivOrder.setPreferredSize(new Dimension(300, 60));
        indivOrder.setLayout(null);
        indivOrder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        int idOrder = order.getOrder_id();

        JLabel nameField = FrameHandler.createLabel("Tujuan: " + order.getOrder_destination(), font4, 10, 5, 100, 25);
        indivOrder.add(nameField);

        JLabel dateField = FrameHandler.createLabel(order.getOrder_date() + "", font5, 353, 5, 70, 20);
        indivOrder.add(dateField);

        JLabel priceField = FrameHandler.createLabel("Rp. " + order.getOrder_final_price() + "", font5, 10, 30, 70, 25);
        indivOrder.add(priceField);

        JLabel status = FrameHandler.createLabel(order.getOrder_status().toString(), font5, 150, 3, 150, 25);
        indivOrder.add(status);

        JButton detailsButton = FrameHandler.createButton("Details", font5, 340, 30, 90, 25, e -> {
            frame.dispose();
            String find = Controller.getInstance().getRolesUser(id);
            if (find.equalsIgnoreCase("Passanger")) {
                new DetailOrderPassanger(id, idOrder, 2);
            } else if (find.equalsIgnoreCase("Driver")) {
                new DetailOrderDriver(id, idOrder, 2);
            } else if (find.equalsIgnoreCase("Admin")) {
                new DetailOrderAdmin(id, idOrder, 2);
            }
        });
        indivOrder.add(detailsButton);

        return indivOrder;
    }
}

=======
package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.Order;

public class OrderRiwayat{

    public OrderRiwayat(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        JFrame f = new JFrame();
        
        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);
        Font font4 = new Font("Courier", Font.BOLD, 14);

        JLabel intro = new JLabel("Riwayat Orderan.");

        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");

        lineDiv.setBounds(10, 100, 460, 20);

        ArrayList<Order> listOrder = Controller.getInstance().getOrderHistory(id);
        
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
            JPanel indivOrder = new JPanel(null);
            indivOrder.setFont(font2);
            indivOrder.setPreferredSize(new Dimension(300,60));
            orderListCont.add(indivOrder);
            indivOrder.setLayout(null);
            
            indivOrder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            int idOrder = order.getOrder_id();

            JLabel nameField = new JLabel("Tujuan: " + order.getOrder_destination());
            nameField.setBounds(10, 5, 100, 25);
            nameField.setBorder(null);
            indivOrder.add(nameField);
            
            JLabel dateField = new JLabel(order.getOrder_date() + "");
            dateField.setBounds(353, 5, 70, 20);
            dateField.setBorder(null);
            indivOrder.add(dateField);
            
            JLabel priceField = new JLabel("Rp. " + order.getOrder_final_price() + "");
            priceField.setBorder(null);
            priceField.setBounds(10, 30, 70, 25);
            indivOrder.add(priceField);
            
            JLabel status = new JLabel( order.getOrder_status().toString() + "");
            status.setFont(font4);
            status.setBorder(null);
            status.setBounds(150, 3, 150, 25);
            indivOrder.add(status);

            JButton buyButton = new JButton("Details");
            buyButton.setBounds(300, 30, 90, 25);
                        JButton detailsButton = new JButton("Details");
            detailsButton.setBounds(340, 28, 90, 25);
            detailsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    String find = Controller.getInstance().getRolesUser(id);
                    if(find.equalsIgnoreCase("Passanger")){
                        new DetailOrderPassanger(id, idOrder, 2);
                    } else if(find.equalsIgnoreCase("Driver")) {
                        new DetailOrderDriver(id, idOrder, 2);
                    } else if(find.equalsIgnoreCase("Admin")){
                        new DetailOrderAdmin(id, idOrder, 2);
                    }
                    
                }
            });
            indivOrder.add(detailsButton);
        }
        
        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new CekOrder(id);
            }
        });
        
        f.add((intro));


        f.add(backButton);
        f.add(lineDiv);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 600);
        f.getContentPane().setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

>>>>>>> master
