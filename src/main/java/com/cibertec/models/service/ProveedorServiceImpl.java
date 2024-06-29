package com.cibertec.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.models.entity.Proveedor;
import com.cibertec.models.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findById(int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public void delete(int id) {
        proveedorRepository.deleteById(id);
    }
}
