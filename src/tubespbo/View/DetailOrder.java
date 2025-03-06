package tubespbo.View;

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

    protected JFrame f = new JFrame();
    protected Font font = new Font("Courier", Font.BOLD, 20);
    protected Font font2 = new Font("Courier", Font.PLAIN, 14);
    protected Font font3 = new Font("Courier", Font.PLAIN, 16);
    protected Font font4 = new Font("Courier", Font.BOLD, 16);
    protected Font fontButton = new Font("Courier", Font.BOLD, 13);
    protected ArrayList<Order> listOrder;

    public DetailOrder(int id, int idOrder, int menu) {
        showDataScreen(id, idOrder, menu);
    }

    private void showDataScreen(int id, int idOrder, int menu) {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listOrder = Controller.getInstance().getDetailOrder(idOrder);

        JLabel intro = FrameHandler.createLabel("Detail Pesanan.", font, 30, 70, 400, 30);

        String message = "";
        if (listOrder.get(listOrder.size() - 1).getOrder_status() == OrderStatusEnum.FINISHED) {
            message = "Sudah sampai tujuan.";
        } else if (listOrder.get(0).getOrder_status() == OrderStatusEnum.CANCEL) {
            message = "Sudah di cancel.";
        } else if (listOrder.get(0).getOrder_status() == OrderStatusEnum.NOW) {
            message = "Sedang dalam perjalanan";
        }

        JLabel intro2 = FrameHandler.createLabel(message, font3, 30, 95, 400, 30);

        JLabel lineDiv = FrameHandler.createLabel(
                "__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 120, 465, 20);

        JLabel time = FrameHandler.createLabel("" + Controller.getInstance().getTimeOrder(idOrder), font2, 30, 150, 300, 30);

        JLabel idForShow = FrameHandler.createLabel("#" + idOrder, font4, 420, 150, 300, 30);

        JLabel asal = FrameHandler.createLabel(
                listOrder.get(listOrder.size() - 1).getOrder_pickup() + " menuju", font4, 340, 95, 100, 30);

        JLabel destination = FrameHandler.createLabel(
                listOrder.get(listOrder.size() - 1).getOrder_destination(), font4, 420, 95, 40, 30);
        JLabel payDetail = FrameHandler.createLabel("Detail Pembayaran:", font4, 30, 385, 300, 30);
        JLabel payRaw = FrameHandler.createLabel("Biaya Perjalanan:", font2, 30, 410, 300, 30);
        JLabel payTax = FrameHandler.createLabel("Biaya  jasa aplikasi:", font2, 30, 435, 300, 30);
        JLabel payVoucher = FrameHandler.createLabel("Diskon Voucher:", font2, 30, 460, 300, 30);
        JLabel total = FrameHandler.createLabel("Total:", font4, 30, 490, 300, 30);
        JLabel payRawVal = FrameHandler.createLabel("Rp. " + listOrder.get(listOrder.size() - 1).getOrder_price(), font2, 370, 410, 300, 30);
        JLabel payTaxVal = FrameHandler.createLabel("Rp. 2000", font2, 370, 435, 300, 30);

        float disc = Controller.getInstance().getPromoValByID(listOrder.get(listOrder.size() - 1).getPromo_id());
        float result = (listOrder.get(listOrder.size() - 1).getOrder_price() + 2000) * disc;

        JLabel payVoucherVal = FrameHandler.createLabel("Rp. " + result, font2, 370, 460, 300, 30);
        JLabel totalVal = FrameHandler.createLabel("Rp. " + listOrder.get(listOrder.size() - 1).getOrder_final_price(), font4, 360, 490, 300, 30);
        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 90, 30, e -> onBack(id, menu));
        f.add((intro));
        f.add(intro2);

        f.add(time);
        f.add(idForShow);

        f.add(asal);
        f.add(destination);

        f.add(payDetail);
        f.add(payRaw);
        f.add(payRawVal);
        f.add(payTax);
        f.add(payVoucher);
        f.add(total);
        f.add(payTaxVal);
        f.add(payVoucherVal);
        f.add(totalVal);

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void onBack(int id, int menu) {
        f.dispose();
        switch (menu) {
            case 1:
                new OrderBerjalan(id);
                break;
            case 2:
                new OrderRiwayat(id);
                break;
            default:
                new MainMenuAdmin();
                break;
        }
    }
}
