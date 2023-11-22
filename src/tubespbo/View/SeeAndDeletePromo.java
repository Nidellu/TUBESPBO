package tubespbo.View;

import java.util.ArrayList;

import javax.swing.JFrame;

import tubespbo.Controller.Controller;
import tubespbo.Model.Passanger;
import tubespbo.Model.Promo;

public class SeeAndDeletePromo {

    public SeeAndDeletePromo(int id) {
        showResult(id);
    }

    private void showResult(int id) {
        Controller cntrl = new Controller();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // list of promo yang di get pakai controller
        ArrayList<Promo> promolList = cntrl.getPromoList();
        
    }
}
