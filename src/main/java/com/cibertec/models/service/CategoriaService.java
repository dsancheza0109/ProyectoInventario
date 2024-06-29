package com.cibertec.models.service;

import java.util.List;
import com.cibertec.models.entity.Categoria;

public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findById(int id);
    void save(Categoria categoria);
    void delete(int id);
}
