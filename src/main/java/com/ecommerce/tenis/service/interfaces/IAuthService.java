package com.ecommerce.tenis.service.interfaces;

import com.ecommerce.tenis.controller.dtos.request.AuthenticationRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;

public interface IAuthService {

    BaseResponse authenticate(AuthenticationRequest request);
}
