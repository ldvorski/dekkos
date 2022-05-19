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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    private User(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    private User(String name, Party party) {
        this.name = name;
        this.party = party;
    }

    protected User() {}

    public static User createMember(String username, Party party) {
        return new User(username, party);
    }

    public static User createAdmin(String username) {
        return new User(username, true);
    }

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

    public Party getParty() {
        return this.party;
    }

    public void setParty(Party userParty) {
        System.out.println("userParty = " + userParty.getId() + " ");
        this.party = userParty;
    }

    public Party getAdminToParty() {
        return this.adminToParty;
    }

}

