package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.external.Video;
import hr.mc2.dekkos.external.YouTubeClient;
import hr.mc2.dekkos.model.Song;
import hr.mc2.dekkos.views.components.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Route(value = "suggest", layout = PartyLayout.class)
public class SuggestView extends VerticalLayout {
    private final YouTubeClient youTubeClient;
    private VerticalLayout results = new VerticalLayout();
    private Notification successNotification = new Notification("Successfully suggested a song to be played", 5, Notification.Position.MIDDLE);

    SuggestView(YouTubeClient youTubeClient) {
        this.youTubeClient = youTubeClient;

        successNotification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        results.setAlignItems(Alignment.CENTER);

        add(
            new SearchBox(this::onSearch),
            results
        );

        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

    private void onSearch(String query) {
        results.removeAll();

        youTubeClient.search(query).forEach(song -> {
            System.out.println("song = " + song.getTitle());
            results.add(new SearchResult(song, this::onSuggestClick));
        });
    }

    private void onSuggestClick(Song video) {
        successNotification.open();
    }
}

class SearchBox extends Div {
    private TextField searchBox = new TextField();

    SearchBox(Consumer<String> onSearch) {
        var searchContainer = new HorizontalLayout(searchBox,
            new Button(new Icon(VaadinIcon.SEARCH), e -> {
                System.out.println("searchBox = " + searchBox.getValue());
                onSearch.accept(searchBox.getValue());
            })
        );
        searchContainer.setWidthFull();
        searchBox.setWidthFull();

        add(
            new H3("Search for a song"),
            searchContainer
        );

        setMaxWidth("600px");
        setWidthFull();
    }
}
