package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> votingUsers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party", referencedColumnName = "id")
    private Party party;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song", referencedColumnName = "id")
    private Song song;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    public Song getSong(){
        return this.song;
    }
    public User getCreator(){
        return this.createdBy;
    }
    public Party belongsToParty(){
        return this.party;
    }
    public String getSuggestionSongTitle(){
        return this.song.getTitle();
    }
    public String getSuggestionSongUrl(){
        return this.song.getUrl();
    }
    public String getSuggestionSongThumbnail(){
        return this.song.thumbnail;
    }
    public Integer getVotes(){
        return this.votingUsers.size();
    }
    public void userVoted(User user){
        this.votingUsers.add(user);
    }
}
