package tubespbo.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import tubespbo.Controller.Controller;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;



public class DetailOrder {

    public DetailOrder(int id) {
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

        ArrayList<Order> listOrder = con.getDetailOrder(id);

        if (listOrder.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... Order masih kosong nih :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(360, 280, 400, 30);
            f.add(ingpo);
        }

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Coba1");
        tableModel.addColumn("Coba2");
        tableModel.addColumn("Coba3");
        tableModel.addColumn("Coba4");
        tableModel.addColumn("Coba5");

        String tujuan = "Tujuan Kota:" + listOrder.get(0).getOrder_destination();
        double harga = listOrder.get(0).getOrder_final_price();
        OrderStatusEnum enumOrder = listOrder.get(0).getOrder_status();
        String hasilEnum = enumOrder.toString();
        String kendaraan = listOrder.get(0).getOrder_vehicle_name();

        Object[] dataRow = {tujuan, "Kendaraan: " + kendaraan, "Rp. " + harga, hasilEnum, "Details"};
        tableModel.insertRow(0, dataRow);

        JTable table = new JTable(tableModel);
        table.setFont(font2);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        table.setBackground(new Color(238, 238, 238));

        table.setBounds(15, 140, 910, 350);
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        f.setSize(800, 500);
        f.add(new JScrollPane(table));
        f.add(table);

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

        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);
        f.add(lineDiv2);

        f.setSize(1000, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

}
