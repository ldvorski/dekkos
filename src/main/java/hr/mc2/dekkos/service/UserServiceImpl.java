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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User addToParty(User user, Party party){
        user.setUserParty(party);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUser(Integer userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new NotFoundException(
                    "ERR_USER_NOT_FOUND"
            );
        }
        return userOptional.get();
    }
}
