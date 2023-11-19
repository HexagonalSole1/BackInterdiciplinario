package com.ecommerce.tenis.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venta")
public class Venta {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ventaId")
    private Long ventaId;

    @Column(name = "cliente_id")
    private Long clienteId;
    @Column(name = "producto_id")
    private Long productoId;



    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "precio_unitario")
    private Float precioUnitario;
    @Column(name = "sub_total")
    private Float subTotal;
    @Column(name = "descuento")
    private int descuento;
    @Column(name = "total")
    private Float total;



    @Column(name = "vigencia")
    private Date vigencia;

    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "updatedAt")
    private Date updatedAt;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "deletedAt")
    private Date deletedAt;

}
