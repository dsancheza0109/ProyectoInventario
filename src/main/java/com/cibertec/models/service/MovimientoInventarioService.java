package com.cibertec.models.service;

import java.util.List;
import com.cibertec.models.entity.MovimientoInventario;

public interface MovimientoInventarioService {
    List<MovimientoInventario> findAll();
    MovimientoInventario findById(int id);
    void save(MovimientoInventario movimientoInventario);
    void delete(int id);
}
