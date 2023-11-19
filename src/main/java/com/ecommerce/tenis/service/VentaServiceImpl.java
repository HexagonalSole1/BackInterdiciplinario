package com.ecommerce.tenis.service;

import com.ecommerce.tenis.controller.dtos.request.CreateVentaRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import com.ecommerce.tenis.entity.Venta;
import com.ecommerce.tenis.repository.VentaRepository;
import com.ecommerce.tenis.service.interfaces.IVentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    VentaRepository ventaRepository;

    @Override
    @Transactional
    public BaseResponse createVenta(CreateVentaRequest request) {
        ventaRepository.createVenta(
                request.getProductoId(),request.getCantidad(),request.getPrecioUnitario()
                ,request.getSubTotal(),request.getDescuento(),request.getTotal()
                ,request.getClienteId(),request.getVigencia(),new Date(),false
        );

        return BaseResponse.builder()
                .data(null)
                .message("Venta Creada")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse getVentas(int page, int size) {
        int offset = (page - 1) * size;

        List<Venta> response = ventaRepository.getVentas(offset, size);
        int totalVentas = ventaRepository.getTotalVentas();

        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontraron las siguientes Ventas:")
                    .total(totalVentas)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalVentas / size))
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(response)
                    .message("No se encontraron Ventas")
                    .total(totalVentas)
                    .currentPage(page)
                    .totalPages((int) Math.ceil((double) totalVentas / size))
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    @Transactional
    public BaseResponse getVentaById(Long ventaId) {
        Optional<Venta> ventaOptional = ventaRepository.getVentaById(ventaId);

        if (ventaOptional.isPresent()) {
            return BaseResponse.builder()
                    .data(ventaOptional.get())
                    .message("Venta encontrada con ID: " + ventaId)
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(null)
                    .message("No se encontró ninguna Venta con ID: " + ventaId)
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    @Transactional
    public BaseResponse deleteVentaById(Long ventaId) {
        int deletedRows = ventaRepository.deleteVentaById(ventaId, new Date());

        if (deletedRows > 0) {
            return BaseResponse.builder()
                    .message("Venta con ID " + ventaId + " eliminada exitosamente.")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.builder()
                    .message("No se encontró ninguna Venta con ID: " + ventaId)
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
