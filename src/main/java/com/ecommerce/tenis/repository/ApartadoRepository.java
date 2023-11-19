package com.ecommerce.tenis.repository;

import com.ecommerce.tenis.entity.Apartado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ApartadoRepository extends JpaRepository<Apartado,Long> {


    @Modifying
    @Query(value = "INSERT INTO apartado (producto_id, cantidad, precio_unitario, sub_total, descuento, total, cliente_id, vigencia, create_at, deleted) " +
            "VALUES (:productoId, :Cantidad, :precioUnitario, :subTotal, :descuento, :total, :clienteId, :vigencia, :createAt, :deleted)",
            nativeQuery = true)
    void createApartado(
            @Param("productoId") Long productoId,
            @Param("Cantidad") Float Cantidad,
            @Param("precioUnitario") Float precioUnitario,
            @Param("subTotal") Float subTotal,
            @Param("descuento") int descuento,
            @Param("total") Float total,
            @Param("clienteId") Long clienteId,
            @Param("vigencia") Date vigencia,
            @Param("createAt") Date createAt,
            @Param("deleted") boolean deleted
    );
    @Query(value = "SELECT * FROM apartado WHERE deleted = 0 LIMIT :offset, :limit", nativeQuery = true)
    List<Apartado> getApartados(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM apartado WHERE deleted = 0", nativeQuery = true)
    int getTotalApartados();


    @Query(value = "SELECT * FROM apartado WHERE apartadoID = :apartadoId AND deleted = 0", nativeQuery = true)
    Optional<Apartado> getApartadoById(@Param("apartadoId") Long apartadoId);

      @Modifying
    @Query(value = "UPDATE apartado SET deleted = 1, deleted_at = :deletedAt WHERE apartadoID = :apartadoId AND deleted = 0", nativeQuery = true)
    int deleteCalzadoById(
            @Param("apartadoId") Long apartadoId,
            @Param("deletedAt") Date deletedAt
    );


}
