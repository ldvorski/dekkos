package hr.mc2.dekkos.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.PartyService;
import hr.mc2.dekkos.service.UserService;

@Route
public class MainView extends VerticalLayout {
    PartyService partyService;
    UserService userService;
    TextField userName = new TextField("Enter your name");
    IntegerField partyId = new IntegerField("Enter desired party");

    Button admin = new Button("Create Party", e -> partyService.makeAdminParty(new User(userName.getValue(),true)));
    Button member = new Button("Join Party", e -> partyService.addUserToParty(new User(userName.getValue(), false),partyId.getValue()));
    HorizontalLayout buttons = new HorizontalLayout(admin, member);
    public MainView(UserService userService, PartyService partyService){
        add(userName, partyId ,buttons);
        this.userService = userService;
        this.partyService = partyService;
    }

}
