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
public class PartyService{
    private final PartyRepository partyRepository;

    private final UserService userService;

    @Autowired
    public PartyService(PartyRepository partyRepository, UserService userService) {
        this.partyRepository = partyRepository;
        this.userService = userService;
    }

    public String getObfuscatedPartyId(Party party){
        return Obfuscator.obfuscate(party.getIdParty());
    }

    public List<String> getAllObfuscatedPartyIds(){
        List<String> ids = new ArrayList<>();
        for(Party party : partyRepository.findAll()){
            ids.add(Obfuscator.obfuscate(party.getIdParty()));
        }
        return ids;
    }

    public Party getParty(Integer partyId) throws NotFoundException {

        Optional<Party> partyOptional = partyRepository.findPartyByIdParty(partyId);
        if(partyOptional.isEmpty()){
            throw new NotFoundException(
              String.format("ERR_PARTY_NOT_FOUND" + partyId)
            );
        }
        return partyOptional.get();
    }

    public User makeAdminParty(User user){
        Party adminParty = new Party(user);
        partyRepository.save(adminParty);
        return userService.addToParty(user, adminParty);

    }

    public User addUserToParty(User user, Integer id){
        Party party = getParty(id);
        return userService.addToParty(user, party);
    }

    public Iterable<Party> findAll(){
        return partyRepository.findAll();
    }

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
