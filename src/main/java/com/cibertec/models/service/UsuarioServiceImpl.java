package com.cibertec.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.models.entity.Usuario;
import com.cibertec.models.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
