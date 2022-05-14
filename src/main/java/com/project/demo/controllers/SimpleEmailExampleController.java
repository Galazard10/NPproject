package com.project.demo.controllers;

import com.project.demo.entities.QuestionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class SimpleEmailExampleController {

    @Autowired
    public JavaMailSender emailSender;

    @PostMapping(value="/send-question-email")
    public ResponseEntity<String> sendSimpleEmail(@RequestBody QuestionMessage questionMessage) {

        // Create a Simple MailMessage.
        SimpleMailMessage newMessage = new SimpleMailMessage();

        newMessage.setFrom(questionMessage.getEmail());
        newMessage.setTo("qashqyn291@gmail.com");
        newMessage.setSubject(questionMessage.getFullname() + " - " + questionMessage.getQuestionType());
        newMessage.setText(questionMessage.getMessage() + "\n\n" + questionMessage.getFullname() + "\n" + questionMessage.getEmail() + "\n" + questionMessage.getPhoneNumber());

        // Send Message!
        emailSender.send(newMessage);
        return new ResponseEntity("Email Sent!", HttpStatus.OK);
    }
}
