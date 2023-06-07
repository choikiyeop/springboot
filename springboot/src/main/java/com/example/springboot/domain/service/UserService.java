package com.example.springboot.domain.service;

import com.example.springboot.global.util.MailUtil;
//import com.example.springboot.global.util.RedisUtil;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MailUtil mailUtil;
    //private final RedisUtil redisUtil;

    @Async
    public void sendMail(String email) {
        String key = UUID.randomUUID().toString();
        if (mailUtil.sendSignUpMail(email, key)) {
            //redisUtil.set(key, email, 10);
        }
    }

    public boolean checkKey() {
        return true;
    }

    public void signUp() {

    }
}
