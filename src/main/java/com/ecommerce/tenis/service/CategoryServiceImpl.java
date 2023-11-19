package com.ecommerce.tenis.service;

import com.ecommerce.tenis.controller.dtos.request.CreateCategoryRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.entity.Calzado;
import com.ecommerce.tenis.entity.Categoria;
import com.ecommerce.tenis.repository.CategoryRepository;
import com.ecommerce.tenis.service.interfaces.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BaseResponse create(CreateCategoryRequest request) {


        Categoria categoria = new Categoria();
        categoria.setCategoria(request.getCategory());

        categoryRepository.save(categoria);

        return BaseResponse.builder()
                .data(null)
                .message("Categoria Creada")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
}