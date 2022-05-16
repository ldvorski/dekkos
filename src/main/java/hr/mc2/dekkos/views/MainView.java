package hr.mc2.dekkos.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.PartyService;
import hr.mc2.dekkos.service.UserService;
import hr.mc2.dekkos.external.YouTubeAPI;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Route
public class MainView extends VerticalLayout {
    PartyService partyService;
    UserService userService;
    TextField userName = new TextField("Enter your name");
    IntegerField partyId = new IntegerField("Enter desired party");
    TextField searchBox = new TextField("Enter desired song");

    Button admin = new Button("Create Party", e -> adminButton(userName.getValue()));
    Button member = new Button("Join Party", e -> userButton(userName.getValue(),partyId.getValue()));
    Button searchBtn = new Button("Search YouTube", e ->{
        try {
            Notification.show(YouTubeAPI.search(searchBox.getValue()).toString());
        } catch (GeneralSecurityException | IOException ex) {
            throw new RuntimeException(ex);
        }

    });
    HorizontalLayout buttons = new HorizontalLayout(admin, member,searchBtn);
    public MainView(UserService userService, PartyService partyService){
        add(userName, partyId, searchBox ,buttons);
        this.userService = userService;
        this.partyService = partyService;
    }

    private void adminButton(String name){
        partyService.makeAdminParty(new User(name, true));
    }
    private void userButton(String name, Integer partyId){
        partyService.addUserToParty(new User(name, false), partyId);
    }
}
