package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "suggest", layout = PartyLayout.class)
public class SuggestView extends VerticalLayout {

    SuggestView() {
        add(new H1("Suggest"));
    }
}
