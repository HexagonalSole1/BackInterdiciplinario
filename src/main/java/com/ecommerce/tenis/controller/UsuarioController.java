package com.ecommerce.tenis.controller;


import com.ecommerce.tenis.controller.dtos.request.CreateCalzadoRequest;
import com.ecommerce.tenis.controller.dtos.request.CreateUsuarioRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.repository.UsuarioRepository;
import com.ecommerce.tenis.service.CalzadoServiceImpl;
import com.ecommerce.tenis.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearUsuario(@RequestBody CreateUsuarioRequest request){
        BaseResponse baseResponse = usuarioService.createUsuario(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> deleteUsuarioById(@PathVariable Long id){
        BaseResponse baseResponse = usuarioService.deletedById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> getUsuarios(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        BaseResponse baseResponse = usuarioService.getUsuario(page, size);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getUsuarioById(@PathVariable Long id){
        BaseResponse baseResponse = usuarioService.getUsuariosById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PatchMapping(path="/{id}")
    public ResponseEntity<BaseResponse> updateUsuario(@PathVariable Long id, @RequestBody CreateUsuarioRequest request){
        BaseResponse baseResponse = usuarioService.updateUsuario(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
