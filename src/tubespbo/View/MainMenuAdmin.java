/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubespbo.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tubespbo.Controller.Controller;
import tubespbo.Model.User;

public class MainMenuAdmin {

    public MainMenuAdmin(int id) {
        showDataScreen(id);
    }

    private void showDataScreen(int id) {
        Controller con = new Controller();
        ArrayList<User> listUser;

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String column[] = {"Id", "Name", "Email", "Password", "Category", "Photos"};
        Object dummy[][] = {};
        DefaultTableModel model = new DefaultTableModel(dummy, column);
//
//        for (int i = 0; i < listUser.size(); i++) {
//            int id = listUser.get(i).getId();
//            String name = listUser.get(i).getName();
//            String email = listUser.get(i).getEmail();
//            String password = listUser.get(i).getPassword();
//            int idCategory = listUser.get(i).getIdCategory();
//            String categoryString = con.getStringCategory(idCategory);
//            String photo = listUser.get(i).getPhoto();
//
//            ImageIcon originalFotoIcon = new ImageIcon(photo);
//
//            Object[] data = {id, name, email, password, categoryString, originalFotoIcon};
//
//            model.addRow(data);
//
//        }

    // button buat balik ke main menu
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(170, 350, 160, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();

            }
        });

    // button buat nambah promo
        JButton addPromoBtn = new JButton("Add New Promo");
        addPromoBtn.setBounds(100, 150, 200, 30);
        addPromoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new AddPromo();
            }
        });
    
    //button buat liat dafrat promo
        JButton seePromoBtn = new JButton("See All Promo");
        seePromoBtn.setBounds(340, 150, 200, 30);
        seePromoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new SeeAndDeletePromo();
            }
        });

        JTable table = new JTable(model) {
            public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
            }
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        table.setBounds(50, 100, 700, 200);
        JScrollPane sp = new JScrollPane(table);
        table.setRowHeight(50);

        JPanel panel = new JPanel(null);
        panel.add(backButton);
        panel.add(table);
        panel.add(sp);
        
        f.setSize(800, 500);
        f.add(panel);
        f.setVisible(true);

    }
}
