package com.ecommerce.tenis.service.interfaces;

import com.ecommerce.tenis.controller.dtos.request.CreateApartadoRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import jakarta.transaction.Transactional;

public interface IApartadoService {

    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse createUsuario(CreateApartadoRequest request);

    @Transactional
    BaseResponse getApartado(int page, int size);

    @Transactional
    BaseResponse getApartadoById(Long apartadoId);


    @Transactional
    BaseResponse deleteApartadoById(Long apartadoId);
}
