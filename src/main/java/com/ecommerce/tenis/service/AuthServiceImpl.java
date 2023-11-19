package com.ecommerce.tenis.service;


import com.ecommerce.tenis.controller.dtos.request.AuthenticationRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.security.JWTUtils;
import com.ecommerce.tenis.service.interfaces.IAuthService;
import com.ecommerce.tenis.service.serviceConfig.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;

    @Value("${devmov.app.jwtSecret}")
    private String jwtSecret;

    @Override
    public BaseResponse authenticate(AuthenticationRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String email = userDetails.getUsername();

        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userDetails.getUser().getClienteId());
        String token = JWTUtils.generateToken(jwtSecret, email, payload);
        String id = String.valueOf(userDetails.getUser().getClienteId());



        return BaseResponse.builder()
                .data(token)
                .message(id)
                .httpStatus(HttpStatus.CREATED)
                .success(Boolean.TRUE)
                .build();
    }
}
