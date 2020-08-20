package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.config.JwtUtil;
import com.rahpouyan.rahayi.demo.config.MyUserDetailService;
import com.rahpouyan.rahayi.demo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/authenticate")
    @ResponseBody
    public Object auth(@ModelAttribute User user) {

        HashMap<String, String> hashMap = new HashMap<>();

        try {


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            hashMap.put("status", "correct");
            hashMap.put("jwt", jwt);

            return hashMap;

        } catch (BadCredentialsException e) {
            hashMap.put("status", "incorrect");
            hashMap.put("message", "password is wrong");
            return hashMap;

        } catch (Exception e) {

            hashMap.put("status", "incorrect");
            hashMap.put("message", "there is no one any user with this username");
            return hashMap;
        }

    }


}
