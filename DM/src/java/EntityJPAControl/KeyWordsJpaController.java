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
import Entity.Document;
import Entity.KeyWords;
import EntityJPAControl.exceptions.NonexistentEntityException;
import EntityJPAControl.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aking
 */
public class KeyWordsJpaController implements Serializable {

    public KeyWordsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KeyWords keyWords) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Document documentID = keyWords.getDocumentID();
            if (documentID != null) {
                documentID = em.getReference(documentID.getClass(), documentID.getId());
                keyWords.setDocumentID(documentID);
            }
            em.persist(keyWords);
            if (documentID != null) {
                documentID.getKeyWordsList().add(keyWords);
                documentID = em.merge(documentID);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKeyWords(keyWords.getId()) != null) {
                throw new PreexistingEntityException("KeyWords " + keyWords + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KeyWords keyWords) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KeyWords persistentKeyWords = em.find(KeyWords.class, keyWords.getId());
            Document documentIDOld = persistentKeyWords.getDocumentID();
            Document documentIDNew = keyWords.getDocumentID();
            if (documentIDNew != null) {
                documentIDNew = em.getReference(documentIDNew.getClass(), documentIDNew.getId());
                keyWords.setDocumentID(documentIDNew);
            }
            keyWords = em.merge(keyWords);
            if (documentIDOld != null && !documentIDOld.equals(documentIDNew)) {
                documentIDOld.getKeyWordsList().remove(keyWords);
                documentIDOld = em.merge(documentIDOld);
            }
            if (documentIDNew != null && !documentIDNew.equals(documentIDOld)) {
                documentIDNew.getKeyWordsList().add(keyWords);
                documentIDNew = em.merge(documentIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = keyWords.getId();
                if (findKeyWords(id) == null) {
                    throw new NonexistentEntityException("The keyWords with id " + id + " no longer exists.");
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
            KeyWords keyWords;
            try {
                keyWords = em.getReference(KeyWords.class, id);
                keyWords.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The keyWords with id " + id + " no longer exists.", enfe);
            }
            Document documentID = keyWords.getDocumentID();
            if (documentID != null) {
                documentID.getKeyWordsList().remove(keyWords);
                documentID = em.merge(documentID);
            }
            em.remove(keyWords);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KeyWords> findKeyWordsEntities() {
        return findKeyWordsEntities(true, -1, -1);
    }

    public List<KeyWords> findKeyWordsEntities(int maxResults, int firstResult) {
        return findKeyWordsEntities(false, maxResults, firstResult);
    }

    private List<KeyWords> findKeyWordsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KeyWords.class));
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

    public KeyWords findKeyWords(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KeyWords.class, id);
        } finally {
            em.close();
        }
    }

    public int getKeyWordsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KeyWords> rt = cq.from(KeyWords.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
