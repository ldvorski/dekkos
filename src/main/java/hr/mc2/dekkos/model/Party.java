package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_party")
    private Integer idParty;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_admin", referencedColumnName = "id")
    private User partyAdmin;

    @OneToMany(mappedBy = "userParty",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<User> users;

    public Party(User user){
        this.isActive = true;
        this.partyAdmin = user;
    }

    public Party() {

    }

    public Integer getIdParty() {
        return this.idParty;
    }
    public boolean partyIsActive(){
        return this.isActive;
    }

    public User getPartyAdmin() {
        return this.partyAdmin;
    }

    public Set<User> getUsers() {
        return this.users;
    }

}
