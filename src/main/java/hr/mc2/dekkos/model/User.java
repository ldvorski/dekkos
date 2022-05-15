package hr.mc2.dekkos.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Party adminToParty;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "party_id", nullable = false)
    private Party userParty;
    /*
        partyAdmin kreira party i postavlja se kao admin partija,
        partyUser trazi party preko partyIda te se stavlja na popis usera

     */

    public User(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
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
}

