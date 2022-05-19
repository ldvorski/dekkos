package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.Suggestion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SuggestionRepository extends CrudRepository {
        List<Suggestion> getSuggestionsByIdParty(Integer idParty);
        Suggestion findSuggestionByIdParty(Integer idParty);
}
