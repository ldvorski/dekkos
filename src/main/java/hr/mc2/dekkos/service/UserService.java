package hr.mc2.dekkos.service;

import hr.mc2.dekkos.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User add(User user);

    List<User> getUsers();
}

