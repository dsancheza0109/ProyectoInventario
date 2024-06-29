package com.cibertec.models.service;

import java.util.List;
import com.cibertec.models.entity.Producto;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(int id);
    void save(Producto producto);
    void delete(int id);
    Producto findByCodigo(String codigo); // Método agregado
}
