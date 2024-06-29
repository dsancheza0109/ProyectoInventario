package com.cibertec.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.models.entity.MovimientoInventario;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
}
