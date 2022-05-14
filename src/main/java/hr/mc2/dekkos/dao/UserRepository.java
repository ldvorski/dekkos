package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}

