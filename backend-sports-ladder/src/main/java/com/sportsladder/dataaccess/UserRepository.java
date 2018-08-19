package com.sportsladder.dataaccess;

import com.sportsladder.domain.Player;
import com.sportsladder.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByPlayer(Player player);
    User findById(Long id);
    User findByEmail(String email);

}

