/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aking
 */
public class BaseService {

    /**
     * Define EntityManagerFactory.
     */
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DMPU");

    /**
     * @return EntityManagerFactory.
     */
    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
