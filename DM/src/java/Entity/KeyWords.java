/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aking
 */
@Entity
@Table(name = "KeyWords")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KeyWords.findAll", query = "SELECT k FROM KeyWords k"),
    @NamedQuery(name = "KeyWords.findById", query = "SELECT k FROM KeyWords k WHERE k.id = :id"),
    @NamedQuery(name = "KeyWords.findByKeyWord", query = "SELECT k FROM KeyWords k WHERE k.keyWord = :keyWord")})
public class KeyWords implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "keyWord")
    private String keyWord;
    @JoinColumn(name = "documentID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Document documentID;

    public KeyWords() {
    }

    public KeyWords(Integer id) {
        this.id = id;
    }

    public KeyWords(Integer id, String keyWord) {
        this.id = id;
        this.keyWord = keyWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Document getDocumentID() {
        return documentID;
    }

    public void setDocumentID(Document documentID) {
        this.documentID = documentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeyWords)) {
            return false;
        }
        KeyWords other = (KeyWords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.KeyWords[ id=" + id + " ]";
    }
    
}
