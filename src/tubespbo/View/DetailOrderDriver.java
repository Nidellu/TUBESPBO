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

import tubespbo.Model.OrderStatusEnum;
import tubespbo.Model.Passanger;

public class DetailOrderDriver extends DetailOrder {

    public DetailOrderDriver(int id, int idOrder, int menu) {
        super(id, idOrder, menu);
        showDataScreenDriver(id, idOrder, menu);
    }

    private void showDataScreenDriver(int id, int idOrder, int menu) {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String passName = Controller.getInstance().getUsername(listOrder.get(listOrder.size() - 1).getCust_id());
        JLabel passInfo = FrameHandler.createLabel("Info Passanger:", font4, 30, 190, 300, 30);
        JLabel passNameCont = FrameHandler.createLabel(passName, font4, 30, 220, 300, 30);
        ArrayList<Passanger> passCont = Controller.getInstance().getPassangerByID(listOrder.get(listOrder.size() - 1).getCust_id());
        JLabel passPhon = FrameHandler.createLabel(passCont.get(passCont.size() - 1).getPhone_number(), font2, 370, 220, 300, 30);

        if (listOrder.get(listOrder.size() - 1).getOrder_status() == OrderStatusEnum.NOW) {
            JButton cancel = FrameHandler.createButton("Selesaikan Order", new Font("Courier", Font.BOLD, 12), 30, 310, 420, 30, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int choice = FrameHandler.showConfirmationDialog("Selesaikan?", "Konfirmasi");

                    if (choice == JOptionPane.YES_OPTION) {
                        if (Controller.getInstance().updateStatusOrder(idOrder, "FINISHED")) {
                            FrameHandler.showInformationMessage("Berhasil diselesaikan", "Yeay");
                            // get saldo user
                            float currSaldo = Controller.getInstance().getWallet(listOrder.get(listOrder.size() - 1).getCust_id());
                            // get saldo driver
                            float currSaldoDriver = Controller.getInstance().getWallet(listOrder.get(listOrder.size() - 1).getDriver_id());
                            float currSaldoAdmin = Controller.getInstance().getWallet(1);
                            Controller.getInstance().updateJoPay(1, currSaldoAdmin + 2000);
                            // nambah saldo driver
                            Controller.getInstance().updateJoPay(listOrder.get(listOrder.size() - 1).getDriver_id(), currSaldoDriver + listOrder.get(listOrder.size() - 1).getOrder_final_price() - 2000);
                            // kurangi saldo user
                            Controller.getInstance().updateJoPay(listOrder.get(listOrder.size() - 1).getCust_id(), currSaldo - listOrder.get(listOrder.size() - 1).getOrder_final_price());
                            // kembalikan status driver menjadi AVAILABLE
                            Controller.getInstance().changeToAvailable(id);
                            f.dispose();
                            new CekOrder(id);
                        } else {
                            FrameHandler.showErrorDialog("Gagal di diselesaikan", "Yahhh");
                        }
                    } else {
                        FrameHandler.showInformationMessage("Batal diselesaikan", "");
                    }
                }
            });
            f.add(cancel);
        }

        f.add(passInfo);
        f.add(passNameCont);
        f.add(passPhon);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

}
