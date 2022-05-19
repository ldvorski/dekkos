package hr.mc2.dekkos.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.Song;
import hr.mc2.dekkos.model.Suggestion;
import hr.mc2.dekkos.service.PartyService;
import hr.mc2.dekkos.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("grid-dynamic-height")
public class GridMaker extends Div {
    private final PartyService partyService;
    private final SuggestionService suggestionService;
    private List<Suggestion> suggestionList;
    private Grid<Suggestion> suggestionGrid;

    @Autowired
    public GridMaker(PartyService partyService, SuggestionService suggestionService) {
        this.partyService = partyService;
        this.suggestionService = suggestionService;
    }

    private  void getPartySuggestions(Integer partyId){
        this.suggestionList = suggestionService.getPartySuggestions(partyId);

    }

    private void setupGrid(){
        Grid<Suggestion> temp = new Grid<>(Suggestion.class, false);
        temp.setAllRowsVisible(true);
        temp.addColumn(Suggestion::getSuggestionSongThumbnail);
        temp.addColumn(Suggestion::getSuggestionSongTitle);
        temp.addColumn(new ComponentRenderer<>(Button::new, (button, suggestion )->{
            button.addThemeVariants(ButtonVariant.LUMO_ICON);
            button.addClickListener(e -> this.)
        }))

    }

    private void upvote(Suggestion suggestion){
        if
    }

}
