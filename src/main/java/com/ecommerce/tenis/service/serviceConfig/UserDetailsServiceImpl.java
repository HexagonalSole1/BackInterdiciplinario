package com.ecommerce.tenis.service.serviceConfig;


import com.ecommerce.tenis.entity.Usuario;
import com.ecommerce.tenis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

       Usuario user = repository.findByEmail(email)
               .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return new UserDetailsImpl(user);
    }
}
