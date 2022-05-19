package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.Suggestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SuggestionRepository extends CrudRepository<Suggestion, Integer> {
        List<Suggestion> getSuggestionsByParty(Party party);
        Suggestion findSuggestionByParty(Party party);
}
