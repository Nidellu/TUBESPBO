<<<<<<< HEAD
package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.JopayWaitingList;

public class InboxDriver {

    JFrame f;

    public InboxDriver(int idDriver) {
        showResult(idDriver);
    }

    private void showResult(int idDriver) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // list of promo yang di get pakai controller
        ArrayList<JopayWaitingList> jopayList = Controller.getInstance().getWaitingList(idDriver);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font3 = new Font("Courier", Font.PLAIN, 18);

        JLabel intro = FrameHandler.createLabel("Inbox Driver", font, 30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        String lineText = "__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________";
        JLabel lineDiv = FrameHandler.createLabel(lineText, null, 10, 100, 460, 20);

        if (jopayList.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... belum ada yang top up :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(110, 300, 400, 30);
            f.add(ingpo);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 445, 420);
        f.getContentPane().add(scrollPane);

        JPanel containerJwl = new JPanel();
        scrollPane.setViewportView(containerJwl);
        containerJwl.setLayout(new BorderLayout(0, 0));

        JPanel jwlContainer = new JPanel();
        containerJwl.add(jwlContainer, BorderLayout.NORTH);
        jwlContainer.setLayout(new GridLayout(0, 1, 0, 1));
        jwlContainer.setBackground(Color.gray);

        for (JopayWaitingList jwl : jopayList) {
            JPanel jwlPanel = createPromoPanel(jwl, idDriver);
            jwlContainer.add(jwlPanel);
        }

        JButton backButton = FrameHandler.createButton("Kembali", fontButton, 10, 10, 85, 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuDriver(idDriver);
            }
        });

        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private JPanel createPromoPanel(JopayWaitingList jwl, int idDriver) {
        JPanel jwlPanel = new JPanel();
        jwlPanel.setPreferredSize(new Dimension(300, 60));

        Font font2 = new Font("Courier", Font.BOLD, 14);

        jwlPanel.setLayout(null);
        jwlPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        String username = Controller.getInstance().getUsername(jwl.getCust_id());

        JLabel promoCode = FrameHandler.createLabel("Permintaan : " + username, font2, 10, 5, 200, 25);
        promoCode.setBorder(null);
        jwlPanel.add(promoCode);

        JLabel promoVaField = FrameHandler.createLabel("Nominal: " + jwl.getNominal(), font2, 10, 30, 200, 20);
        promoVaField.setBorder(null);
        jwlPanel.add(promoVaField);

        JButton terima = FrameHandler.createButton("Terima", font2, 230, 20, 90, 25, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float currSaldo = Controller.getInstance().getWallet(jwl.getCust_id()); // get passenger saldo
                float currSaldo2 = Controller.getInstance().getWallet(jwl.getDriver_id()); // get driver saldo
                int choice = FrameHandler.showConfirmationDialog("Terima top up?", "Konfirmasi");
                if (choice == JOptionPane.YES_OPTION) {
                    if (currSaldo2 > jwl.getNominal()) {
                        boolean succeed = Controller.getInstance().updateJoPay(idDriver, currSaldo2 - jwl.getNominal());
                        boolean succeed2 = Controller.getInstance().updateJoPay(jwl.getCust_id(), currSaldo + jwl.getNominal());
                        boolean succeed3 = Controller.getInstance().deleteWaitingJopay(jwl.getJopaylist_id());
                        if (succeed && succeed2 && succeed3) {
                            FrameHandler.showInformationMessage("Berhasil di top up!", "");
                            f.dispose();
                            new InboxDriver(idDriver);
                        } else {
                            FrameHandler.showInformationMessage("Gagal di top up!", "");
                        }
                    } else {
                        FrameHandler.showInformationMessage("Saldo tidak mencukupi buat top up!", "");
                    }
                } else {
                    FrameHandler.showInformationMessage("Batal top up!", "");
                }
            }
        });
        jwlPanel.add(terima);

        JButton tolak = FrameHandler.createButton("Tolak", font2, 330, 20, 90, 25, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = FrameHandler.showConfirmationDialog("Tolak top up?", "Konfirmasi");

                if (choice == JOptionPane.YES_OPTION) {
                    boolean succeed3 = Controller.getInstance().deleteWaitingJopay(jwl.getJopaylist_id());
                    if (succeed3) {
                        FrameHandler.showInformationMessage("Berhasil ditolak!", "");
                        f.dispose();
                        new InboxDriver(idDriver);
                    } else {
                        FrameHandler.showInformationMessage("Gagal ditolak!", "");
                    }
                } else {
                    FrameHandler.showInformationMessage("Batal ditolak", "");
                }
            }
        });
        jwlPanel.add(tolak);
        return jwlPanel;
    }
}
=======
package tubespbo.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tubespbo.Controller.Controller;
import tubespbo.Model.JopayWaitingList;

