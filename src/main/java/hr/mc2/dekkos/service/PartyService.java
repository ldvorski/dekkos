package hr.mc2.dekkos.service;

import org.springframework.stereotype.Service;

@Service
public interface PartyService {
    boolean existsPartyByPartyCode(String partyCode);
}
