package com.ayeminwai.auth.server.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping("/user")
	Principal getUser(Principal user) {
		return user;
	}
	
}
