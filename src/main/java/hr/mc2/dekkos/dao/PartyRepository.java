package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.Party;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartyRepository extends CrudRepository<Party, Integer> {
    Optional<Party> findPartyByIdParty(Integer idParty);
}
