package com.ecommerce.tenis.repository;

import com.ecommerce.tenis.entity.Apartado;
import com.ecommerce.tenis.entity.Calzado;
import com.ecommerce.tenis.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Modifying
    @Query(value = "INSERT INTO Usuario (nombre, apellido, email, password, rol, deleted, created_at) VALUES (:nombre, :apellido, :email, :password, :rol, 0, :created_at)", nativeQuery = true)
    void createUsuario(
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("email") String email,
            @Param("password") String password,
            @Param("rol") String rol,
            @Param("created_at") Date created_at
    );

    @Query(value = "SELECT COUNT(email) FROM usuario WHERE email = :email", nativeQuery = true)
    int existeUsuario(@Param("email") String email);



    @Modifying
    @Query(value = "UPDATE Usuario SET deleted = 1, deleted_at = :deletedAt WHERE clienteId = :clienteId AND deleted = 0", nativeQuery = true)
    void deleteUsuarioById(
            @Param("clienteId") Long clienteId,
            @Param("deletedAt") Date deletedAt
    );
    @Query(value = "SELECT * FROM Usuario WHERE deleted = 0 LIMIT :offset, :limit", nativeQuery = true)
    List<Usuario> getUsuarios(@Param("offset") int offset, @Param("limit") int limit);
    @Query(value = "SELECT COUNT(*) FROM Usuario WHERE deleted = 0", nativeQuery = true)
    int getTotalUsuarios();
    @Query(value = "SELECT * FROM Usuario WHERE clienteId = :clienteId AND deleted = 0", nativeQuery = true)
    List<Usuario> getUsuarioById(@Param("clienteId") Long clienteId);


    @Modifying
    @Query(value = "UPDATE Usuario SET nombre = :nombre, apellido = :apellido, email = :email, password = :password, rol = :rol, updated_at = :updatedAt WHERE clienteID = :clienteId and deleted=0", nativeQuery = true)
    void updateUsuario(
            @Param("clienteId") Long clienteId,
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("email") String email,
            @Param("password") String password,
            @Param("rol") String rol,
            @Param("updatedAt") Date updatedAt
    );



    Optional<Usuario> findByEmail(String username);


}
