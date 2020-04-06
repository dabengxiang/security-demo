package com.onion.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gyc
 * @date 2020/3/9
 */
@RestController
@RequestMapping
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserController {


    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('user:read')")
    public String getUser(){
        return "getUser";
    }



    @GetMapping("/notAccess")
    public String notAccess(){
        return "没有权限访问";
    }



}
