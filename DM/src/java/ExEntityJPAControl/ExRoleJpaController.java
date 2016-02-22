/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExEntityJPAControl;

import EntityJPAControl.RoleJpaController;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class ExRoleJpaController extends RoleJpaController{

    public ExRoleJpaController(EntityManagerFactory emf) {
        super(emf);
    }
    
}
