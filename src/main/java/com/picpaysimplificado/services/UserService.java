package com.picpaysimplificado.services;

import com.picpaysimplificado.core.domain.user.User;
import com.picpaysimplificado.core.domain.user.UserType;
import com.picpaysimplificado.dto.UserDTO;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void validateTransaction(User sender, BigDecimal amount){
        if(sender.getUserType()== UserType.MERCHANT){
            throw new RuntimeException("User for type Merchant do not authorized a transaction");
        }
        if(sender.getBalance().compareTo(amount)<0){
            throw new RuntimeException("User have not balance");

        }
    }
    public User findUserById(Long id){
        try {
            return this.userRepository.findUserById(id).orElseThrow(()->new Exception("User not find!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public User createUser(UserDTO user){
        User newUser=new User(user);
        this.saveUser(newUser);
        return newUser;
    }
    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public ResponseEntity<List<User>> getAllUsers() {
     List<User> users= this.userRepository.findAll();
     return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
