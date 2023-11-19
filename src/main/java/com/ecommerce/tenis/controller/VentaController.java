package com.ecommerce.tenis.controller;

import com.ecommerce.tenis.controller.dtos.request.CreateApartadoRequest;
import com.ecommerce.tenis.controller.dtos.request.CreateVentaRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.service.ApartadoServiceImpl;
import com.ecommerce.tenis.service.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "v1/Ventas")
public class VentaController {

    @Autowired
    private VentaServiceImpl ventaService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearApartado(@RequestBody CreateVentaRequest request){
        BaseResponse baseResponse = ventaService.createVenta(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getApartado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        BaseResponse baseResponse = ventaService.getVentas(page, size);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }


    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getApartadoById(@PathVariable Long id){
        BaseResponse baseResponse = ventaService.getVentaById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> deleteCalzadoById(@PathVariable Long id){
        BaseResponse baseResponse = ventaService.deleteVentaById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }


}
