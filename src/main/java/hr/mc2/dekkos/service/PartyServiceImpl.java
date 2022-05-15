package hr.mc2.dekkos.service;

import com.vaadin.flow.router.NotFoundException;
import hr.mc2.dekkos.dao.PartyRepository;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PartyServiceImpl implements PartyService {
    private final PartyRepository partyRepository;

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository, UserServiceImpl userServiceImpl) {
        this.partyRepository = partyRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Party getParty(Integer partyId) throws NotFoundException {
        Optional<Party> partyOptional = partyRepository.findPartyByIdParty(partyId);
        if(!partyOptional.isPresent()){
            throw new NotFoundException(
              String.format("ERR_PARTY_NOT_FOUND" + partyId)
            );
        }
        return partyOptional.get();
    }

    @Override
    public void makeAdminParty(User user){
        Party adminParty = new Party(user);
        partyRepository.save(adminParty);
        userServiceImpl.addToParty(user, adminParty);
    }

    @Override
    public void addUserToParty(User user, Integer idParty){
        Party party = getParty(idParty);
        userServiceImpl.addToParty(user, party);
    }
}
