package com.cibertec.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.models.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByCodigo(String codigo); // Método agregado
}
