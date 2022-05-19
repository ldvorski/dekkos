package hr.mc2.dekkos.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import hr.mc2.dekkos.components.PotentialSongComponent;
import hr.mc2.dekkos.external.YouTubeClient;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.Song;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.ObfuscatorService;
import hr.mc2.dekkos.service.PartyService;
import hr.mc2.dekkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Route
public class MainView extends VerticalLayout {
//    YouTubeClient youtubeClient;
//    Party uiParty;
//    User uiUser;
//    PartyService partyService;
//    UserService userService;
//    TextField userName = new TextField("Enter your name");
//    IntegerField partyId = new IntegerField("Enter desired party");
//    TextField searchBox = new TextField("Enter desired song");
//
//    Button admin = new Button("Create Party", e -> setSessionUser(adminButton(userName.getValue())));
//    Button member = new Button("Join Party", e -> setSessionUser(userButton(userName.getValue(), partyId.getValue())));
//    Button searchBtn = new Button("Search YouTube", e -> this.sessionInfo.add(PotentialSongComponent.create(queryYouTube(searchBox.getValue()))));
//    Button iframe = new Button("iframe", e -> {
//        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("curSong")!= null){
//            this.sessionInfo.add(new IFrame(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("curSong").toString()));
//        }
//    });
//    VerticalLayout sessionInfo = new VerticalLayout();
//    Button button = new Button("update session info", e -> getSessionUser());
//    Button showid = new Button("show obfuscated ids", e -> this.sessionInfo.add(new Label((ObfuscatorService.obfuscate(this.uiParty.getId())))));
//
//    HorizontalLayout buttons = new HorizontalLayout(admin, member,searchBtn, button,showid,iframe);
//
//    @Autowired
//    public MainView(UserService userService, PartyService partyService, YouTubeClient youTubeClient){
//        this.userService = userService;
//        this.partyService = partyService;
//        this.youtubeClient = youTubeClient;
//        add(userName, partyId, searchBox ,buttons);
//        add(sessionInfo);
//
//    }
//
//    private void setSessionUser(User user){
//        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("curUser",user.getId());
//        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("curParty",user.getUserParty().getId());
//        MainView.this.add(new H1(String.valueOf(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("curParty"))));
//        MainView.this.add(new H1(String.valueOf(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("curUser"))));
//    }
//    private void getSessionUser(){
//        Integer holder = (Integer) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("curParty");
//        if ( holder != null){
//            this.uiParty = partyService.getParty(holder);
//        }
//    }
//    private User adminButton(String name){
//        User user = new User(name, true);
//
//        return partyService.createParty(user);
//    }
//    private User userButton(String name, Integer id){
//        User user = new User(name, false);
//        return partyService.addUserToParty(user, id);
//    }
//
//    private List<Song> queryYouTube(String query) {
//        return youtubeClient.search(query);
//    }
}
