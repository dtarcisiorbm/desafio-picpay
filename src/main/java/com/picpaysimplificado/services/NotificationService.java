package com.picpaysimplificado.services;

import com.picpaysimplificado.core.domain.user.User;
import com.picpaysimplificado.dto.NotificationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email=user.getEmail();
        NotificationRequestDTO notificationRequest=new NotificationRequestDTO(email,message);
        ResponseEntity<String> notificationResponse =restTemplate.postForEntity("https://util.devi.tools/api/v1/notify",notificationRequest,String.class);
        if(!(notificationResponse.getStatusCode() == HttpStatusCode.OK)){
            throw new RuntimeException("Notification failed");
            System.out.println("Notification failed");
        }
    }
}
