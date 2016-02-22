/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Account;
import ExEntityJPAControl.ExAccountJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class AccountService extends BaseService {
    /* Request Jpa controller. */
    private final ExAccountJpaController controller;

    /**
     * contructor.
     */
    public AccountService() {
        controller = new ExAccountJpaController(super.getEmf());
    }
    /* Default constructor to create controller */

    public AccountService(EntityManagerFactory emf) {
        controller = new ExAccountJpaController(emf);
    }

    public Account checkLogin(String username, String password) {
        return controller.checkLogin(username, password);
    }

    public void createNewAccount(Account account) {
        try {
//            System.out.println("ACC:" + account.toString());
            controller.create(account);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
