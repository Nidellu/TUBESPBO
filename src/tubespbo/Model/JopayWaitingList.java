/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tubespbo.Model;

/**
 *
 * @author brian
 */
public class JopayWaitingList{
    private int jopaylist_id;
    private int cust_id;
    private int driver_id;
    private float nominal;

    public JopayWaitingList(int jopaylist_id, int cust_id, int driver_id, float nominal) {
        this.jopaylist_id = jopaylist_id;
        this.cust_id = cust_id;
        this.driver_id = driver_id;
        this.nominal = nominal;
    }

    public int getJopaylist_id() {
        return jopaylist_id;
    }

    public void setJopaylist_id(int jopaylist_id) {
        this.jopaylist_id = jopaylist_id;
    }


    public JopayWaitingList() {
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public float getNominal() {
        return nominal;
    }

    public void setNominal(float nominal) {
        this.nominal = nominal;
    }

    
}
