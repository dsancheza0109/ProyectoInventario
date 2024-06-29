package com.cibertec.models.service;

import com.cibertec.models.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Integer id);
    void save(Usuario usuario);
    void delete(Integer id);
}
