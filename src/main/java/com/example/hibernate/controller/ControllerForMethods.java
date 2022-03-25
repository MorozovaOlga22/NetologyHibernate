package com.example.hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class ControllerForMethods {
    @GetMapping("/methods/read")
    @Secured("ROLE_READ")
    public String read() {
        return "You read this!";
    }

    @GetMapping("/methods/write")
    @RolesAllowed("ROLE_WRITE")
    public String write() {
        return "You wrote something!";
    }

    @GetMapping("/methods/delete")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public String delete() {
        return "Deleted!";
    }

    @GetMapping("/methods/check")
    @PreAuthorize("#username == authentication.principal.username")
    public String checkUser(String username) {
        return "User checked!";
    }
}