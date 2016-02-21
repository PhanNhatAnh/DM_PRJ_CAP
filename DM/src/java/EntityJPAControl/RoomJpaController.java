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
import Entity.CatergoryDocument;
import Entity.Room;
import java.util.ArrayList;
import java.util.List;
import Entity.RoomAccount;
import EntityJPAControl.exceptions.IllegalOrphanException;
import EntityJPAControl.exceptions.NonexistentEntityException;
import EntityJPAControl.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class RoomJpaController implements Serializable {

    public RoomJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Room room) throws PreexistingEntityException, Exception {
        if (room.getCatergoryDocumentList() == null) {
            room.setCatergoryDocumentList(new ArrayList<CatergoryDocument>());
        }
        if (room.getRoomAccountList() == null) {
            room.setRoomAccountList(new ArrayList<RoomAccount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CatergoryDocument> attachedCatergoryDocumentList = new ArrayList<CatergoryDocument>();
            for (CatergoryDocument catergoryDocumentListCatergoryDocumentToAttach : room.getCatergoryDocumentList()) {
                catergoryDocumentListCatergoryDocumentToAttach = em.getReference(catergoryDocumentListCatergoryDocumentToAttach.getClass(), catergoryDocumentListCatergoryDocumentToAttach.getId());
                attachedCatergoryDocumentList.add(catergoryDocumentListCatergoryDocumentToAttach);
            }
            room.setCatergoryDocumentList(attachedCatergoryDocumentList);
            List<RoomAccount> attachedRoomAccountList = new ArrayList<RoomAccount>();
            for (RoomAccount roomAccountListRoomAccountToAttach : room.getRoomAccountList()) {
                roomAccountListRoomAccountToAttach = em.getReference(roomAccountListRoomAccountToAttach.getClass(), roomAccountListRoomAccountToAttach.getId());
                attachedRoomAccountList.add(roomAccountListRoomAccountToAttach);
            }
            room.setRoomAccountList(attachedRoomAccountList);
            em.persist(room);
            for (CatergoryDocument catergoryDocumentListCatergoryDocument : room.getCatergoryDocumentList()) {
                Room oldRoomIDOfCatergoryDocumentListCatergoryDocument = catergoryDocumentListCatergoryDocument.getRoomID();
                catergoryDocumentListCatergoryDocument.setRoomID(room);
                catergoryDocumentListCatergoryDocument = em.merge(catergoryDocumentListCatergoryDocument);
                if (oldRoomIDOfCatergoryDocumentListCatergoryDocument != null) {
                    oldRoomIDOfCatergoryDocumentListCatergoryDocument.getCatergoryDocumentList().remove(catergoryDocumentListCatergoryDocument);
                    oldRoomIDOfCatergoryDocumentListCatergoryDocument = em.merge(oldRoomIDOfCatergoryDocumentListCatergoryDocument);
                }
            }
            for (RoomAccount roomAccountListRoomAccount : room.getRoomAccountList()) {
                Room oldRoomIDOfRoomAccountListRoomAccount = roomAccountListRoomAccount.getRoomID();
                roomAccountListRoomAccount.setRoomID(room);
                roomAccountListRoomAccount = em.merge(roomAccountListRoomAccount);
                if (oldRoomIDOfRoomAccountListRoomAccount != null) {
                    oldRoomIDOfRoomAccountListRoomAccount.getRoomAccountList().remove(roomAccountListRoomAccount);
                    oldRoomIDOfRoomAccountListRoomAccount = em.merge(oldRoomIDOfRoomAccountListRoomAccount);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoom(room.getId()) != null) {
                throw new PreexistingEntityException("Room " + room + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Room room) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room persistentRoom = em.find(Room.class, room.getId());
            List<CatergoryDocument> catergoryDocumentListOld = persistentRoom.getCatergoryDocumentList();
            List<CatergoryDocument> catergoryDocumentListNew = room.getCatergoryDocumentList();
            List<RoomAccount> roomAccountListOld = persistentRoom.getRoomAccountList();
            List<RoomAccount> roomAccountListNew = room.getRoomAccountList();
            List<String> illegalOrphanMessages = null;
            for (CatergoryDocument catergoryDocumentListOldCatergoryDocument : catergoryDocumentListOld) {
                if (!catergoryDocumentListNew.contains(catergoryDocumentListOldCatergoryDocument)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CatergoryDocument " + catergoryDocumentListOldCatergoryDocument + " since its roomID field is not nullable.");
                }
            }
            for (RoomAccount roomAccountListOldRoomAccount : roomAccountListOld) {
                if (!roomAccountListNew.contains(roomAccountListOldRoomAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RoomAccount " + roomAccountListOldRoomAccount + " since its roomID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CatergoryDocument> attachedCatergoryDocumentListNew = new ArrayList<CatergoryDocument>();
            for (CatergoryDocument catergoryDocumentListNewCatergoryDocumentToAttach : catergoryDocumentListNew) {
                catergoryDocumentListNewCatergoryDocumentToAttach = em.getReference(catergoryDocumentListNewCatergoryDocumentToAttach.getClass(), catergoryDocumentListNewCatergoryDocumentToAttach.getId());
                attachedCatergoryDocumentListNew.add(catergoryDocumentListNewCatergoryDocumentToAttach);
            }
            catergoryDocumentListNew = attachedCatergoryDocumentListNew;
            room.setCatergoryDocumentList(catergoryDocumentListNew);
            List<RoomAccount> attachedRoomAccountListNew = new ArrayList<RoomAccount>();
            for (RoomAccount roomAccountListNewRoomAccountToAttach : roomAccountListNew) {
                roomAccountListNewRoomAccountToAttach = em.getReference(roomAccountListNewRoomAccountToAttach.getClass(), roomAccountListNewRoomAccountToAttach.getId());
                attachedRoomAccountListNew.add(roomAccountListNewRoomAccountToAttach);
            }
            roomAccountListNew = attachedRoomAccountListNew;
            room.setRoomAccountList(roomAccountListNew);
            room = em.merge(room);
            for (CatergoryDocument catergoryDocumentListNewCatergoryDocument : catergoryDocumentListNew) {
                if (!catergoryDocumentListOld.contains(catergoryDocumentListNewCatergoryDocument)) {
                    Room oldRoomIDOfCatergoryDocumentListNewCatergoryDocument = catergoryDocumentListNewCatergoryDocument.getRoomID();
                    catergoryDocumentListNewCatergoryDocument.setRoomID(room);
                    catergoryDocumentListNewCatergoryDocument = em.merge(catergoryDocumentListNewCatergoryDocument);
                    if (oldRoomIDOfCatergoryDocumentListNewCatergoryDocument != null && !oldRoomIDOfCatergoryDocumentListNewCatergoryDocument.equals(room)) {
                        oldRoomIDOfCatergoryDocumentListNewCatergoryDocument.getCatergoryDocumentList().remove(catergoryDocumentListNewCatergoryDocument);
                        oldRoomIDOfCatergoryDocumentListNewCatergoryDocument = em.merge(oldRoomIDOfCatergoryDocumentListNewCatergoryDocument);
                    }
                }
            }
            for (RoomAccount roomAccountListNewRoomAccount : roomAccountListNew) {
                if (!roomAccountListOld.contains(roomAccountListNewRoomAccount)) {
                    Room oldRoomIDOfRoomAccountListNewRoomAccount = roomAccountListNewRoomAccount.getRoomID();
                    roomAccountListNewRoomAccount.setRoomID(room);
                    roomAccountListNewRoomAccount = em.merge(roomAccountListNewRoomAccount);
                    if (oldRoomIDOfRoomAccountListNewRoomAccount != null && !oldRoomIDOfRoomAccountListNewRoomAccount.equals(room)) {
                        oldRoomIDOfRoomAccountListNewRoomAccount.getRoomAccountList().remove(roomAccountListNewRoomAccount);
                        oldRoomIDOfRoomAccountListNewRoomAccount = em.merge(oldRoomIDOfRoomAccountListNewRoomAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = room.getId();
                if (findRoom(id) == null) {
                    throw new NonexistentEntityException("The room with id " + id + " no longer exists.");
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
            Room room;
            try {
                room = em.getReference(Room.class, id);
                room.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The room with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CatergoryDocument> catergoryDocumentListOrphanCheck = room.getCatergoryDocumentList();
            for (CatergoryDocument catergoryDocumentListOrphanCheckCatergoryDocument : catergoryDocumentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Room (" + room + ") cannot be destroyed since the CatergoryDocument " + catergoryDocumentListOrphanCheckCatergoryDocument + " in its catergoryDocumentList field has a non-nullable roomID field.");
            }
            List<RoomAccount> roomAccountListOrphanCheck = room.getRoomAccountList();
            for (RoomAccount roomAccountListOrphanCheckRoomAccount : roomAccountListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Room (" + room + ") cannot be destroyed since the RoomAccount " + roomAccountListOrphanCheckRoomAccount + " in its roomAccountList field has a non-nullable roomID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(room);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Room> findRoomEntities() {
        return findRoomEntities(true, -1, -1);
    }

    public List<Room> findRoomEntities(int maxResults, int firstResult) {
        return findRoomEntities(false, maxResults, firstResult);
    }

    private List<Room> findRoomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Room.class));
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

    public Room findRoom(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Room.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Room> rt = cq.from(Room.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
