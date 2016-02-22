/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Role;
import ExEntityJPAControl.ExRoleJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class RoleService extends BaseService{
    /* Request Jpa controller. */
    private final ExRoleJpaController controller;

    /**
     * contructor.
     */
    public RoleService() {
        controller = new ExRoleJpaController(super.getEmf());
    }
    /* Default constructor to create controller */

    public RoleService(EntityManagerFactory emf) {
        controller = new ExRoleJpaController(emf);
    }
    
    public Role findByID(int id) {
        return controller.findRole(id);
    }
    
    public List<Role> listAllRole(){
        return controller.findRoleEntities();
    }
}
