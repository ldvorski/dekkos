package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.SuggestionRepository;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.Suggestion;
import hr.mc2.dekkos.model.User;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.index.qual.SearchIndexBottom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SuggestionService {
    UserService userService;
    PartyService partyService;
    SuggestionRepository suggestionRepository;
    public List<Suggestion> getPartySuggestions(Integer partyId){
        return suggestionRepository.getSuggestionsByIdParty(partyId);
    }
    public void setUserVote(User user, Party party){
        Suggestion suggestion = suggestionRepository.findSuggestionByIdParty(party.getId());
        suggestion.
    }
}
