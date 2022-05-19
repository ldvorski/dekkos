package hr.mc2.dekkos.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import hr.mc2.dekkos.model.Song;
import hr.mc2.dekkos.model.Suggestion;
import hr.mc2.dekkos.model.User;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SuggestionGrid extends Grid<SuggestionItem> {
    public SuggestionGrid(List<Suggestion> items, User activeUser, Consumer<Song> onPlay) {
        super(SuggestionItem.class, false);
        setAllRowsVisible(true);

        var gridItems = items.stream()
            .filter(suggestion -> !suggestion.getSong().getIsPlayed())
            .map(this::mapSuggestionToGridItem).toList();

        setItems(gridItems);
        addColumn(gridItems::indexOf).setHeader("#").setWidth("50px").setFlexGrow(0);
        addColumn(SuggestionItem::title).setHeader("Song");
        addColumn(SuggestionItem::votes).setHeader("Votes").setWidth("100px").setFlexGrow(0);
        addColumn(SuggestionItem::suggestedBy).setHeader("Suggested by").setWidth("150px").setFlexGrow(0);
        ;
        addColumn(new ComponentRenderer<>(Button::new, (button, suggestion) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON);
            button.setIcon(VaadinIcon.ARROW_UP.create());
        })).setWidth("75px").setFlexGrow(0);
        addColumn(new ComponentRenderer<Button, SuggestionItem>(Button::new, (button, suggestion) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ERROR);
            button.setIcon(VaadinIcon.BAN.create());
            if (!suggestion.suggestedBy().equals(activeUser.getName())) {
                button.getStyle().set("display", "none");
            }
        })).setWidth("75px").setFlexGrow(0);
        addColumn(new ComponentRenderer<Button, SuggestionItem>(Button::new, (button, suggestion) -> {
            button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            button.setIcon(VaadinIcon.PLAY_CIRCLE.create());
            if (!activeUser.isAdmin()) {
                button.getStyle().set("display", "none");
            }
            button.addClickListener(e -> onPlay.accept(suggestion.song()));
        })).setWidth("75px").setFlexGrow(0);
    }

    private SuggestionItem mapSuggestionToGridItem(Suggestion suggestion) {
        return new SuggestionItem(
            suggestion.getSong().getTitle(),
            suggestion.getVotes(),
            suggestion.getCreator().getName(),
            suggestion.getSong()
        );
    }
}

record SuggestionItem(
    String title,
    Integer votes,
    String suggestedBy,
    Song song
) {
}
