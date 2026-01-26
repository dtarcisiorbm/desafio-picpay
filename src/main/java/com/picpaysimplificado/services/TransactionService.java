package com.picpaysimplificado.services;

import com.picpaysimplificado.core.domain.transaction.Transaction;
import com.picpaysimplificado.core.domain.user.User;
import com.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception{
    User sender=this.userService.findUserById(transaction.senderId());
    User receiver=this.userService.findUserById(transaction.receiverId());

    userService.validateTransaction(sender,transaction.value());
    if(!this.authorizeTrasaction(sender,transaction.value())){
        throw new Exception("Transaction not authorze");

    }
        Transaction newtransaction=new Transaction();
        newtransaction.setAmount(newtransaction.getAmount());
        newtransaction.setSender(sender);
        newtransaction.setReceiver(receiver);
        newtransaction.setTimestamp(LocalDateTime.now());
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(newtransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }
public boolean authorizeTrasaction(User sender, BigDecimal value){
ResponseEntity<Map > authorizationResponse=restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class );

if(authorizationResponse.getStatusCode()== HttpStatus.OK ){
    String message= (String )authorizationResponse.getBody().get("message");
    return "Autorizado".equalsIgnoreCase(message);
}else return false;

}
}
