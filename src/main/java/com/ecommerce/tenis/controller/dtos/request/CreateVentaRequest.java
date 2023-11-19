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
public class CreateVentaRequest {




    private Long ventaId;

    private Long clienteId;
    private Long productoId;



    private int cantidad;
    private Float precioUnitario;
    private Float subTotal;
    private int descuento;
    private Float total;


    private Date vigencia;


}
