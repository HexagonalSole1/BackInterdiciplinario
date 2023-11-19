package com.ecommerce.tenis.repository;

import com.ecommerce.tenis.controller.dtos.request.CreateCalzadoRequest;
import com.ecommerce.tenis.entity.Calzado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CalzadoRepository extends JpaRepository<Calzado, Long> {

    @Modifying
    @Query(value = "INSERT INTO Calzado (precio, talla, modelo, genero, color,id_categoria, descripcion, tipo, inventario, deleted, created_at) " +
            "VALUES (:precio, :talla, :modelo, :genero, :color,:id_categoria, :descripcion, :tipo, :inventario, :deleted, :createdAt)", nativeQuery = true)
    void createCalzado(
            @Param("precio") BigDecimal precio ,
            @Param("talla") String talla,
            @Param("modelo") String modelo,
            @Param("genero") char genero,
            @Param("id_categoria") Long idCategoria,

            @Param("color") String color,
            @Param("descripcion") String descripcion,
            @Param("tipo") String tipo,
            @Param("inventario") int inventario,
            @Param("deleted") boolean deleted,
            @Param("createdAt") Date createdAt
    );

    @Modifying
    @Query(value = "UPDATE calzado SET deleted = 1, deleted_at = :deletedAt WHERE productoID = :productoId AND deleted = 0", nativeQuery = true)
    void deleteCalzadoById(
            @Param("productoId") Long productoId,
            @Param("deletedAt") Date deletedAt
    );
    @Query(value = "SELECT * FROM calzado WHERE deleted = 0 LIMIT :offset, :limit", nativeQuery = true)
    List<Calzado> getCalzado(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM calzado WHERE deleted = 0", nativeQuery = true)
    int getTotalCalzados();

    @Query(value = "SELECT * FROM calzado WHERE productoID = :productoId AND deleted = 0", nativeQuery = true)
    List<Calzado> getCalzado(@Param("productoId") Long productoId);


    @Modifying
    @Query(value = "UPDATE Calzado SET precio = :precio, talla = :talla, modelo = :modelo, genero = :genero, color = :color, " +
            "descripcion = :descripcion, tipo = :tipo, inventario = :inventario, updated_at = :updatedAt " +
            "WHERE productoID = :productoId AND deleted = 0", nativeQuery = true)
    void updateCalzado(
            @Param("productoId") Long productoId,
            @Param("precio") BigDecimal precio,
            @Param("talla") String talla,
            @Param("modelo") String modelo,
            @Param("genero") char genero,
            @Param("color") String color,
            @Param("descripcion") String descripcion,
            @Param("tipo") String tipo,
            @Param("inventario") Integer inventario,
            @Param("updatedAt") Date updatedAt
    );
}
