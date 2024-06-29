package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.models.entity.Usuario;
import com.cibertec.models.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/views/usuarios/")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "views/usuarios/listar";
    }

    @GetMapping("/views/usuarios/create")
    public String crear(Model model) {
        model.addAttribute("usuario", new Usuario(null, null, null, null));
        model.addAttribute("titulo", "Nuevo Usuario");
        return "views/usuarios/frmCrear";
    }

    @GetMapping("/views/usuarios/edit/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return "redirect:/views/usuarios/";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Editar Usuario");
        return "views/usuarios/frmCrear";
    }

    @PostMapping("/views/usuarios/save")
    public String guardar(Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Usuario");
            return "views/usuarios/frmCrear";
        }
        usuarioService.save(usuario);
        return "redirect:/views/usuarios/";
    }

    @GetMapping("/views/usuarios/delete/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        usuarioService.delete(id);
        return "redirect:/views/usuarios/";
    }
}
