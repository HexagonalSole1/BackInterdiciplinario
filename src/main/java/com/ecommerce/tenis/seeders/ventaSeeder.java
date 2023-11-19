package com.ecommerce.tenis.seeders;

import com.ecommerce.tenis.entity.Apartado;
import com.ecommerce.tenis.entity.Venta;
import com.ecommerce.tenis.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class ventaSeeder  implements CommandLineRunner {

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() {
        List<Venta> ventas = Arrays.asList(
                Venta.builder()
                        .clienteId(1L)
                        .productoId(1L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),

                         Venta.builder()
                        .clienteId(1L)
                        .productoId(2L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),   Venta.builder()
                        .clienteId(1L)
                        .productoId(1L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),

                Venta.builder()
                        .clienteId(1L)
                        .productoId(3L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),
                Venta.builder()
                        .clienteId(1L)
                        .productoId(4L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),
                Venta.builder()
                        .clienteId(1L)
                        .productoId(5L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),
                Venta.builder()
                        .clienteId(1L)
                        .productoId(6L)
                        .cantidad(3)
                        .precioUnitario(500F)
                        .subTotal(500F)
                        .descuento(20)
                        .total(1500F)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build()

        );

        ventaRepository.deleteAll();
        ventaRepository.saveAll(ventas);
        System.out.println("Ventas creados exitosamente");
    }
}
