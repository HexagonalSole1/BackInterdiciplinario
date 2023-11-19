package com.ecommerce.tenis.service.interfaces;

import com.ecommerce.tenis.controller.dtos.request.CreateCalzadoRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.entity.Calzado;
import com.ecommerce.tenis.service.CalzadoServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public interface ICalzadoService {



    @Transactional
    BaseResponse deletedById(Long id);

    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse getCalzado();

    @Transactional
    BaseResponse getCalzado(int page, int size);

    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse getCalzadoById(Long id);

    @Transactional
    BaseResponse create(CreateCalzadoRequest request);



    @Transactional
    BaseResponse updateCalzado(Long id, CreateCalzadoRequest request);
}
