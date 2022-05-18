package hr.mc2.dekkos.service;

import com.vaadin.flow.router.NotFoundException;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PartyService {

    String getObfuscatedPartyId(Party party);

    List<String> getAllObfuscatedPartyIds();

    Party getParty(Integer partyId) throws NotFoundException;

    User makeAdminParty(User user);

    User addUserToParty(User user, Integer id);

    Iterable<Party> findAll();

    Party getPartyByAdmin(User admin);
}
