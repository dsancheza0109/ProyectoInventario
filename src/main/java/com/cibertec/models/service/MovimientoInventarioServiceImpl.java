package com.cibertec.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.models.entity.MovimientoInventario;
import com.cibertec.models.repository.MovimientoInventarioRepository;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Override
    public List<MovimientoInventario> findAll() {
        return movimientoInventarioRepository.findAll();
    }

    @Override
    public MovimientoInventario findById(int id) {
        return movimientoInventarioRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MovimientoInventario movimientoInventario) {
        movimientoInventarioRepository.save(movimientoInventario);
    }

    @Override
    public void delete(int id) {
        movimientoInventarioRepository.deleteById(id);
    }
    
}
