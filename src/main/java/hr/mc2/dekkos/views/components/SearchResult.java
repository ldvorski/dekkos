package hr.mc2.dekkos.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import hr.mc2.dekkos.external.Video;
import hr.mc2.dekkos.model.Song;

import java.util.function.Consumer;

public class SearchResult extends HorizontalLayout {
    public SearchResult(Song song, Consumer<Song> onClick) {
        var suggestButton = new FlexLayout(new Button("Suggest", e -> onClick.accept(song)));
        suggestButton.setJustifyContentMode(JustifyContentMode.END);
        suggestButton.setWidthFull();

        var content = new VerticalLayout(
            new SongTitle(song.getTitle()),
            suggestButton
        );

        content.getStyle().set("padding", "0px");
        content.setJustifyContentMode(JustifyContentMode.BETWEEN);

        add(
            new Thumbnail(song.getThumbnail()),
            content
        );

        setWidthFull();
        setMaxWidth("760px");
        getStyle()
            .set("background-color", "rgba(229, 229, 229, 0.35)")
            .set("padding", "10px");
    }
}

class SongTitle extends H5 {
    SongTitle(String title) {
        super(title);

        getStyle().set("margin", "0px");
    }
}

class Thumbnail extends Div {
    Thumbnail(String imageUrl) {
        setWidth("33%");
        setMaxHeight("130px");

        var img = new Image(imageUrl, "Song thumbnail");
        img.setWidthFull();
        img.setHeight("130px");

        add(img);
    }
}
