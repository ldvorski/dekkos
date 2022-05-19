package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idParty;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_admin", referencedColumnName = "id")
    private User partyAdmin;

    @OneToMany(mappedBy = "userParty",fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<User> users;

    public Party(User user){
        this.isActive = true;
        this.partyAdmin = user;
    }

    public Party() {}

    public Integer getId() {
        return this.idParty;
    }
    public boolean getIsActive(){
        return this.isActive;
    }

    public User getAdmin() {
        return this.partyAdmin;
    }

    public Set<User> getUsers() {
        return this.users;
    }

}
