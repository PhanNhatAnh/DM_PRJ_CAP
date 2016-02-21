/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityJPAControl;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Room;
import Entity.Account;
import Entity.RoomAccount;
import EntityJPAControl.exceptions.NonexistentEntityException;
import EntityJPAControl.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class RoomAccountJpaController implements Serializable {

    public RoomAccountJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RoomAccount roomAccount) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room roomID = roomAccount.getRoomID();
            if (roomID != null) {
                roomID = em.getReference(roomID.getClass(), roomID.getId());
                roomAccount.setRoomID(roomID);
            }
            Account accountID = roomAccount.getAccountID();
            if (accountID != null) {
                accountID = em.getReference(accountID.getClass(), accountID.getId());
                roomAccount.setAccountID(accountID);
            }
            em.persist(roomAccount);
            if (roomID != null) {
                roomID.getRoomAccountList().add(roomAccount);
                roomID = em.merge(roomID);
            }
            if (accountID != null) {
                accountID.getRoomAccountList().add(roomAccount);
                accountID = em.merge(accountID);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoomAccount(roomAccount.getId()) != null) {
                throw new PreexistingEntityException("RoomAccount " + roomAccount + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RoomAccount roomAccount) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RoomAccount persistentRoomAccount = em.find(RoomAccount.class, roomAccount.getId());
            Room roomIDOld = persistentRoomAccount.getRoomID();
            Room roomIDNew = roomAccount.getRoomID();
            Account accountIDOld = persistentRoomAccount.getAccountID();
            Account accountIDNew = roomAccount.getAccountID();
            if (roomIDNew != null) {
                roomIDNew = em.getReference(roomIDNew.getClass(), roomIDNew.getId());
                roomAccount.setRoomID(roomIDNew);
            }
            if (accountIDNew != null) {
                accountIDNew = em.getReference(accountIDNew.getClass(), accountIDNew.getId());
                roomAccount.setAccountID(accountIDNew);
            }
            roomAccount = em.merge(roomAccount);
            if (roomIDOld != null && !roomIDOld.equals(roomIDNew)) {
                roomIDOld.getRoomAccountList().remove(roomAccount);
                roomIDOld = em.merge(roomIDOld);
            }
            if (roomIDNew != null && !roomIDNew.equals(roomIDOld)) {
                roomIDNew.getRoomAccountList().add(roomAccount);
                roomIDNew = em.merge(roomIDNew);
            }
            if (accountIDOld != null && !accountIDOld.equals(accountIDNew)) {
                accountIDOld.getRoomAccountList().remove(roomAccount);
                accountIDOld = em.merge(accountIDOld);
            }
            if (accountIDNew != null && !accountIDNew.equals(accountIDOld)) {
                accountIDNew.getRoomAccountList().add(roomAccount);
                accountIDNew = em.merge(accountIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roomAccount.getId();
                if (findRoomAccount(id) == null) {
                    throw new NonexistentEntityException("The roomAccount with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RoomAccount roomAccount;
            try {
                roomAccount = em.getReference(RoomAccount.class, id);
                roomAccount.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roomAccount with id " + id + " no longer exists.", enfe);
            }
            Room roomID = roomAccount.getRoomID();
            if (roomID != null) {
                roomID.getRoomAccountList().remove(roomAccount);
                roomID = em.merge(roomID);
            }
            Account accountID = roomAccount.getAccountID();
            if (accountID != null) {
                accountID.getRoomAccountList().remove(roomAccount);
                accountID = em.merge(accountID);
            }
            em.remove(roomAccount);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RoomAccount> findRoomAccountEntities() {
        return findRoomAccountEntities(true, -1, -1);
    }

    public List<RoomAccount> findRoomAccountEntities(int maxResults, int firstResult) {
        return findRoomAccountEntities(false, maxResults, firstResult);
    }

    private List<RoomAccount> findRoomAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RoomAccount.class));
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

    public RoomAccount findRoomAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RoomAccount.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RoomAccount> rt = cq.from(RoomAccount.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
