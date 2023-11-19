package com.ecommerce.tenis.service;

import com.ecommerce.tenis.controller.dtos.request.CreateUsuarioRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.entity.Usuario;
import com.ecommerce.tenis.repository.UsuarioRepository;
import com.ecommerce.tenis.service.interfaces.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse getUsuariosById (Long id){


        List<Usuario> response = usuarioRepository.getUsuarioById(id);

        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontro el Usuario:")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        }
        return BaseResponse.builder()
                .data(response)
                .message("No se encontro al Usuario")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse deletedById(Long id) {

        usuarioRepository.deleteUsuarioById(id, new Date());

        return BaseResponse.builder()
                .data(null)
                .message("Usuario a sido Eliminado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse createUsuario(CreateUsuarioRequest request) {

        usuarioRepository.createUsuario(request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                encodePassword(request.getPassword()),
                request.getRol(),
                new Date());


        if (usuarioRepository.existeUsuario(request.getEmail())==0){
            return BaseResponse.builder()
                    .data(null)
                    .message("Usuario Creado")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.CREATED)
                    .build();

        }
        else {
            return BaseResponse.builder()
                    .data(null)
                    .message("Ya hay un usuario con ese correo")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();

        }

    }


    private static String encodePassword(String request){
        return new BCryptPasswordEncoder().encode(request);
    }

    @Override
    @Transactional
    public BaseResponse getUsuario(int page, int size) {
        int offset = (page-1) * size;


        System.out.println(page+"sddsds        "+size);

        List<Usuario> response = usuarioRepository.getUsuarios(offset, size);
        int totalUsuarios = usuarioRepository.getTotalUsuarios();  // Asegúrate de tener este método en tu repositorio

        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontraron los siguientes Usuarios:")
                    .total(totalUsuarios)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalUsuarios / size))
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(response)
                    .message("No se encontraron Usuarios")
                    .total(totalUsuarios)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalUsuarios / size))
                    .success(Boolean.FALSE)  // Corregir a Boolean.FALSE
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }



    @Override
    @Transactional
    public BaseResponse updateUsuario(Long id, CreateUsuarioRequest request) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuarioActual = optionalUsuario.get();
            Date updatedAt = new Date();

            Usuario usuarioActualizado = combinarUsuarioConRequest(usuarioActual, request);

            usuarioRepository.updateUsuario(
                    id,
                    usuarioActualizado.getNombre(),
                    usuarioActualizado.getApellido(),
                    usuarioActualizado.getEmail(),
                    encodePassword(  usuarioActualizado.getPassword()),
                    usuarioActualizado.getRol(),
                    updatedAt
            );

            return BaseResponse.builder()
                    .data(null)
                    .message("Usuario Modificado")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(null)
                    .message("Usuario no encontrado")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    private Usuario combinarUsuarioConRequest(Usuario usuario, CreateUsuarioRequest request) {
        Usuario usuarioActualizado = new Usuario();
        BeanUtils.copyProperties(usuario, usuarioActualizado, getNullPropertyNames(usuario));
        BeanUtils.copyProperties(request, usuarioActualizado, getNullPropertyNames(request));
        return usuarioActualizado;
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



}





