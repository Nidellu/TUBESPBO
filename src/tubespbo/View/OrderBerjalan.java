package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tubespbo.Contoller.Controller;
import tubespbo.Model.Order;
import tubespbo.Model.Passanger;

public class OrderBerjalan {

    public OrderBerjalan(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();

        ArrayList<Passanger> pass = con.getUserByID(id);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 16);

        JLabel intro = new JLabel("Orderan saat Ini.");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 120, 500, 20);

        Font fontLabel = new Font("Courier", Font.BOLD, 16);

        JLabel labelNama = new JLabel("Username ");
        JTextField textNama = new JTextField(pass.get(pass.size() - 1).getUser_name());
        labelNama.setFont(fontLabel);
        labelNama.setBounds(30, 160, 100, 30);
        textNama.setBounds(260, 160, 200, 30);

        ArrayList<Order> listOrder = new ArrayList<>();
        
        DefaultTableModel tableModel = new DefaultTableModel();

        JTable table = new JTable(tableModel);

        for (int i = 0; i < listOrder.size(); i++) {
            int idOrder = listOrder.get(i).getOrder_id();
            
            String destanition = listOrder.get(i).getOrder_destination();
            int userID = listOrder.get(i).getUser_id();
            
            
            Object[] dataRow1 = {idTr, userID, name, gameID, gameName, price};
            tableModel.insertRow(i, dataRow1);
            
            String name = listOrder.get(i).getUsername();
            int gameID = listOrder.get(i).getGame_id();
            String gameName = listOrder.get(i).getGameName();
            String price = listOrder.get(i).getPrice();
            JButton backButton = new JButton("Back to Game List");
            backButton.setBounds(170, 350, 150, 30);
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new MenuGameList(id);
                }
            });


        }

        table.setBounds(50, 100, 700, 200);
        table.setRowHeight(100);

        f.setSize(800, 500);
        f.add(backButton);
        f.add(new JScrollPane(table));

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
        f.add(labelNama);
        f.add(textNama);
        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

}
