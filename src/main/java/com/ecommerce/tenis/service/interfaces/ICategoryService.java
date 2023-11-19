package com.ecommerce.tenis.service.interfaces;

import com.ecommerce.tenis.controller.dtos.request.CreateCategoryRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import jakarta.transaction.Transactional;

public interface ICategoryService {
    @Transactional
        // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse create(CreateCategoryRequest request);
}
