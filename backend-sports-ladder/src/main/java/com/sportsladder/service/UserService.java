package com.sportsladder.service;

import com.sportsladder.dataaccess.UserRepository;
import com.sportsladder.domain.User;
import com.sportsladder.domain.UserDto;
import com.sportsladder.domain.UserRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerNewUserAccount(UserDto accountDto)
//            throws EmailExistsException
            throws Exception
    {

        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
            throw new Exception(
                    "There is an account with that email address:"  + accountDto.getEmail());
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRole(UserRole.Admin);
        return userRepository.save(user);
    }
    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
