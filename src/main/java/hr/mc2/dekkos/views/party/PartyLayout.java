package hr.mc2.dekkos.views.party;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;

@RoutePrefix("party/:partyCode")
public class PartyLayout extends AppLayout implements BeforeEnterObserver {
    private final Tabs tabs = new Tabs();

    PartyLayout() {
        addToNavbar(tabs);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
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
