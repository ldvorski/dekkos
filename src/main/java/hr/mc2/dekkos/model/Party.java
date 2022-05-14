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

    @OneToOne
    @MapsId
    @JoinColumn(name = "partyAdmin")
    private User partyAdmin;

    @OneToMany(mappedBy = "userParty",fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<User> users;

    public Party(User user){
        this.isActive = true;
        this.partyAdmin = user;
    }

    public Party() {

    }

    public Integer getPartyCode() {
        return idParty;
    }
    public boolean partyIsActive(){
        return isActive;
    }

    public User getPartyAdmin() {
        return partyAdmin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUser(User user) {
        this.users.add(user);
    }
}
