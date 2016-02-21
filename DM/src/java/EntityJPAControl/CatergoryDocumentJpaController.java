/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityJPAControl;

import Entity.CatergoryDocument;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Room;
import Entity.Document;
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
public class CatergoryDocumentJpaController implements Serializable {

    public CatergoryDocumentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CatergoryDocument catergoryDocument) throws PreexistingEntityException, Exception {
        if (catergoryDocument.getDocumentList() == null) {
            catergoryDocument.setDocumentList(new ArrayList<Document>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room roomID = catergoryDocument.getRoomID();
            if (roomID != null) {
                roomID = em.getReference(roomID.getClass(), roomID.getId());
                catergoryDocument.setRoomID(roomID);
            }
            List<Document> attachedDocumentList = new ArrayList<Document>();
            for (Document documentListDocumentToAttach : catergoryDocument.getDocumentList()) {
                documentListDocumentToAttach = em.getReference(documentListDocumentToAttach.getClass(), documentListDocumentToAttach.getId());
                attachedDocumentList.add(documentListDocumentToAttach);
            }
            catergoryDocument.setDocumentList(attachedDocumentList);
            em.persist(catergoryDocument);
            if (roomID != null) {
                roomID.getCatergoryDocumentList().add(catergoryDocument);
                roomID = em.merge(roomID);
            }
            for (Document documentListDocument : catergoryDocument.getDocumentList()) {
                CatergoryDocument oldCatergoryIDOfDocumentListDocument = documentListDocument.getCatergoryID();
                documentListDocument.setCatergoryID(catergoryDocument);
                documentListDocument = em.merge(documentListDocument);
                if (oldCatergoryIDOfDocumentListDocument != null) {
                    oldCatergoryIDOfDocumentListDocument.getDocumentList().remove(documentListDocument);
                    oldCatergoryIDOfDocumentListDocument = em.merge(oldCatergoryIDOfDocumentListDocument);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatergoryDocument(catergoryDocument.getId()) != null) {
                throw new PreexistingEntityException("CatergoryDocument " + catergoryDocument + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CatergoryDocument catergoryDocument) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatergoryDocument persistentCatergoryDocument = em.find(CatergoryDocument.class, catergoryDocument.getId());
            Room roomIDOld = persistentCatergoryDocument.getRoomID();
            Room roomIDNew = catergoryDocument.getRoomID();
            List<Document> documentListOld = persistentCatergoryDocument.getDocumentList();
            List<Document> documentListNew = catergoryDocument.getDocumentList();
            List<String> illegalOrphanMessages = null;
            for (Document documentListOldDocument : documentListOld) {
                if (!documentListNew.contains(documentListOldDocument)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Document " + documentListOldDocument + " since its catergoryID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (roomIDNew != null) {
                roomIDNew = em.getReference(roomIDNew.getClass(), roomIDNew.getId());
                catergoryDocument.setRoomID(roomIDNew);
            }
            List<Document> attachedDocumentListNew = new ArrayList<Document>();
            for (Document documentListNewDocumentToAttach : documentListNew) {
                documentListNewDocumentToAttach = em.getReference(documentListNewDocumentToAttach.getClass(), documentListNewDocumentToAttach.getId());
                attachedDocumentListNew.add(documentListNewDocumentToAttach);
            }
            documentListNew = attachedDocumentListNew;
            catergoryDocument.setDocumentList(documentListNew);
            catergoryDocument = em.merge(catergoryDocument);
            if (roomIDOld != null && !roomIDOld.equals(roomIDNew)) {
                roomIDOld.getCatergoryDocumentList().remove(catergoryDocument);
                roomIDOld = em.merge(roomIDOld);
            }
            if (roomIDNew != null && !roomIDNew.equals(roomIDOld)) {
                roomIDNew.getCatergoryDocumentList().add(catergoryDocument);
                roomIDNew = em.merge(roomIDNew);
            }
            for (Document documentListNewDocument : documentListNew) {
                if (!documentListOld.contains(documentListNewDocument)) {
                    CatergoryDocument oldCatergoryIDOfDocumentListNewDocument = documentListNewDocument.getCatergoryID();
                    documentListNewDocument.setCatergoryID(catergoryDocument);
                    documentListNewDocument = em.merge(documentListNewDocument);
                    if (oldCatergoryIDOfDocumentListNewDocument != null && !oldCatergoryIDOfDocumentListNewDocument.equals(catergoryDocument)) {
                        oldCatergoryIDOfDocumentListNewDocument.getDocumentList().remove(documentListNewDocument);
                        oldCatergoryIDOfDocumentListNewDocument = em.merge(oldCatergoryIDOfDocumentListNewDocument);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catergoryDocument.getId();
                if (findCatergoryDocument(id) == null) {
                    throw new NonexistentEntityException("The catergoryDocument with id " + id + " no longer exists.");
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
            CatergoryDocument catergoryDocument;
            try {
                catergoryDocument = em.getReference(CatergoryDocument.class, id);
                catergoryDocument.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catergoryDocument with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Document> documentListOrphanCheck = catergoryDocument.getDocumentList();
            for (Document documentListOrphanCheckDocument : documentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatergoryDocument (" + catergoryDocument + ") cannot be destroyed since the Document " + documentListOrphanCheckDocument + " in its documentList field has a non-nullable catergoryID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Room roomID = catergoryDocument.getRoomID();
            if (roomID != null) {
                roomID.getCatergoryDocumentList().remove(catergoryDocument);
                roomID = em.merge(roomID);
            }
            em.remove(catergoryDocument);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatergoryDocument> findCatergoryDocumentEntities() {
        return findCatergoryDocumentEntities(true, -1, -1);
    }

    public List<CatergoryDocument> findCatergoryDocumentEntities(int maxResults, int firstResult) {
        return findCatergoryDocumentEntities(false, maxResults, firstResult);
    }

    private List<CatergoryDocument> findCatergoryDocumentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatergoryDocument.class));
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

    public CatergoryDocument findCatergoryDocument(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatergoryDocument.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatergoryDocumentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatergoryDocument> rt = cq.from(CatergoryDocument.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
