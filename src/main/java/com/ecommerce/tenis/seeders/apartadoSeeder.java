package com.ecommerce.tenis.seeders;

import com.ecommerce.tenis.entity.Apartado;
import com.ecommerce.tenis.repository.ApartadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class apartadoSeeder implements CommandLineRunner {

    @Autowired
    ApartadoRepository apartadoRepository;

    @Override
    public void run(String... args) throws Exception {
        seedData();

    }
    private void seedData() {
        List<Apartado> apartados = Arrays.asList(
                Apartado.builder()
                        .productoId(1L)
                        .cantidad(2.5f)
                        .precioUnitario(29.99f)
                        .subTotal(74.98f)
                        .descuento(5)
                        .total(69.98f)
                        .clienteId(1L)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),

                Apartado.builder()
                        .productoId(2L)
                        .cantidad(1.0f)
                        .precioUnitario(39.99f)
                        .subTotal(39.99f)
                        .descuento(0)
                        .total(39.99f)
                        .clienteId(2L)
                        .vigencia(new Date())
                        .createdAt(new Date())
                        .deleted(false)
                        .build()
        );

        apartadoRepository.deleteAll();
        apartadoRepository.saveAll(apartados);
        System.out.println("Apartados creados exitosamente");
    }

}
