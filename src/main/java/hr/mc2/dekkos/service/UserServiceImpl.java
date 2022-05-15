package hr.mc2.dekkos.service;


import hr.mc2.dekkos.model.Party;
import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void addToParty(User user, Party party){
        user.setUserParty(party);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
