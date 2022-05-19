package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "suggestion")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_played")
    private boolean isPlayed;

    @Column(name = "votes")
    private int votes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party", referencedColumnName = "id")
    private Party party;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song", referencedColumnName = "id")
    private Song song;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;
}
