package hr.mc2.dekkos.service;

import com.vaadin.flow.router.NotFoundException;
import hr.mc2.dekkos.dao.PartyRepository;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PartyService{
    private final PartyRepository partyRepository;
    private final UserService userService;
    private final ObfuscatorService obfuscatorService;


    public Party getPartyById(Integer partyId) throws NotFoundException {
        return partyRepository.findPartyByIdParty(partyId)
            .orElseThrow(NotFoundException::new);
    }

    public Party getPartyByCode(String code) throws NotFoundException {
        return partyRepository.findPartyByIdParty(deobfuscatePartyId(code))
            .orElseThrow(NotFoundException::new);
    }

    public Party createParty(User admin){
        Party party = new Party(admin);
        party = partyRepository.save(party);

        userService.addToParty(admin, party);

        return party;
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

    public String obfuscatePartyId(Party party){
        return obfuscatorService.obfuscate(party.getId());
    }
    public Integer deobfuscatePartyId(String partyCode){
        return obfuscatorService.deobfuscate(partyCode);
    }
}
