package com.ecommerce.tenis.seeders;

import com.ecommerce.tenis.entity.Calzado;
import com.ecommerce.tenis.repository.CalzadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class calzadoSeeder implements CommandLineRunner {

    @Autowired
    private CalzadoRepository calzadoRepository;

    public void run(String... args) throws Exception {
        System.out.println("Aqui falla");
        seedData();
    }

    private void seedData() {
        List<Calzado> calzados = Arrays.asList(
                Calzado.builder()
                        .precio(new BigDecimal("49.99"))
                        .talla("40")
                        .modelo("Deportivos Negros")
                        .genero('H')
                        .color("D")
                        .descripcion("Zapatos deportivos negros para hombres")
                        .tipo("Deportivos")
                        .inventario(10)
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),

                Calzado.builder()
                        .precio(new BigDecimal("54.99"))
                        .talla("38")
                        .modelo("Zapatos Deportivos Rosados")
                        .genero('H')
                        .color("Rosa")
                        .descripcion("Zapatos deportivos rosados para mujeres")
                        .tipo("Deportivos")
                        .inventario(13)
                        .createdAt(new Date())
                        .deleted(false)
                        .build()
        );
        calzadoRepository.deleteAll();
        calzadoRepository.saveAll(calzados);
        System.out.println("Calzados creados exitosamente");
    }
}
