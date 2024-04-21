package tubespbo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tubespbo.Controller.Controller;
import tubespbo.Model.Driver;

public class MainMenuDriver {

    JFrame f;

    public MainMenuDriver(int id) {
        showDataScreen(id);
    }
    protected Driver drv;

    private void showDataScreen(int id) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drv = Controller.getInstance().getDriverByID(id).get(0);

        String nameDisplay = Controller.getInstance().getUsername(id);

        JLabel intro = new JLabel("Selamat Datang di Josen " + nameDisplay + "!");
        Font font = new Font("Courier", Font.BOLD, 20);
        JLabel intro2 = new JLabel("Mau antar penumpang kemana hari ini ges?");
        Font font2 = new Font("Courier", Font.PLAIN, 16);
        intro.setFont(font);
        intro2.setFont(font2);
        intro.setBounds(10, 10, 400, 30);
        intro2.setBounds(10, 30, 470, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 12);

        JLabel lineDiv = new JLabel("_______________________________"
                + "__________________________________________");
        lineDiv.setBounds(10, 50, 500, 20);

        JButton profileButton = new JButton("Check Profil");
        profileButton.setFont(fontButton);
        profileButton.setBounds(10, 80, 470, 30);
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new DriverProfile(drv);
            }
        });

        JLabel border = new JLabel();
        border.setBorder(BorderFactory.createLineBorder(Color.black));
        border.setBounds(30, 135, 425, 60);

        String strSaldo = String.valueOf(Controller.getInstance().getWallet(id));
        if (Controller.getInstance().getWallet(id) > 9999999) {
            strSaldo = "9999999+";
        }

        JLabel wallet = new JLabel("JOPAY: Rp. " + strSaldo);
        wallet.setFont(font2);
        wallet.setBounds(50, 150, 800, 30);
        wallet.setBackground(null);

        JButton topUp = FrameHandler.createButton("Top Up", fontButton, 340, 150, 100, 30, e -> onTopUp(id));
        JButton inbox = FrameHandler.createButton("Inbox", fontButton, 70, 230, 140, 30, e -> onInbox(id));
        JButton historyOrder = FrameHandler.createButton("Lihat Pesanan", fontButton, 250, 230, 170, 30, e -> onCheckOrder(id));
        JButton tarikDanaButton = FrameHandler.createButton("Tarik Dana", fontButton, 70, 320, 350, 30, e -> onWithdraw(drv));
        JButton cekPendapatanBtn = FrameHandler.createButton("Cek Pendapatan Driver", fontButton, 70, 360, 350, 30, e -> onCheckIncome(id));
        JButton switchStatus = FrameHandler.createButton(Controller.getInstance().getSwitchStatusText(id), fontButton, 70, 410, 350, 30, e -> onSwitchStatus(id, drv));

        String statDrv = Controller.getInstance().getDriverStat(id);
        if (statDrv.equals("BOOKED")) {
            switchStatus.setEnabled(false); // Disable the button
        } else {
            switchStatus.setEnabled(true); // Enable the button
        }

        // back button
        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 170, 350, 150, 30, e -> onBack());

        // logout button
        JButton logOut = FrameHandler.createButton("Log out", fontButton, 340, 500, 100, 30, e -> onLogOut());

        f.add((intro));
        f.add((intro2));
        f.add(border);
        f.add(wallet);
        f.add(topUp);
        f.add(profileButton);
        f.add(cekPendapatanBtn);
        f.add(lineDiv);

        f.add(inbox);
        f.add(historyOrder);
        f.add(tarikDanaButton);
        f.add(switchStatus);
        f.add(logOut);

        f.setSize(500, 600);
        f.setLayout(null);

        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private void onTopUp(int id) {
        new MenuTopUp(id);
        f.dispose();
    }

    private void onInbox(int id) {
        f.dispose();
        new InboxDriver(id);
    }

    private void onCheckOrder(int id) {
        f.dispose();
        new CekOrder(id);
    }

    private void onWithdraw(Driver drv) {
        f.dispose();
        new TarikDana(drv);
    }

    private void onCheckIncome(int id) {
        f.dispose();
        new CekPendapatanDriver(id);
    }

    private void onSwitchStatus(int id, Driver drv) {
        String state = Controller.getInstance().getDriverStat(drv.getDriver_id());
        drv.setStateDriver(state);
        state = drv.updateState(state);
        boolean success = Controller.getInstance().updateDriverStatus(id, state);
        if (success) {
            FrameHandler.showMessageDialog("Status Berhasil Diubah!", "Yeay");
        } else {
            FrameHandler.showErrorDialog("Status Gagal Diubah!", "Upss");
        }
        f.dispose();
        new MainMenuDriver(id);
    }

    private void onBack() {
        f.dispose();
    }

    private void onLogOut() {
        f.dispose();
        new StartMenu();
    }
}
