/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExEntityJPAControl;

import Entity.Account;
import EntityJPAControl.AccountJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Aking
 */
public class ExAccountJpaController extends AccountJpaController {

    public ExAccountJpaController(EntityManagerFactory emf) {
        super(emf);
    }

    /**
     * Declare serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    public Account checkLogin(String username, String password) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.mail = :mail and a.passwords = :passwords");
            query.setParameter("mail", username);
            query.setParameter("passwords", password);
            Account account = (Account) query.getSingleResult();

            return account;
        } finally {
            em.close();
        }

    }
}
