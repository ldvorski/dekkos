package hr.mc2.dekkos.service;


import com.vaadin.flow.router.NotFoundException;
import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    public User addToParty(User user, Party party){
        user.setUserParty(party);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(Integer userId){
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }

}
