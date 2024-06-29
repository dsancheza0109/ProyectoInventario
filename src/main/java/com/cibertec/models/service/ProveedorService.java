package com.cibertec.models.service;

import java.util.List;
import com.cibertec.models.entity.Proveedor;

public interface ProveedorService {
    List<Proveedor> findAll();
    Proveedor findById(int id);
    void save(Proveedor proveedor);
    void delete(int id);
}
