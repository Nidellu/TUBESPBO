package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tubespbo.Controller.Controller;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;



public class DetailOrder {

    public DetailOrder(int id, int idOrder) {
        showDataScreen(id, idOrder);
    }

    private void showDataScreen(int id, int idOrder) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 16);
        Font font4 = new Font("Courier", Font.BOLD, 16);
        Font fontButton = new Font("Courier", Font.BOLD, 13);

         ArrayList<Order> listOrder = con.getDetailOrder(idOrder);
        
        JLabel intro = new JLabel("Detail Pesanan #" + idOrder +".");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);
        
        String message = "";
        if(listOrder.get(listOrder.size()-1).getOrder_status() == OrderStatusEnum.FINISHED){
            message = "Sudah sampai tujuan.";
        } else if(listOrder.get(0).getOrder_status() == OrderStatusEnum.CANCEL){
            message = "Sudah di cancel.";
        } else if(listOrder.get(0).getOrder_status() == OrderStatusEnum.NOW){
            message = "Sedang dalam perjalanan";
        }
        
        JLabel intro2 = new JLabel(message);
        intro2.setFont(font3);
        intro2.setBounds(30, 95, 400, 30);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv.setBounds(10, 120, 968, 20);

        JLabel email = new JLabel("" + con.getTimeOrder(idOrder));
        email.setFont(font2);
        email.setBounds(30, 150, 300, 30);

        String driver = con.getUsername(listOrder.get(listOrder.size()-1).getDriver_id());
        
        JLabel driverName = new JLabel(driver);
        driverName.setFont(font4);
        driverName.setBounds(30, 180, 300, 30);

        f.setSize(800, 500);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new CekOrder(id);
            }
        });

        f.add((intro));
        f.add(intro2);

        f.add(email);
        f.add(driverName);
        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new DetailOrder(5,1);
    }
}
