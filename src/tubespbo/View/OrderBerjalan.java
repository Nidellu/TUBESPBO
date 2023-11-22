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

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox, int id) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DetailOrder(id);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

public class OrderBerjalan {

    public OrderBerjalan(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Coba1");
        tableModel.addColumn("Coba2");
        tableModel.addColumn("Coba3");
        tableModel.addColumn("Coba4");
        tableModel.addColumn("Coba5");

        JTable table = new JTable(tableModel);

        for (int i = 0; i < listOrder.size(); i++) {
            int idOrder = listOrder.get(i).getOrder_id();
            
            JOptionPane.showMessageDialog(f, idOrder, "", JOptionPane.WARNING_MESSAGE);


            String tujuan = "Tujuan Kota:" + listOrder.get(i).getOrder_destination();
            double harga = listOrder.get(i).getOrder_final_price();
            OrderStatusEnum enumOrder = listOrder.get(i).getOrder_status();
            String hasilEnum = enumOrder.toString();
            String kendaraan = listOrder.get(i).getOrder_vehicle_name();

            Object[] dataRow = {tujuan, "Kendaraan: " + kendaraan, "Rp. " + harga, hasilEnum, "Details"};
            tableModel.insertRow(i, dataRow);
            table.getColumn("Coba5").setCellRenderer(new ButtonRenderer());
            table.getColumn("Coba5").setCellEditor(new ButtonEditor(new JCheckBox(),idOrder));
        }

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

    public static void main(String[] args) {
        new OrderBerjalan(5);
    }

}
