package hr.mc2.dekkos.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import hr.mc2.dekkos.model.Suggestion;

import java.util.List;

public class SuggestionGrid extends Grid<SuggestionItem> {
    private List<Suggestion> suggestionList;
    private Grid<Suggestion> suggestionGrid;

    public SuggestionGrid(List<Suggestion> items) {
        super(SuggestionItem.class, false);
        setAllRowsVisible(true);

        var gridItems = items.stream().map(this::mapSuggestionToGridItem).toList();

        setItems(gridItems);
        addColumn(gridItems::indexOf);
        addColumn(SuggestionItem::title).setHeader("Song");
        addColumn(SuggestionItem::votes).setHeader("Votes");
        addColumn(SuggestionItem::suggestedBy).setHeader("Suggested by");
        addColumn(new ComponentRenderer<>(Button::new, (button, suggestion) -> {
        button.addThemeVariants(ButtonVariant.LUMO_ICON);
    }));
    }

    private SuggestionItem mapSuggestionToGridItem(Suggestion suggestion) {
        return new SuggestionItem(
            suggestion.getSong().getTitle(),
            suggestion.getVotes(),
            suggestion.getCreator().getName()
        );
    }

    private String titleFactory(Suggestion suggestion) {
        return suggestion.getSong().getTitle();
    }

    private String suggestedByFactory(Suggestion suggestion) {
        return suggestion.getCreator().getName();
    }
}

record SuggestionItem(
   String title,
   Integer votes,
   String suggestedBy
) {}
