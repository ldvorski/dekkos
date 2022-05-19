package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "playlist", layout = PartyLayout.class)
public class PlaylistView extends VerticalLayout {

    PlaylistView() {
        add(new H1("Playlist"));
    }
}
