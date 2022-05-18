package hr.mc2.dekkos.service;

import com.vaadin.flow.router.NotFoundException;
import hr.mc2.dekkos.dao.PartyRepository;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    public String getObfuscatedPartyId(Party party){
        return ObfuscatorImpl.obfuscate(party.getIdParty());
    }

    @Override
    public List<String> getAllObfuscatedPartyIds(){
        List<String> ids = new ArrayList<>();
        for(Party party : partyRepository.findAll()){
            ids.add(ObfuscatorImpl.obfuscate(party.getIdParty()));
        }
        return ids;
    }

    @Override
    public Party getParty(Integer partyId) throws NotFoundException {

        Optional<Party> partyOptional = partyRepository.findPartyByIdParty(partyId);
        if(partyOptional.isEmpty()){
            throw new NotFoundException(
              String.format("ERR_PARTY_NOT_FOUND" + partyId)
            );
        }
        return partyOptional.get();
    }

    @Override
    public User makeAdminParty(User user){
        Party adminParty = new Party(user);
        partyRepository.save(adminParty);
        return userServiceImpl.addToParty(user, adminParty);

    }

    @Override
    public User addUserToParty(User user, Integer id){
        Party party = getParty(id);
        return userServiceImpl.addToParty(user, party);
    }

    @Override
    public Iterable<Party> findAll(){
        return partyRepository.findAll();
    }

    @Override
    public Party getPartyByAdmin(User admin){
        Optional<Party> partyOptional = partyRepository.findByPartyAdmin(admin);
        if(partyOptional.isEmpty()){
            throw new NotFoundException(
                    "ERR_PARTY_NOT_FOUND"
            );
        }
        return partyOptional.get();
    }
}
