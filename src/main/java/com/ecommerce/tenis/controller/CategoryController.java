package com.ecommerce.tenis.controller;


import com.ecommerce.tenis.controller.dtos.request.CreateCalzadoRequest;
import com.ecommerce.tenis.controller.dtos.request.CreateCategoryRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.service.CalzadoServiceImpl;
import com.ecommerce.tenis.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/Category")
public class CategoryController {


    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearCalzado(@RequestBody CreateCategoryRequest request){
        BaseResponse baseResponse = categoryService.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
}
