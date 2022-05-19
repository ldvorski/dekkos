package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.SuggestionRepository;
import hr.mc2.dekkos.model.Song;
import hr.mc2.dekkos.model.Suggestion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SuggestionService {
    private AuthenticationService authenticationService;
    private SuggestionRepository suggestionRepository;

    public Suggestion addSuggestion(Song song) {
        var user = authenticationService.getUserFromSession();
        var suggestion = Suggestion.create(user, song);

        return suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getSuggestionsForActiveParty() {
        var party = authenticationService.getPartyFromSession();

        return suggestionRepository.getSuggestionsByParty(party);
    }
}
