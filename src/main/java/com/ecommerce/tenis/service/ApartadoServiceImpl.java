package com.ecommerce.tenis.service;

import com.ecommerce.tenis.controller.dtos.request.CreateApartadoRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.entity.Apartado;
import com.ecommerce.tenis.repository.ApartadoRepository;
import com.ecommerce.tenis.service.interfaces.IApartadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ApartadoServiceImpl implements IApartadoService {

    @Autowired
    ApartadoRepository apartadoRepository;

    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse createUsuario(CreateApartadoRequest request) {

        apartadoRepository.createApartado(request.getProductoId(), request.getCantidad(), request.getPrecioUnitario(), request.getSubTotal(),request.getDescuento(),request.getTotal(),
                                        request.getClienteId(),request.getVigencia(),new Date(),false);


        return BaseResponse.builder()
                .data(null)
                .message("Usuario Creado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }


    @Override
    @Transactional
    public BaseResponse getApartado(int page, int size) {
        int offset = (page - 1) * size;
        List<Apartado> response = apartadoRepository.getApartados(offset, size);
        int totalApartados = apartadoRepository.getTotalApartados();
        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontraron los siguientes Apartados:")
                    .total(totalApartados)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalApartados / size))
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(response)
                    .message("No se encontraron Apartados")
                    .total(totalApartados)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalApartados / size))
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
    @Override
    @Transactional
    public BaseResponse getApartadoById(Long apartadoId) {
        Optional<Apartado> apartadoOptional = apartadoRepository.getApartadoById(apartadoId);

        if (apartadoOptional.isPresent()) {
            return BaseResponse.builder()
                    .data(apartadoOptional.get())
                    .message("Apartado encontrado con ID: " + apartadoId)
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(null)
                    .message("No se encontró ningún Apartado con ID: " + apartadoId)
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }

    }
    @Override
    @Transactional
    public BaseResponse deleteApartadoById(Long apartadoId) {
        int deletedRows = apartadoRepository.deleteCalzadoById(apartadoId, new Date());

        if (deletedRows > 0) {
            return BaseResponse.builder()
                    .message("Apartado con ID " + apartadoId + " eliminado exitosamente.")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.builder()
                    .message("No se encontró ningún Apartado con ID: " + apartadoId)
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
