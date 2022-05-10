package hr.mc2.dekkos.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_id", referencedColumnName = "id_party")
    private Party party;

    public User(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
        if (isAdmin == true){
            this.party = new Party(this);
        }

    }

    protected User() {}

    public String getThisUser() {
        String admin = "isn't";
        if (this.isAdmin) {
            admin = "is";
        }

        return "The users name is " + this.name + " and he " + admin + " the party admin";
    }
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s', isAdmin='%s']",
                id,name,isAdmin);
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public boolean isAdmin(){
        return isAdmin;
    }
}

