package com.ecommerce.tenis.repository;


import com.ecommerce.tenis.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categoria,Long> {
}
