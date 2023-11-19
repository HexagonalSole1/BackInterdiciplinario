package com.ecommerce.tenis.controller;


import com.ecommerce.tenis.controller.dtos.request.AuthenticationRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private IAuthService service;

    @PostMapping("token")
    public ResponseEntity<BaseResponse> authentication (@RequestBody AuthenticationRequest request){
        return service.authenticate(request).apply();
    }

}
