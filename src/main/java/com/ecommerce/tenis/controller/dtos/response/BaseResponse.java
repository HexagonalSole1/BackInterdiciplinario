package com.ecommerce.tenis.controller.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter @Setter
@Builder
public class BaseResponse {

    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
    private int total;  // Nuevo campo para el total de elementos
    private int currentPage;  // Nuevo campo para la página actual
    private int totalPages;


    public ResponseEntity<BaseResponse> apply() {
        return new ResponseEntity<>(this, httpStatus);
    }
}
