package com.ecommerce.tenis.controller;


import com.ecommerce.tenis.controller.dtos.request.CreateCalzadoRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.service.CalzadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/Calzados")
public class CalzadoController {

    @Autowired
    private CalzadoServiceImpl calzadoService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearCalzado(@RequestBody CreateCalzadoRequest request){
        BaseResponse baseResponse = calzadoService.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> deleteCalzadoById(@PathVariable Long id){
        BaseResponse baseResponse = calzadoService.deletedById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @GetMapping
    public ResponseEntity<BaseResponse> getCalzado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        BaseResponse baseResponse = calzadoService.getCalzado(page, size);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getCalzadoById(@PathVariable Long id){
        BaseResponse baseResponse = calzadoService.getCalzadoById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PatchMapping(path="/{id}")
    public ResponseEntity<BaseResponse> updateCalzado(@PathVariable Long id, @RequestBody CreateCalzadoRequest request){
        BaseResponse baseResponse = calzadoService.updateCalzado(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
