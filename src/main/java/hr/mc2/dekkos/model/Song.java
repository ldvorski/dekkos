package hr.mc2.dekkos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @Column(name = "id")
    String id;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "title")
    String title;

    public Song(String videoId, String url, String title) {
        this.id = videoId;
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

    public String getUrl() {
        return "https://www.youtube.com/embed/" + id;
    }
}
