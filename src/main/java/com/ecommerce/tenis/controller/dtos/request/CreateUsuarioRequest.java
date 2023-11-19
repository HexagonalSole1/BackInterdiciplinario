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
public class CreateUsuarioRequest {



    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private String rol;

}
