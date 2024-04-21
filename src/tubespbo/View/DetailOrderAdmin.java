package tubespbo.View;

import java.util.ArrayList;

import javax.swing.JLabel;
import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;
import tubespbo.Model.Passanger;

public class DetailOrderAdmin extends DetailOrder {

    public DetailOrderAdmin(int id, int idOrder, int menu) {
        super(id, idOrder, menu);
        showDataScreenAdmin(id, idOrder, menu);
    }

    private void showDataScreenAdmin(int id, int idOrder, int menu) {

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
        JLabel lineDiv = FrameHandler.createLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________", null, 10, 120, 465, 20);
        JLabel time = FrameHandler.createLabel("" + Controller.getInstance().getTimeOrder(idOrder), font2, 30, 150, 300, 30);
        JLabel idForShow = FrameHandler.createLabel("#" + idOrder, font4, 420, 150, 300, 30);
        JLabel driverInfo = FrameHandler.createLabel("Info Driver:", font4, 30, 190, 300, 30);
        String driver = Controller.getInstance().getUsername(listOrder.get(listOrder.size() - 1).getDriver_id());
        JLabel driverName = FrameHandler.createLabel(driver, font4, 30, 220, 300, 30);
        ArrayList<Driver> driverCont = Controller.getInstance().getDriverByID(listOrder.get(listOrder.size() - 1).getDriver_id());
        JLabel driverPhon = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getDriver_phonNum(), font2, 370, 220, 300, 30);
        JLabel driverVType = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getVehicle_type(), font2, 30, 245, 300, 30);
        JLabel driverVPlate = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getVehicle_plate(), font2, 390, 255, 300, 30);
        JLabel driverVName = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getVehicle_name(), font2, 30, 265, 300, 30);

        f.add(driverInfo);
        f.add(driverName);
        f.add(driverPhon);
        f.add(driverVType);
        f.add(driverVName);
        f.add(driverVPlate);

        String passName = Controller.getInstance().getUsername(listOrder.get(listOrder.size() - 1).getCust_id());
        JLabel passInfo = FrameHandler.createLabel("Info Passanger:", font4, 30, 290, 300, 30);
        JLabel passNameCont = FrameHandler.createLabel(passName, font4, 30, 318, 300, 30);
        ArrayList<Passanger> passCont = Controller.getInstance().getPassangerByID(listOrder.get(listOrder.size() - 1).getCust_id());
        JLabel passPhon = FrameHandler.createLabel(passCont.get(passCont.size() - 1).getPhone_number(), font2, 370, 318, 300, 30);

        f.add(intro);
        f.add(intro2);
        f.add(passInfo);
        f.add(passNameCont);
        f.add(passPhon);

        f.add(time);
        f.add(idForShow);

        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

}
