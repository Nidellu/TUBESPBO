<<<<<<< HEAD
package tubespbo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;
import tubespbo.Model.OrderStatusEnum;

public class DetailOrderPassanger extends DetailOrder {

    public DetailOrderPassanger(int id, int idOrder, int menu) {
        super(id, idOrder, menu);
        showDataScreenPassanger(id, idOrder, menu);
    }

    private void showDataScreenPassanger(int id, int idOrder, int menu) {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String driver = Controller.getInstance().getUsername(listOrder.get(listOrder.size() - 1).getDriver_id());

        JLabel driverInfo = FrameHandler.createLabel("Info Driver:", font4, 30, 190, 300, 30);
        f.add(driverInfo);

        JLabel driverName = FrameHandler.createLabel(driver, font4, 30, 220, 300, 30);
        f.add(driverName);
        ArrayList<Driver> driverCont = Controller.getInstance().getDriverByID(listOrder.get(listOrder.size() - 1).getDriver_id());
        JLabel driverPhon = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getDriver_phonNum(), font2, 370, 220, 300, 30);
        f.add(driverPhon);
        JLabel driverVType = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getVehicle_type(), font2, 30, 245, 300, 30);
        f.add(driverVType);
        JLabel driverVPlate = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getVehicle_plate(), font2, 390, 255, 300, 30);
        f.add(driverVPlate);
        JLabel driverVName = FrameHandler.createLabel(driverCont.get(driverCont.size() - 1).getVehicle_name(), font2, 30, 265, 300, 30);
        f.add(driverVName);

        if (listOrder.get(listOrder.size() - 1).getOrder_status() == OrderStatusEnum.NOW) {
            JButton cancel = FrameHandler.createButton("Top Up ke Driver", new Font("Courier", Font.BOLD, 12), 30, 310, 420, 30, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new MenuTopUpDariDriver(id, listOrder.get(listOrder.size() - 1).getDriver_id(), driver);
                }
            });
            f.add(cancel);

            JButton topUp = FrameHandler.createButton("Cancel", new Font("Courier", Font.BOLD, 12), 30, 350, 420, 30, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int choice = FrameHandler.showConfirmationDialog("Yakin mau dicancel?", "Konfirmasi");

                    if (choice == JOptionPane.YES_OPTION) {
                        if (Controller.getInstance().updateStatusOrder(idOrder, "CANCEL")) {
                            FrameHandler.showInformationMessage("Berhasil dicancel", "Yahhh");
                            Controller.getInstance().changeToAvailable(driverCont.get(driverCont.size() - 1).getDriver_id());
                            f.dispose();
                            new CekOrder(id);
                        } else {
                            FrameHandler.showErrorDialog("Gagal di cancel", "Upss");
                        }

                    } else {
                        FrameHandler.showInformationMessage("Batal di cancel ya", "");
                    }
                }
            });

            f.add(topUp);
        }

        // adding driver's data to frame
        f.add(driverInfo);
        f.add(driverName);
        f.add(driverPhon);
        f.add(driverVType);
        f.add(driverVName);
        f.add(driverVPlate);

    }

}
=======
package tubespbo.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;
import tubespbo.Model.OrderStatusEnum;

public class DetailOrderPassanger extends DetailOrder {

    public DetailOrderPassanger(int id, int idOrder, int menu) {
        super(id, idOrder, menu);
        showDataScreenPassanger(id, idOrder,menu);
    }

    private void showDataScreenPassanger(int id, int idOrder, int menu) {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String driver = Controller.getInstance().getUsername(listOrder.get(listOrder.size() - 1).getDriver_id());

        JLabel driverInfo = new JLabel("Info Driver:");
        driverInfo.setFont(font4);
        driverInfo.setBounds(30, 190, 300, 30);

        // get drive's name
        JLabel driverName = new JLabel(driver);
        driverName.setFont(font4);
        driverName.setBounds(30, 220, 300, 30);

        ArrayList<Driver> driverCont = Controller.getInstance().getDriverByID(listOrder.get(listOrder.size() - 1).getDriver_id());

        // get driver's phone number
        JLabel driverPhon = new JLabel(driverCont.get(driverCont.size() - 1).getDriver_phonNum());
        driverPhon.setFont(font2);
        driverPhon.setBounds(370, 220, 300, 30);

        // get driver vehicle type
        JLabel driverVType = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_type());
        driverVType.setFont(font2);
        driverVType.setBounds(30, 245, 300, 30);

        // get driver's plate
        JLabel driverVPlate = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_plate());
        driverVPlate.setFont(font2);
        driverVPlate.setBounds(390, 255, 300, 30);

        // get driver vehicle's name
        JLabel driverVName = new JLabel(driverCont.get(driverCont.size() - 1).getVehicle_name());
        driverVName.setFont(font2);
        driverVName.setBounds(30, 265, 300, 30);

        if (listOrder.get(listOrder.size() - 1).getOrder_status() == OrderStatusEnum.NOW) {
            JButton cancel = new JButton("Top Up ke Driver");
            cancel.setBounds(30, 310, 420, 30);
            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new MenuTopUpDariDriver(id, listOrder.get(listOrder.size() - 1).getDriver_id(), driver);
                }
            });
            f.add(cancel);
            JButton topUp = new JButton("Cancel");
            topUp.setBounds(30, 350, 420, 30);
            topUp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "Yakin mau dicancel?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        if (Controller.getInstance().updateStatusOrder(idOrder, "CANCEL")) {
                            JOptionPane.showMessageDialog(null, "Berhasil dicancel", "Yahhh", JOptionPane.INFORMATION_MESSAGE);
                            // balikin status driver jadi available
                            Controller.getInstance().changeToAvailable(driverCont.get(driverCont.size() - 1).getDriver_id());
                            f.dispose();
                            new CekOrder(id);
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal di cancel", "Upss", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Batal di cancel ya", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            f.add(topUp);
        }

        // adding driver's data to frame
        f.add(driverInfo);
        f.add(driverName);
        f.add(driverPhon);
        f.add(driverVType);
        f.add(driverVName);
        f.add(driverVPlate);

    }

}
>>>>>>> master
