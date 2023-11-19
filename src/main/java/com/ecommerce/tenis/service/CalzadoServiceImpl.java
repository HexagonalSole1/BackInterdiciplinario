package com.ecommerce.tenis.service;

import com.ecommerce.tenis.controller.dtos.request.CreateCalzadoRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.entity.Calzado;
import com.ecommerce.tenis.repository.CalzadoRepository;
import com.ecommerce.tenis.service.interfaces.ICalzadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalzadoServiceImpl  implements ICalzadoService {

    @Autowired
    private CalzadoRepository calzadoRepository;

    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse deletedById(Long id) {


            calzadoRepository.deleteCalzadoById(id,new Date());

        return BaseResponse.builder()
                .data(null)
                .message("Calzado a sido Eliminado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getCalzado() {
        return null;
    }

    @Override
    @Transactional
    public BaseResponse getCalzado(int page, int size) {
        int offset = (page - 1) * size;
        System.out.println(offset + "ssssssssss  "+ size);
        List<Calzado> response = calzadoRepository.getCalzado(offset, size);
        int totalCalzados = calzadoRepository.getTotalCalzados();

        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontraron los siguientes Calzados:")
                    .total(totalCalzados)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalCalzados / size))
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(response)
                    .message("No se encontraron Calzados")
                    .total(totalCalzados)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalCalzados / size))
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse getCalzadoById(Long id) {



        List<Calzado> response =calzadoRepository.getCalzado(id);

        if (!response.isEmpty()){
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontro el calzado:")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        }
        return BaseResponse.builder()
                .data(response)
                .message("No se encontraron Calzados")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }





    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse create(CreateCalzadoRequest request) {
        calzadoRepository.createCalzado(
                request.getPrecio(),
                request.getTalla(),
                request.getModelo(),
                request.getGenero(),
                request.getId_categoria(),
                request.getColor(),
                request.getDescripcion(),
                request.getTipo(),
                request.getInventario(),
                false,
                new Date()

        );

        return BaseResponse.builder()
                .data(null)
                .message("Calzado Creado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
    @Override
    @Transactional
    public BaseResponse updateCalzado(Long id, CreateCalzadoRequest request) {
        Optional<Calzado> optionalCalzado = calzadoRepository.findById(id);

        if (optionalCalzado.isPresent()) {
            Calzado calzadoActual = optionalCalzado.get();
            Date updatedAt = new Date();

            Calzado calzadoActualizado = combinarCalzadoConRequest(calzadoActual, request);
            System.out.println(calzadoActualizado.getDescripcion());

            calzadoRepository.updateCalzado(
                    id,
                    calzadoActualizado.getPrecio(),
                    calzadoActualizado.getTalla(),
                    calzadoActualizado.getModelo(),
                    calzadoActualizado.getGenero(),
                    calzadoActualizado.getColor(),
                    calzadoActualizado.getDescripcion(),
                    calzadoActualizado.getTipo(),
                    calzadoActualizado.getInventario(),
                    updatedAt
            );

            return BaseResponse.builder()
                    .data(null)
                    .message("Calzado Modificado")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(null)
                    .message("Calzado no encontrado")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    private Calzado combinarCalzadoConRequest(Calzado calzado, CreateCalzadoRequest request) {
        Calzado calzadoActualizado = new Calzado();
        BeanUtils.copyProperties(calzado, calzadoActualizado, getNullPropertyNames(calzado));
        BeanUtils.copyProperties(request, calzadoActualizado, getNullPropertyNames(request));
        return calzadoActualizado;
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
