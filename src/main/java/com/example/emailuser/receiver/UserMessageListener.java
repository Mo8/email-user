package com.example.emailuser.receiver;

import com.example.emailuser.email.EmailService;
import com.example.emailuser.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {

    EmailService emailService;

    public UserMessageListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "user-email")
    public void receiveUserMessage(User user) {
        System.out.println("Received user message: " + user);
        emailService.sendSimpleMessage(user.getEmail(), "Welcome to our app", "Hello " + user.getNom() + ",\n\nWelcome to our app!\n\n Please click on the following link to activate your account: http://localhost:8080/validate/" + user.getValidationCode() + "\n\nBest regards,\n\nThe team");
    }
}
