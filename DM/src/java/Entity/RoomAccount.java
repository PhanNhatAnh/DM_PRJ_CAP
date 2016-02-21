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
@Table(name = "RoomAccount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomAccount.findAll", query = "SELECT r FROM RoomAccount r"),
    @NamedQuery(name = "RoomAccount.findById", query = "SELECT r FROM RoomAccount r WHERE r.id = :id")})
public class RoomAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "roomID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room roomID;
    @JoinColumn(name = "accountID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account accountID;

    public RoomAccount() {
    }

    public RoomAccount(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoomID() {
        return roomID;
    }

    public void setRoomID(Room roomID) {
        this.roomID = roomID;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
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
        if (!(object instanceof RoomAccount)) {
            return false;
        }
        RoomAccount other = (RoomAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RoomAccount[ id=" + id + " ]";
    }
    
}
