package hr.mc2.dekkos.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
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
import hr.mc2.dekkos.service.UserService;
import hr.mc2.dekkos.views.party.PlaylistView;


@Route("/")
public class JoinPartyView extends VerticalLayout {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final PartyService partyService;

    public JoinPartyView(AuthenticationService authenticationService, UserService userService, PartyService partyService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.partyService = partyService;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        add(createForm());

        var joinRoomLink = new Anchor("/create", "Create a party instead");

        add(joinRoomLink);
    }

    private void onJoin(String username, String partyCode) {
        var party = partyService.getPartyByCode(partyCode);
        var user = User.createMember(username, party);

        userService.add(user);

        authenticationService.addUserToSession(user);

        UI.getCurrent().navigate(
            PlaylistView.class,
            new RouteParameters(new RouteParam("partyCode", partyService.obfuscatePartyId(party)))
        );
    }

    private FlexLayout createForm() {
        var title = new H1("Join a party");
        title.getStyle().set("margin", "0");

        var username = new TextField("Username");
        var code = new TextField("Party code");

        var action = new Button("Join", e -> onJoin(username.getValue(), code.getValue()));

        var form = new FormLayout(
            title,
            username,
            code,
            action
        );

        form.setMaxWidth("500px");
        form.setResponsiveSteps(
            new ResponsiveStep("0", 1),
            new ResponsiveStep("500px", 1)
        );

        var flex = new FlexLayout(form);
        flex.setJustifyContentMode(JustifyContentMode.CENTER);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER);
        setWidthFull();


        return flex;
    }
}
