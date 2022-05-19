package hr.mc2.dekkos.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.AuthenticationService;
import hr.mc2.dekkos.service.PartyService;
import hr.mc2.dekkos.views.party.PlaylistView;


@Route("create")
public class CreatePartyView extends VerticalLayout {

    private final AuthenticationService authenticationService;
    private final PartyService partyService;

    public CreatePartyView(AuthenticationService authenticationService, PartyService partyService) {
        this.authenticationService = authenticationService;
        this.partyService = partyService;

        add(createForm());

        var joinRoomLink = new Anchor("/", "Join an existing party");

        add(joinRoomLink);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }

    private void onCreate(String username) {
        var user = User.createAdmin(username);
        var party = partyService.createParty(user);

        authenticationService.addUserToSession(user);

        UI.getCurrent().navigate(
            PlaylistView.class,
            new RouteParameters(new RouteParam("partyCode", partyService.obfuscatePartyId(party)))
        );
    }

    private FlexLayout createForm() {
        var title = new H1("Create a party");
        title.getStyle().set("margin", "0");

        var username = new TextField("Username");

        var action = new Button("Create", e -> onCreate(username.getValue()));

        var form = new FormLayout(
            title,
            username,
            action
        );

        form.setMaxWidth("500px");
        form.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("500px", 1)
        );

        var flex = new FlexLayout(form);
        flex.setJustifyContentMode(JustifyContentMode.CENTER);

        return flex;
    }

}
