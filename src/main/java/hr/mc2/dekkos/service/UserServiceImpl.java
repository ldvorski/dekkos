package hr.mc2.dekkos.service;


import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User add(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
