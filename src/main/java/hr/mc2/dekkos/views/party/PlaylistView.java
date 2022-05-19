package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.dao.SuggestionRepository;
import hr.mc2.dekkos.service.SuggestionService;
import hr.mc2.dekkos.views.components.SuggestionGrid;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "playlist", layout = PartyLayout.class)
public class PlaylistView extends VerticalLayout {
    private final SuggestionService suggestionService;

    public PlaylistView(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;

        removeAll();
        add(new SuggestionGrid(suggestionService.getSuggestionsForActiveParty()));
    }
}
