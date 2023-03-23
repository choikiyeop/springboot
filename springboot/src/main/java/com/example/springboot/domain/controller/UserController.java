package com.example.springboot.domain.controller;

import com.example.springboot.domain.dto.request.EmailRequest;
import com.example.springboot.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> sendMail(@RequestBody EmailRequest request) {
        userService.sendMail(request.getEmail());

        System.out.println("메일을 보냈습니다");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/signup/{key}")
    public ResponseEntity<Boolean> checkKey(@PathVariable("key") String key) {

        return ResponseEntity.ok(userService.checkKey());
    }

    @PostMapping("/signup/{key}")
    public ResponseEntity signUp(@PathVariable("key") String key, @RequestBody EmailRequest request) {
        userService.signUp();

        return ResponseEntity.ok().build();
    }
}
