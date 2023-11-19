package com.ecommerce.tenis.service.interfaces;

import com.ecommerce.tenis.controller.dtos.request.CreateVentaRequest;
import com.ecommerce.tenis.controller.dtos.response.BaseResponse;
import jakarta.transaction.Transactional;

public interface IVentaService {
    @Transactional
    BaseResponse createVenta(CreateVentaRequest request);

    @Transactional
    BaseResponse getVentas(int page, int size);

    @Transactional
    BaseResponse getVentaById(Long ventaId);

    @Transactional
    BaseResponse deleteVentaById(Long ventaId);
}
