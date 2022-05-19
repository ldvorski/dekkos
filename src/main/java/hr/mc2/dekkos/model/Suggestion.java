package hr.mc2.dekkos.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "suggestion")
public class Suggestion {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_played")
    private boolean isPlayed = false;

    @Column(name = "votes")
    private String votingUsers = "[]";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party", referencedColumnName = "id")
    private Party party;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song", referencedColumnName = "id")
    private Song song;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    Suggestion() {}

    Suggestion(User user, Song song, Party party) {
        this.createdBy = user;
        this.song = song;
        this.party = party;

        addVote(user);
    }

    public static Suggestion create(User user, Song song) {
        return new Suggestion(user, song, user.getParty());
    }

    public Song getSong() {
        return this.song;
    }

    public User getCreator() {
        return this.createdBy;
    }

    public Party belongsToParty() {
        return this.party;
    }

    public String getSuggestionSongTitle() {
        return this.song.getTitle();
    }

    public String getSuggestionSongUrl() {
        return this.song.getUrl();
    }

    public String getSuggestionSongThumbnail() {
        return this.song.thumbnail;
    }

    public Integer getVotes() {
        return readUserVotes().size();
    }

    public void addVote(User user) {
        var votes = readUserVotes();
        votes.add(user);
        this.votingUsers = writeUserVotes(votes);
    }

    private List<User> readUserVotes() {
        try {
            return MAPPER.readValue(votingUsers, new TypeReference<List<User>>() {});
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }

    private String writeUserVotes(List<User> votes) {
        try {
            return MAPPER.writeValueAsString(votingUsers);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }

}
