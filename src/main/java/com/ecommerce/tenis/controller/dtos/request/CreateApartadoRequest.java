package com.ecommerce.tenis.controller.dtos.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApartadoRequest {

    private Long productoId;
    private Float Cantidad;
    private Float precioUnitario;
    private Float subTotal;
    private int descuento;
    private Float total;
    private Long clienteId;
    private Date vigencia;

}
