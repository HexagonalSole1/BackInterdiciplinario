package com.ecommerce.tenis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorias;

    @Column(name = "categoria", length = 45)
    private  String categoria;


    @OneToMany(targetEntity = Calzado.class, fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Calzado> calzados;



}
