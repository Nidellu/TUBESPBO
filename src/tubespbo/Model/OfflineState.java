/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubespbo.Model;

public class OfflineState implements DriverStatusInterface {

    @Override
    public String update(Driver driver, String status) {
        driver.state = new AvailableState();
        return status = "AVAILABLE";
    }
}

