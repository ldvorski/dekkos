package hr.mc2.dekkos.views.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.AuthenticationService;
import hr.mc2.dekkos.service.PartyService;
import hr.mc2.dekkos.service.UserService;


@Route
public class CreatePartyView extends VerticalLayout {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final PartyService partyService;

    public CreatePartyView(AuthenticationService authenticationService, UserService userService, PartyService partyService){
        this.authenticationService = authenticationService;
        this.partyService = partyService;
        this.userService = userService;

        add(createForm());

    }

    private void onCreate(String username){
        var user = User.createAdmin(username);
        partyService.createParty(user);

        authenticationService.addUserToSession(user);
    }

    private FormLayout createForm() {
        var title = new H1("Create a party");

        var username = new TextField("Username");;

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
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER);
        setWidthFull();


        return form;
    }

}
