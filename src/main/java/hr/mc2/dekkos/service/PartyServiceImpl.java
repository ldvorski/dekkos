package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.PartyRepository;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartyServiceImpl implements PartyService {
    @Autowired
    private PartyRepository partyRepository;

    @Override
    public void makeAdminParty(User user){
        Party adminParty = new Party(user);
        adminParty.setUser(user);
        partyRepository.save(adminParty);
    }

    @Override
    public void addUserToParty(User user, Integer idParty){
        if(partyRepository.findById(idParty).isPresent()) {
            Party party =partyRepository.findById(idParty).get();
            party.setUser(user);
            partyRepository.save(party);
        }
    }
}
