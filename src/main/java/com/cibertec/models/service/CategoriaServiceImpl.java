package com.cibertec.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.models.entity.Categoria;
import com.cibertec.models.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void delete(int id) {
        categoriaRepository.deleteById(id);
    }
}
