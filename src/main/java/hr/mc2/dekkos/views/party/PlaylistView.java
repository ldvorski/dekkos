package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import hr.mc2.dekkos.model.Song;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.*;
import hr.mc2.dekkos.views.components.SuggestionGrid;

@Route(value = "playlist", layout = PartyLayout.class)
public class PlaylistView extends VerticalLayout {
    private final SuggestionService suggestionService;
    private final SongService songService;
    private final AuthenticationService authenticationService;
    private final PartyService partyService;
    private Registration registration = null;
    private final Div currentlyPlaying = new Div();
    private final Div grid = new Div();

    public PlaylistView(
        SuggestionService suggestionService,
        AuthenticationService authenticationService,
        SongService songService,
        PartyService partyService
    ) {
        this.suggestionService = suggestionService;
        this.authenticationService = authenticationService;
        this.songService = songService;
        this.partyService = partyService;

        setWidthFull();
        removeAll();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(
            new RoomCode(partyService.obfuscatePartyId(authenticationService.getPartyFromSession())),
            currentlyPlaying,
            new H3("Active suggestions"),
            grid
        );
        drawGrid();
    }

    private void onPlay(Song song) {
        songService.playSong(song);
    }

    private void drawGrid() {
        grid.removeAll();
        grid.setMaxWidth("900px");
        grid.setWidthFull();

        var suggestionsGrid = new SuggestionGrid(suggestionService.getSuggestionsForActiveParty(), authenticationService.getUserFromSession(), this::onPlay);
        suggestionsGrid.setWidthFull();

        grid.add(suggestionsGrid);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        var currentSong = songService.getCurrentSong();
        if (currentSong.isPresent()) {
            currentlyPlaying.removeAll();
            currentlyPlaying.add(new CurrentlyPlaying(authenticationService.getUserFromSession(), currentSong.get()));
        }

        var ui = attachEvent.getUI();
        registration = SongBroadcaster.register(nextSong -> {
            ui.access(() -> {
                currentlyPlaying.removeAll();
                currentlyPlaying.add(new CurrentlyPlaying(authenticationService.getUserFromSession(), nextSong));
                drawGrid();
            });
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        registration.remove();
        registration = null;
    }
}

class RoomCode extends VerticalLayout {
    RoomCode(String roomCode) {
        var title = new H3("Party room code");
        var roomCodeField = new H3(roomCode);

        setMaxWidth("900px");
        roomCodeField.setWidthFull();

        title.getStyle().set("margin", "0");
        roomCodeField.getStyle().set("margin", "0");

        add(
            title,
            roomCodeField
        );
    }
}

class CurrentlyPlaying extends HorizontalLayout {
    CurrentlyPlaying(User activeUser, Song song) {
        if (activeUser.isAdmin()) {
            add(new Video(song));
        } else {
            add(
                new Thumbnail(song),
                new Details(song)
            );
        }
    }

    class Details extends VerticalLayout {
        Details(Song song) {
            getStyle().set("padding", "0px");

            var title = new H3(song.getTitle());
            title.getStyle().set("margin", "0px");

            add(
                title,
                new Text("Suggested by: " + song.getSuggestion().getCreator().getName())
            );
        }
    }


    class Thumbnail extends Div {
        Thumbnail(Song song) {
            setWidth("33%");
            setMaxHeight("135px");

            var img = new Image(song.getThumbnail(), "Currently playing thumbnail");
            img.setWidthFull();
            img.setHeight("135px");

            add(img);
        }
    }

    class Video extends IFrame {
        Video(Song song) {
            super(song.getUrl());
        }
    }
}

