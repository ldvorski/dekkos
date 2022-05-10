package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartyServiceImpl implements PartyService {
    @Autowired
    private PartyRepository partyRepository;

    @Override
    public boolean existsPartyByPartyCode(String partyCode){
        return partyRepository.existsPartyByPartyCode(partyCode);
    }
}
