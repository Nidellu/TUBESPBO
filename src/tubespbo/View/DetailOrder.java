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
import tubespbo.Model.Driver;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;
import tubespbo.Model.Passanger;

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

        JLabel intro = new JLabel("Detail Pesanan.");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        String message = "";
        if (listOrder.get(listOrder.size() - 1).getOrder_status() == OrderStatusEnum.FINISHED) {
            message = "Sudah sampai tujuan.";
        } else if (listOrder.get(0).getOrder_status() == OrderStatusEnum.CANCEL) {
            message = "Sudah di cancel.";
        } else if (listOrder.get(0).getOrder_status() == OrderStatusEnum.NOW) {
            message = "Sedang dalam perjalanan";
        }

        JLabel intro2 = new JLabel(message);
        intro2.setFont(font3);
        intro2.setBounds(30, 95, 400, 30);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");
        lineDiv.setBounds(10, 120, 465, 20);

        JLabel time = new JLabel("" + con.getTimeOrder(idOrder));
        time.setFont(font2);
        time.setBounds(30, 150, 300, 30);

        JLabel idForShow = new JLabel("#" + idOrder);
        idForShow.setFont(font4);
        idForShow.setBounds(420, 150, 300, 30);

        String find = con.getRolesUser(id);

        if (find.equalsIgnoreCase("Passanger")) {
            String driver = con.getUsername(listOrder.get(listOrder.size() - 1).getDriver_id());

            JLabel driverInfo = new JLabel("Info Driver:");
            driverInfo.setFont(font4);
            driverInfo.setBounds(30, 190, 300, 30);

            JLabel driverName = new JLabel(driver);
            driverName.setFont(font4);
            driverName.setBounds(30, 220, 300, 30);

            ArrayList<Driver> driverCont = con.getDriverByID(listOrder.get(listOrder.size() - 1).getDriver_id());

            JLabel driverPhon = new JLabel(driverCont.get(driverCont.size() - 1).getDriver_phonNum());
            driverPhon.setFont(font2);
            driverPhon.setBounds(370, 220, 300, 30);

            JLabel driverVType = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_type());
            driverVType.setFont(font2);
            driverVType.setBounds(30, 245, 300, 30);

            JLabel driverVPlate = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_plate());
            driverVPlate.setFont(font2);
            driverVPlate.setBounds(390, 255, 300, 30);

            JLabel driverVName = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_name());
            driverVName.setFont(font2);
            driverVName.setBounds(30, 265, 300, 30);

            f.add(driverInfo);
            f.add(driverName);
            f.add(driverPhon);
            f.add(driverVType);
            f.add(driverVName);
            f.add(driverVPlate);
        } else if (find.equalsIgnoreCase("Driver")) {
            String passName = con.getUsername(listOrder.get(listOrder.size() - 1).getCust_id());

            JLabel passInfo = new JLabel("Info Passanger:");
            passInfo.setFont(font4);
            passInfo.setBounds(30, 190, 300, 30);

            JLabel passNameCont = new JLabel(passName);
            passNameCont.setFont(font4);
            passNameCont.setBounds(30, 220, 300, 30);

            ArrayList<Passanger> passCont = con.getPassangerByID(id);

            JLabel passPhon = new JLabel(passCont.get(passCont.size() - 1).getPhone_number());
            passPhon.setFont(font2);
            passPhon.setBounds(370, 220, 300, 30);

            f.add(passInfo);
            f.add(passNameCont);
            f.add(passPhon);

        } else {
            String driver = con.getUsername(listOrder.get(listOrder.size() - 1).getDriver_id());

            JLabel driverInfo = new JLabel("Info Driver:");
            driverInfo.setFont(font4);
            driverInfo.setBounds(30, 190, 300, 30);

            JLabel driverName = new JLabel(driver);
            driverName.setFont(font4);
            driverName.setBounds(30, 220, 300, 30);

            ArrayList<Driver> driverCont = con.getDriverByID(listOrder.get(listOrder.size() - 1).getDriver_id());

            JLabel driverPhon = new JLabel(driverCont.get(driverCont.size() - 1).getDriver_phonNum());
            driverPhon.setFont(font2);
            driverPhon.setBounds(370, 220, 300, 30);

            JLabel driverVType = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_type());
            driverVType.setFont(font2);
            driverVType.setBounds(30, 245, 300, 30);

            JLabel driverVPlate = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_plate());
            driverVPlate.setFont(font2);
            driverVPlate.setBounds(390, 255, 300, 30);

            JLabel driverVName = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_name());
            driverVName.setFont(font2);
            driverVName.setBounds(30, 265, 300, 30);

            f.add(driverInfo);
            f.add(driverName);
            f.add(driverPhon);
            f.add(driverVType);
            f.add(driverVName);
            f.add(driverVPlate);
            
            String passName = con.getUsername(listOrder.get(listOrder.size() - 1).getCust_id());

            JLabel passInfo = new JLabel("Info Passanger:");
            passInfo.setFont(font4);
            passInfo.setBounds(30, 290, 300, 30);

            JLabel passNameCont = new JLabel(passName);
            passNameCont.setFont(font4);
            passNameCont.setBounds(30, 318, 300, 30);

            ArrayList<Passanger> passCont = con.getPassangerByID(listOrder.get(listOrder.size()-1).getCust_id());

            JLabel passPhon = new JLabel(passCont.get(passCont.size() - 1).getPhone_number());
            passPhon.setFont(font2);
            passPhon.setBounds(370, 318, 300, 30);

            f.add(passInfo);
            f.add(passNameCont);
            f.add(passPhon);
        }

        JLabel payDetail = new JLabel("Detail Pembayaran:");
        payDetail.setFont(font4);
        payDetail.setBounds(30, 350, 300, 30);

        JLabel payRaw = new JLabel("Biaya Perjalanan:");
        payRaw.setFont(font2);
        payRaw.setBounds(30, 375, 300, 30);

        JLabel payTax = new JLabel("Biaya  jasa aplikasi:");
        payTax.setFont(font2);
        payTax.setBounds(30, 400, 300, 30);

        JLabel payVoucher = new JLabel("Diskon Voucher:");
        payVoucher.setFont(font2);
        payVoucher.setBounds(30, 425, 300, 30);

        JLabel total = new JLabel("Total:");
        total.setFont(font4);
        total.setBounds(30, 455, 300, 30);

        JLabel payRawVal = new JLabel("Rp. " + listOrder.get(listOrder.size() - 1).getOrder_price());
        payRawVal.setFont(font2);
        payRawVal.setBounds(370, 375, 300, 30);

        JLabel payTaxVal = new JLabel("Rp. 2000");
        payTaxVal.setFont(font2);
        payTaxVal.setBounds(370, 400, 300, 30);

        JLabel payVoucherVal = new JLabel("-BELUM");
        payVoucherVal.setFont(font2);
        payVoucherVal.setBounds(370, 425, 300, 30);

        JLabel totalVal = new JLabel("Rp. " + listOrder.get(listOrder.size() - 1).getOrder_final_price());
        totalVal.setFont(font4);
        totalVal.setBounds(360, 455, 300, 30);

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                if (id == 1) {
                    new MainMenuAdmin();
                } else {
                    new OrderBerjalan(id);
                }
            }
        });

        f.add((intro));
        f.add(intro2);

        f.add(time);
        f.add(idForShow);

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
        f.setLayout(null);
        f.setVisible(true);
    }

}
