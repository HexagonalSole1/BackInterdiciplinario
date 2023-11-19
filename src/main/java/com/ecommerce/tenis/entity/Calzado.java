package com.ecommerce.tenis.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calzado")
public class Calzado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoID")
    private Long productoID;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "talla", length = 10)
    private String talla;

    @Column(name = "modelo", length = 45)
    private String modelo;

    @Column(name = "genero", length = 1)
    private char genero;

    @Column(name = "color", length = 45)
    private String color;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "tipo", length = 45)
    private String tipo;

    @Column(name = "inventario")
    private Integer inventario;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt= new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted")
    private Boolean deleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;

}
