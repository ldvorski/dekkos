package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByName(String username);
}

