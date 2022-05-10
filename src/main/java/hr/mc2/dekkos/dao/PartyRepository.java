package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.Party;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, String> {

    boolean existsPartyByPartyCode(String partyCode);
}
