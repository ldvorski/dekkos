package hr.mc2.dekkos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @Column(name = "url")
    String url;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "title")
    String title;

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return "https://www.youtube.com/embed/" + url;
    }
}
