package com.cibertec.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.models.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
