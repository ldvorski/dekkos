package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;
import hr.mc2.dekkos.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

@RoutePrefix("party/:partyCode")
public class PartyLayout extends AppLayout implements BeforeEnterObserver {
    private final AuthenticationService authenticationService;
    private final Tabs tabs = new Tabs();

    PartyLayout(
        AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
        var flex = new FlexLayout(tabs);
        flex.setWidthFull();
        flex.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        addToNavbar(flex);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        var user = authenticationService.getUserFromSession();
        System.out.println("user = " + user.getName());
        addTabs(beforeEnterEvent.getRouteParameters().get("partyCode").orElseThrow(NotFoundException::new));
    }

    private void addTabs(String code) {
        if (tabs.getComponentCount() != 0) {
            return;
        }
        var params = new RouteParameters(new RouteParam("partyCode", code));

        var playlist = new Tab(new RouterLink("Playlist", PlaylistView.class, params));
        var suggest = new Tab(new RouterLink("Suggest", SuggestView.class, params));
        var leaderboard = new Tab(new RouterLink("Playlist", LeaderboardView.class, params));

        tabs.add(playlist, suggest, leaderboard);
    }
}
