package hr.mc2.dekkos.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.UserService;

@Route
public class MainView extends VerticalLayout {

    UserService userService;
    TextField userName = new TextField("Enter your name");
    Button admin = new Button("Create Party", e -> userService.add(new User(userName.getValue(),true)));
    Button member = new Button("Join Party", e -> userService.add(new User(userName.getValue(), false)));
    HorizontalLayout buttons = new HorizontalLayout(admin, member);
    public MainView(UserService userService){
        add(userName, buttons);
        this.userService = userService;
    }

}
