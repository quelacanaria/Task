package com.example.bankapp.controller;

import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountApiController {

    @Autowired
    private AccountService accountService;

    /**
     * Returns JSON {"exists": true/false} indicating whether a username already exists.
     */
    @GetMapping("/username-exists")
    public Map<String, Boolean> usernameExists(@RequestParam String username) {
        boolean exists = true;
        try {
            accountService.findAccountByUsername(username);
            exists = true;
        } catch (RuntimeException e) {
            // findAccountByUsername throws RuntimeException when not found
            exists = false;
        }
        return Collections.singletonMap("exists", exists);
    }
}
