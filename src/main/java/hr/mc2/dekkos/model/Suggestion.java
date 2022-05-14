package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "sgs")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idsgs")
    private Long idsgs;

    @Column(name = "is_played")
    private boolean isPlayed;

    @Column(name = "votes")
    private int votes;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "party", referencedColumnName = "id_party")
//    private Party party;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song", referencedColumnName = "url")
    private Song song;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;
}
