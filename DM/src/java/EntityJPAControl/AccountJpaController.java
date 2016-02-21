/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityJPAControl;

import Entity.Account;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Role;
import Entity.RoomAccount;
import EntityJPAControl.exceptions.IllegalOrphanException;
import EntityJPAControl.exceptions.NonexistentEntityException;
import EntityJPAControl.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class AccountJpaController implements Serializable {

    public AccountJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Account account) throws PreexistingEntityException, Exception {
        if (account.getRoomAccountList() == null) {
            account.setRoomAccountList(new ArrayList<RoomAccount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role roleID = account.getRoleID();
            if (roleID != null) {
                roleID = em.getReference(roleID.getClass(), roleID.getId());
                account.setRoleID(roleID);
            }
            List<RoomAccount> attachedRoomAccountList = new ArrayList<RoomAccount>();
            for (RoomAccount roomAccountListRoomAccountToAttach : account.getRoomAccountList()) {
                roomAccountListRoomAccountToAttach = em.getReference(roomAccountListRoomAccountToAttach.getClass(), roomAccountListRoomAccountToAttach.getId());
                attachedRoomAccountList.add(roomAccountListRoomAccountToAttach);
            }
            account.setRoomAccountList(attachedRoomAccountList);
            em.persist(account);
            if (roleID != null) {
                roleID.getAccountList().add(account);
                roleID = em.merge(roleID);
            }
            for (RoomAccount roomAccountListRoomAccount : account.getRoomAccountList()) {
                Account oldAccountIDOfRoomAccountListRoomAccount = roomAccountListRoomAccount.getAccountID();
                roomAccountListRoomAccount.setAccountID(account);
                roomAccountListRoomAccount = em.merge(roomAccountListRoomAccount);
                if (oldAccountIDOfRoomAccountListRoomAccount != null) {
                    oldAccountIDOfRoomAccountListRoomAccount.getRoomAccountList().remove(roomAccountListRoomAccount);
                    oldAccountIDOfRoomAccountListRoomAccount = em.merge(oldAccountIDOfRoomAccountListRoomAccount);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccount(account.getId()) != null) {
                throw new PreexistingEntityException("Account " + account + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Account account) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account persistentAccount = em.find(Account.class, account.getId());
            Role roleIDOld = persistentAccount.getRoleID();
            Role roleIDNew = account.getRoleID();
            List<RoomAccount> roomAccountListOld = persistentAccount.getRoomAccountList();
            List<RoomAccount> roomAccountListNew = account.getRoomAccountList();
            List<String> illegalOrphanMessages = null;
            for (RoomAccount roomAccountListOldRoomAccount : roomAccountListOld) {
                if (!roomAccountListNew.contains(roomAccountListOldRoomAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RoomAccount " + roomAccountListOldRoomAccount + " since its accountID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (roleIDNew != null) {
                roleIDNew = em.getReference(roleIDNew.getClass(), roleIDNew.getId());
                account.setRoleID(roleIDNew);
            }
            List<RoomAccount> attachedRoomAccountListNew = new ArrayList<RoomAccount>();
            for (RoomAccount roomAccountListNewRoomAccountToAttach : roomAccountListNew) {
                roomAccountListNewRoomAccountToAttach = em.getReference(roomAccountListNewRoomAccountToAttach.getClass(), roomAccountListNewRoomAccountToAttach.getId());
                attachedRoomAccountListNew.add(roomAccountListNewRoomAccountToAttach);
            }
            roomAccountListNew = attachedRoomAccountListNew;
            account.setRoomAccountList(roomAccountListNew);
            account = em.merge(account);
            if (roleIDOld != null && !roleIDOld.equals(roleIDNew)) {
                roleIDOld.getAccountList().remove(account);
                roleIDOld = em.merge(roleIDOld);
            }
            if (roleIDNew != null && !roleIDNew.equals(roleIDOld)) {
                roleIDNew.getAccountList().add(account);
                roleIDNew = em.merge(roleIDNew);
            }
            for (RoomAccount roomAccountListNewRoomAccount : roomAccountListNew) {
                if (!roomAccountListOld.contains(roomAccountListNewRoomAccount)) {
                    Account oldAccountIDOfRoomAccountListNewRoomAccount = roomAccountListNewRoomAccount.getAccountID();
                    roomAccountListNewRoomAccount.setAccountID(account);
                    roomAccountListNewRoomAccount = em.merge(roomAccountListNewRoomAccount);
                    if (oldAccountIDOfRoomAccountListNewRoomAccount != null && !oldAccountIDOfRoomAccountListNewRoomAccount.equals(account)) {
                        oldAccountIDOfRoomAccountListNewRoomAccount.getRoomAccountList().remove(roomAccountListNewRoomAccount);
                        oldAccountIDOfRoomAccountListNewRoomAccount = em.merge(oldAccountIDOfRoomAccountListNewRoomAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = account.getId();
                if (findAccount(id) == null) {
                    throw new NonexistentEntityException("The account with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account account;
            try {
                account = em.getReference(Account.class, id);
                account.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The account with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RoomAccount> roomAccountListOrphanCheck = account.getRoomAccountList();
            for (RoomAccount roomAccountListOrphanCheckRoomAccount : roomAccountListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Account (" + account + ") cannot be destroyed since the RoomAccount " + roomAccountListOrphanCheckRoomAccount + " in its roomAccountList field has a non-nullable accountID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Role roleID = account.getRoleID();
            if (roleID != null) {
                roleID.getAccountList().remove(account);
                roleID = em.merge(roleID);
            }
            em.remove(account);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Account> findAccountEntities() {
        return findAccountEntities(true, -1, -1);
    }

    public List<Account> findAccountEntities(int maxResults, int firstResult) {
        return findAccountEntities(false, maxResults, firstResult);
    }

    private List<Account> findAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Account.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Account findAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Account.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Account> rt = cq.from(Account.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
