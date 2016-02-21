/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aking
 */
@Entity
@Table(name = "Document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id"),
    @NamedQuery(name = "Document.findByDescription", query = "SELECT d FROM Document d WHERE d.description = :description"),
    @NamedQuery(name = "Document.findBySigner", query = "SELECT d FROM Document d WHERE d.signer = :signer"),
    @NamedQuery(name = "Document.findByFromDate", query = "SELECT d FROM Document d WHERE d.fromDate = :fromDate"),
    @NamedQuery(name = "Document.findByToDate", query = "SELECT d FROM Document d WHERE d.toDate = :toDate"),
    @NamedQuery(name = "Document.findByWriter", query = "SELECT d FROM Document d WHERE d.writer = :writer"),
    @NamedQuery(name = "Document.findBySeriDocument", query = "SELECT d FROM Document d WHERE d.seriDocument = :seriDocument"),
    @NamedQuery(name = "Document.findByLink", query = "SELECT d FROM Document d WHERE d.link = :link"),
    @NamedQuery(name = "Document.findByText", query = "SELECT d FROM Document d WHERE d.text = :text")})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "signer")
    private String signer;
    @Column(name = "fromDate")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "toDate")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Column(name = "writer")
    private String writer;
    @Column(name = "seriDocument")
    private String seriDocument;
    @Basic(optional = false)
    @Column(name = "link")
    private String link;
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "catergoryID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CatergoryDocument catergoryID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentID")
    private List<KeyWords> keyWordsList;

    public Document() {
    }

    public Document(Integer id) {
        this.id = id;
    }

    public Document(Integer id, String link) {
        this.id = id;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSeriDocument() {
        return seriDocument;
    }

    public void setSeriDocument(String seriDocument) {
        this.seriDocument = seriDocument;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CatergoryDocument getCatergoryID() {
        return catergoryID;
    }

    public void setCatergoryID(CatergoryDocument catergoryID) {
        this.catergoryID = catergoryID;
    }

    @XmlTransient
    public List<KeyWords> getKeyWordsList() {
        return keyWordsList;
    }

    public void setKeyWordsList(List<KeyWords> keyWordsList) {
        this.keyWordsList = keyWordsList;
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
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Document[ id=" + id + " ]";
    }
    
}
