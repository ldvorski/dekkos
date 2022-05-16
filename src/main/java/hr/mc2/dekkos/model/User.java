package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToOne(mappedBy = "partyAdmin")
    private Party adminToParty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_party")
    private Party userParty;

    public User(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    protected User() {}

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s', isAdmin='%s']",
                id,name,isAdmin);
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    public Party getUserParty() {
        return userParty;
    }

    public void setUserParty(Party userParty) {
        this.userParty = userParty;
    }

    public Party getAdminToParty() {
        return adminToParty;
    }
}

