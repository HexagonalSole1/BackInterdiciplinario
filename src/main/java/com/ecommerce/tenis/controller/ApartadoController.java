package com.ecommerce.tenis.controller;


import com.ecommerce.tenis.controller.dtos.request.CreateApartadoRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.service.ApartadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/Apartados")
public class ApartadoController {

    @Autowired
    private ApartadoServiceImpl apartadoService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearApartado(@RequestBody CreateApartadoRequest request){
        BaseResponse baseResponse = apartadoService.createUsuario(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getApartado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        BaseResponse baseResponse = apartadoService.getApartado(page, size);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }


    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getApartadoById(@PathVariable Long id){
        BaseResponse baseResponse = apartadoService.getApartadoById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> deleteCalzadoById(@PathVariable Long id){
        BaseResponse baseResponse = apartadoService.deleteApartadoById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }



}
