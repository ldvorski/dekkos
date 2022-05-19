package hr.mc2.dekkos.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "videoId")
    String videoId;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "is_played")
    Boolean isPlayed = false;

    @Column(name = "is_currently_playing")
    Boolean isCurrentlyPlaying;

    @Column(name = "title")
    String title;

    @OneToOne(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Suggestion suggestion;

    public Song(String videoId, String url, String title) {
        this.videoId = videoId;
        this.thumbnail = url;
        this.title = title;
    }

    protected Song() {
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getIsPlayed() {
        return isPlayed;
    }

    public void setIsPlayed(Boolean flag) {
        isPlayed = flag;
    }

    public Boolean getIsCurrentlyPlaying() {
        return isCurrentlyPlaying;
    }

    public void setIsCurrentlyPlaying(Boolean flag) {
        isCurrentlyPlaying = flag;
    }

    public String getUrl() {
        return "https://www.youtube.com/embed/" + videoId;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }
}
