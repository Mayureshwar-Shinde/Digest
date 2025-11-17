package net.masdigest.journalApp.controller;

import org.springframework.security.web.csrf.CsrfToken;   // <-- correct import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
    
}
