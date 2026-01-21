package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void validateTransaction(User sender, BigDecimal amount){
        if(sender.getUserType()== UserType.MERCHANT){
            throw new RuntimeException("User for type Merchant do not authorized a transaction");
        }
    }
}
