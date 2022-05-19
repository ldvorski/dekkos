package hr.mc2.dekkos.service;

import com.vaadin.flow.server.VaadinService;
import hr.mc2.dekkos.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationService {
    public void addUserToSession(User user) {
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("userId", user.getId());
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("partyId", user.getUserParty().getId());
    }
}
