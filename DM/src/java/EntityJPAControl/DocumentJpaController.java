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
import Entity.Document;
import Entity.KeyWords;
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
public class DocumentJpaController implements Serializable {

    public DocumentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Document document) throws PreexistingEntityException, Exception {
        if (document.getKeyWordsList() == null) {
            document.setKeyWordsList(new ArrayList<KeyWords>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatergoryDocument catergoryID = document.getCatergoryID();
            if (catergoryID != null) {
                catergoryID = em.getReference(catergoryID.getClass(), catergoryID.getId());
                document.setCatergoryID(catergoryID);
            }
            List<KeyWords> attachedKeyWordsList = new ArrayList<KeyWords>();
            for (KeyWords keyWordsListKeyWordsToAttach : document.getKeyWordsList()) {
                keyWordsListKeyWordsToAttach = em.getReference(keyWordsListKeyWordsToAttach.getClass(), keyWordsListKeyWordsToAttach.getId());
                attachedKeyWordsList.add(keyWordsListKeyWordsToAttach);
            }
            document.setKeyWordsList(attachedKeyWordsList);
            em.persist(document);
            if (catergoryID != null) {
                catergoryID.getDocumentList().add(document);
                catergoryID = em.merge(catergoryID);
            }
            for (KeyWords keyWordsListKeyWords : document.getKeyWordsList()) {
                Document oldDocumentIDOfKeyWordsListKeyWords = keyWordsListKeyWords.getDocumentID();
                keyWordsListKeyWords.setDocumentID(document);
                keyWordsListKeyWords = em.merge(keyWordsListKeyWords);
                if (oldDocumentIDOfKeyWordsListKeyWords != null) {
                    oldDocumentIDOfKeyWordsListKeyWords.getKeyWordsList().remove(keyWordsListKeyWords);
                    oldDocumentIDOfKeyWordsListKeyWords = em.merge(oldDocumentIDOfKeyWordsListKeyWords);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocument(document.getId()) != null) {
                throw new PreexistingEntityException("Document " + document + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Document document) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Document persistentDocument = em.find(Document.class, document.getId());
            CatergoryDocument catergoryIDOld = persistentDocument.getCatergoryID();
            CatergoryDocument catergoryIDNew = document.getCatergoryID();
            List<KeyWords> keyWordsListOld = persistentDocument.getKeyWordsList();
            List<KeyWords> keyWordsListNew = document.getKeyWordsList();
            List<String> illegalOrphanMessages = null;
            for (KeyWords keyWordsListOldKeyWords : keyWordsListOld) {
                if (!keyWordsListNew.contains(keyWordsListOldKeyWords)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain KeyWords " + keyWordsListOldKeyWords + " since its documentID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (catergoryIDNew != null) {
                catergoryIDNew = em.getReference(catergoryIDNew.getClass(), catergoryIDNew.getId());
                document.setCatergoryID(catergoryIDNew);
            }
            List<KeyWords> attachedKeyWordsListNew = new ArrayList<KeyWords>();
            for (KeyWords keyWordsListNewKeyWordsToAttach : keyWordsListNew) {
                keyWordsListNewKeyWordsToAttach = em.getReference(keyWordsListNewKeyWordsToAttach.getClass(), keyWordsListNewKeyWordsToAttach.getId());
                attachedKeyWordsListNew.add(keyWordsListNewKeyWordsToAttach);
            }
            keyWordsListNew = attachedKeyWordsListNew;
            document.setKeyWordsList(keyWordsListNew);
            document = em.merge(document);
            if (catergoryIDOld != null && !catergoryIDOld.equals(catergoryIDNew)) {
                catergoryIDOld.getDocumentList().remove(document);
                catergoryIDOld = em.merge(catergoryIDOld);
            }
            if (catergoryIDNew != null && !catergoryIDNew.equals(catergoryIDOld)) {
                catergoryIDNew.getDocumentList().add(document);
                catergoryIDNew = em.merge(catergoryIDNew);
            }
            for (KeyWords keyWordsListNewKeyWords : keyWordsListNew) {
                if (!keyWordsListOld.contains(keyWordsListNewKeyWords)) {
                    Document oldDocumentIDOfKeyWordsListNewKeyWords = keyWordsListNewKeyWords.getDocumentID();
                    keyWordsListNewKeyWords.setDocumentID(document);
                    keyWordsListNewKeyWords = em.merge(keyWordsListNewKeyWords);
                    if (oldDocumentIDOfKeyWordsListNewKeyWords != null && !oldDocumentIDOfKeyWordsListNewKeyWords.equals(document)) {
                        oldDocumentIDOfKeyWordsListNewKeyWords.getKeyWordsList().remove(keyWordsListNewKeyWords);
                        oldDocumentIDOfKeyWordsListNewKeyWords = em.merge(oldDocumentIDOfKeyWordsListNewKeyWords);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = document.getId();
                if (findDocument(id) == null) {
                    throw new NonexistentEntityException("The document with id " + id + " no longer exists.");
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
            Document document;
            try {
                document = em.getReference(Document.class, id);
                document.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The document with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<KeyWords> keyWordsListOrphanCheck = document.getKeyWordsList();
            for (KeyWords keyWordsListOrphanCheckKeyWords : keyWordsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Document (" + document + ") cannot be destroyed since the KeyWords " + keyWordsListOrphanCheckKeyWords + " in its keyWordsList field has a non-nullable documentID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CatergoryDocument catergoryID = document.getCatergoryID();
            if (catergoryID != null) {
                catergoryID.getDocumentList().remove(document);
                catergoryID = em.merge(catergoryID);
            }
            em.remove(document);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Document> findDocumentEntities() {
        return findDocumentEntities(true, -1, -1);
    }

    public List<Document> findDocumentEntities(int maxResults, int firstResult) {
        return findDocumentEntities(false, maxResults, firstResult);
    }

    private List<Document> findDocumentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Document.class));
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

    public Document findDocument(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Document.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Document> rt = cq.from(Document.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
