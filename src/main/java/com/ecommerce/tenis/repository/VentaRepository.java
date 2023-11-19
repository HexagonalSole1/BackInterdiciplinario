package com.ecommerce.tenis.repository;

import com.ecommerce.tenis.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Modifying
    @Query(value = "INSERT INTO venta (producto_id, cantidad, precio_unitario, sub_total, descuento, total, cliente_id, vigencia, create_at, deleted) " +
            "VALUES (:productoId, :cantidad, :precioUnitario, :subTotal, :descuento, :total, :clienteId, :vigencia, :createAt, :deleted)",
            nativeQuery = true)
    void createVenta(
            @Param("productoId") Long productoId,
            @Param("cantidad") int cantidad,
            @Param("precioUnitario") Float precioUnitario,
            @Param("subTotal") Float subTotal,
            @Param("descuento") int descuento,
            @Param("total") Float total,
            @Param("clienteId") Long clienteId,
            @Param("vigencia") Date vigencia,
            @Param("createAt") Date createAt,
            @Param("deleted") boolean deleted
    );

    @Query(value = "SELECT * FROM venta WHERE deleted = 0 LIMIT :offset, :limit", nativeQuery = true)
    List<Venta> getVentas(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM venta WHERE deleted = 0", nativeQuery = true)
    int getTotalVentas();

    @Query(value = "SELECT * FROM venta WHERE venta_id = :ventaId AND deleted = 0", nativeQuery = true)
    Optional<Venta> getVentaById(@Param("ventaId") Long ventaId);

    @Modifying
    @Query(value = "UPDATE venta SET deleted = 1, deletedAt = :deletedAt WHERE venta_id = :ventaId AND deleted = 0", nativeQuery = true)
    int deleteVentaById(
            @Param("ventaId") Long ventaId,
            @Param("deletedAt") Date deletedAt
    );
}
