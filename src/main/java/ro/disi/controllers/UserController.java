package ro.disi.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@Configuration
public class UserController {
    @RequestMapping(value = "/login")
    public Principal login(Principal principal) {
        return principal;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')") //in order to authorize one role
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DONOR')") //in order to authorize two or more roles
    @RequestMapping(value = "/test")
    public String test() {
        return "DA";
    }

}