public class InboxDriver {

    public InboxDriver(int idDriver) {
        showResult(idDriver);
    }

    private void showResult(int idDriver) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // list of promo yang di get pakai controller
        ArrayList<JopayWaitingList> jopayList = Controller.getInstance().getWaitingList(idDriver);

        Font font = new Font("Courier", Font.BOLD, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 14);
        Font font3 = new Font("Courier", Font.PLAIN, 18);

        JLabel intro = new JLabel("Inbox Driver");
        intro.setFont(font);
        intro.setBounds(30, 70, 400, 30);

        Font fontButton = new Font("Courier", Font.BOLD, 13);

        JLabel lineDiv = new JLabel("__________________________________"
                + "__________________________________________________"
                + "__________________________________________________"
                + "___________________________");

        lineDiv.setBounds(10, 100, 460, 20);

        if (jopayList.isEmpty()) {
            JLabel ingpo = new JLabel("Yah... belum ada yang top up :'(");
            ingpo.setFont(font3);
            ingpo.setBounds(110, 300, 400, 30);
            f.add(ingpo);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 445, 420);
        f.getContentPane().add(scrollPane);

        JPanel containerJwl = new JPanel();
        scrollPane.setViewportView(containerJwl);
        containerJwl.setLayout(new BorderLayout(0, 0));

        JPanel jwlContainer = new JPanel();
        containerJwl.add(jwlContainer, BorderLayout.NORTH);
        jwlContainer.setLayout(new GridLayout(0, 1, 0, 1));
        jwlContainer.setBackground(Color.gray);

        for (JopayWaitingList jwl : jopayList) {
            JPanel jwlPanel = new JPanel();
            jwlPanel.setFont(font2);
            jwlPanel.setPreferredSize(new Dimension(300, 60));
            jwlContainer.add(jwlPanel);
            jwlPanel.setLayout(null);

            jwlPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

            String username = Controller.getInstance().getUsername(jwl.getCust_id());

            JLabel promoCode = new JLabel("Permintaan : " + username);
            promoCode.setBounds(10, 5, 200, 25);
            promoCode.setBorder(null);
            jwlPanel.add(promoCode);

            JLabel promoVaField = new JLabel("Nominal: " + jwl.getNominal());
            promoVaField.setBounds(10, 30, 200, 20);
            promoVaField.setBorder(null);
            jwlPanel.add(promoVaField);

            JButton terima = new JButton("Terima");
            terima.setBounds(230, 20, 90, 25);
            terima.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    float currSaldo = Controller.getInstance().getWallet(jwl.getCust_id()); // get passanger saldo
                    float currSaldo2 = Controller.getInstance().getWallet(jwl.getDriver_id()); // get driver saldo
                    int choice = JOptionPane.showConfirmDialog(null, "Terima top up?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        if (currSaldo2 > jwl.getNominal()) {
                            boolean succeed = Controller.getInstance().updateJoPay(idDriver, currSaldo2 - jwl.getNominal());
                            boolean succeed2 = Controller.getInstance().updateJoPay(jwl.getCust_id(), currSaldo + jwl.getNominal());
                            boolean succeed3 = Controller.getInstance().deleteWaitingJopay(jwl.getJopaylist_id());
                            if (succeed && succeed2 && succeed3) {
                                JOptionPane.showMessageDialog(null, "Berhasil di top up!", "", JOptionPane.INFORMATION_MESSAGE);
                                f.dispose();
                                new InboxDriver(idDriver);
                            } else {
                                JOptionPane.showMessageDialog(null, "Gagal di top up!", "", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi buat top up!", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Batal top up!", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            jwlPanel.add(terima);

            JButton tolak = new JButton("Tolak");
            tolak.setBounds(330, 20, 90, 25);
            tolak.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "Tolak top up?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        boolean succeed3 = Controller.getInstance().deleteWaitingJopay(jwl.getJopaylist_id());
                            if (succeed3) {
                                JOptionPane.showMessageDialog(null, "Berhasil di tolak!", "", JOptionPane.INFORMATION_MESSAGE);
                                f.dispose();
                                new InboxDriver(idDriver);
                            } else {
                                JOptionPane.showMessageDialog(null, "Gagal ditolak!", "", JOptionPane.INFORMATION_MESSAGE);
                            }
                    } else {
                        JOptionPane.showMessageDialog(null, "Batal ditolak", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            jwlPanel.add(tolak);
        }

        JButton backButton = new JButton("Kembali");
        backButton.setFont(fontButton);
        backButton.setBounds(10, 10, 85, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuDriver(idDriver);
            }
        });

        f.add((intro));

        f.add(backButton);
        f.add(lineDiv);

        f.setSize(500, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new InboxDriver(6);
    }
}
>>>>>>> master
