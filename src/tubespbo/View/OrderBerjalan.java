package tubespbo.View;

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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import tubespbo.Contoller.Controller;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;
import tubespbo.Model.Passanger;

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

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
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
        labelNama.setBounds(30, 500, 100, 30);
        textNama.setBounds(260, 600, 200, 30);

        ArrayList<Order> listOrder = con.getOrder();

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Coba1");
        tableModel.addColumn("Coba2");
        tableModel.addColumn("Coba3");
        tableModel.addColumn("Coba4");

        for (int i = 0; i < listOrder.size(); i++) {
            int idOrder = listOrder.get(i).getOrder_id();

            String tujuan = listOrder.get(i).getOrder_destination();
            double harga = listOrder.get(i).getOrder_final_price();
            OrderStatusEnum enumOrder = listOrder.get(i).getOrder_status();
            String hasilEnum = enumOrder.toString();

            Object[] dataRow = {tujuan, harga, hasilEnum, "Button"};
            tableModel.insertRow(i, dataRow);
        }

        JTable table = new JTable(tableModel);
        table.getColumn("Coba4").setCellRenderer(new ButtonRenderer());
        table.getColumn("Coba4").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        table.setBounds(10, 140, 400, 200);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);


        f.setSize(800, 500);
        f.add(new JScrollPane(table));
        f.add(table);

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
    
    public static void main(String[] args) {
        new OrderBerjalan(5);
    }

}
