package com.cibertec.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.models.entity.Producto;
import com.cibertec.models.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto findByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }
}
