package hr.mc2.dekkos.service;

import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User add(User user);

    void addToParty(User user, Party party);

    List<User> getUsers();

    void setUserParty(User user);
}

