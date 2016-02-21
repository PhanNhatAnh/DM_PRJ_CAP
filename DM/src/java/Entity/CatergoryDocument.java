/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aking
 */
@Entity
@Table(name = "CatergoryDocument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatergoryDocument.findAll", query = "SELECT c FROM CatergoryDocument c"),
    @NamedQuery(name = "CatergoryDocument.findById", query = "SELECT c FROM CatergoryDocument c WHERE c.id = :id"),
    @NamedQuery(name = "CatergoryDocument.findByCatergoryName", query = "SELECT c FROM CatergoryDocument c WHERE c.catergoryName = :catergoryName"),
    @NamedQuery(name = "CatergoryDocument.findByDescription", query = "SELECT c FROM CatergoryDocument c WHERE c.description = :description")})
public class CatergoryDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "catergoryName")
    private String catergoryName;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catergoryID")
    private List<Document> documentList;
    @JoinColumn(name = "roomID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room roomID;

    public CatergoryDocument() {
    }

    public CatergoryDocument(Integer id) {
        this.id = id;
    }

    public CatergoryDocument(Integer id, String catergoryName) {
        this.id = id;
        this.catergoryName = catergoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatergoryName() {
        return catergoryName;
    }

    public void setCatergoryName(String catergoryName) {
        this.catergoryName = catergoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public Room getRoomID() {
        return roomID;
    }

    public void setRoomID(Room roomID) {
        this.roomID = roomID;
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
        if (!(object instanceof CatergoryDocument)) {
            return false;
        }
        CatergoryDocument other = (CatergoryDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CatergoryDocument[ id=" + id + " ]";
    }
    
}
