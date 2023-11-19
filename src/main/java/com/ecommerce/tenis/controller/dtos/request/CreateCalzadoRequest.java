package com.ecommerce.tenis.controller.dtos.request;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCalzadoRequest {



    private BigDecimal precio;

    private String talla;

    private String modelo;


    private char genero;

    private String color;

    private String descripcion;

    private String tipo;

    private Integer inventario;
    private  Long id_categoria;


}
