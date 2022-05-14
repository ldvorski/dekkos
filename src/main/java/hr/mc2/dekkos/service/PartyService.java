package hr.mc2.dekkos.service;

import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PartyService {
    void makeAdminParty(User user);

    void addUserToParty(User user, Integer idParty);
}
