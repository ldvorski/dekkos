package hr.mc2.dekkos.service;

import com.vaadin.flow.server.VaadinService;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationService {
    private UserService userService;
    private PartyService partyService;

    public void addUserToSession(User user) {
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("userId", user.getId());
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("partyId", user.getParty().getId());
    }

    public User getUserFromSession() {
        var userId = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("userId");

        return userService.getUser(Integer.parseInt(userId.toString()));
    }

    public Party getPartyFromSession() {
        var partyId = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("partyId");

        return partyService.getPartyById(Integer.parseInt(partyId.toString()));
    }
}
