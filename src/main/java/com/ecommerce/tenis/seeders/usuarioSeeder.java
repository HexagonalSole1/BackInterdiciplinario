package com.ecommerce.tenis.seeders;

import com.ecommerce.tenis.entity.Usuario;
import com.ecommerce.tenis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class usuarioSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() {
        List<Usuario> usuarios = Arrays.asList(
                Usuario.builder()
                        .nombre("John")
                        .apellido("Doe")
                        .email("john.doe@example.com")
                        .password(encodePassword("password123"))
                        .rol("USER")
                        .createdAt(new Date())
                        .deleted(false)
                        .build(),

                Usuario.builder()
                        .nombre("Jane")
                        .apellido("Doe")
                        .email("jana.doe@example.com")
                        .password(encodePassword("password123"))
                        .rol("ADMIN")
                        .createdAt(new Date())
                        .deleted(false)
                        .build()
        );

        usuarioRepository.deleteAll();
        usuarioRepository.saveAll(usuarios);
        System.out.println("Usuarios creados exitosamente");
    }
    private static String encodePassword(String request){
        return new BCryptPasswordEncoder().encode(request);
    }

}
