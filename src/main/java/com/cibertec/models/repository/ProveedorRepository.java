package com.cibertec.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.models.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
}
