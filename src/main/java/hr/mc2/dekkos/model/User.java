package hr.mc2.dekkos.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean isAdmin;

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

