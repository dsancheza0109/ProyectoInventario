package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.models.entity.Usuario;
import com.cibertec.models.service.UsuarioService;

@Controller
@RequestMapping("/views/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String listar(Model model) {
    	System.out.println("estoy en el listar de usuario controller ");
    	List<Usuario> usuariosList = usuarioService.findAll();
        model.addAttribute("usuarios", usuariosList);
        
        for(Usuario usu : usuariosList) {
        	System.out.println(usu);
        }
        
        return "views/usuarios/listar";
    }

    @GetMapping("/create")
    public String crear(Model model) {
    	System.out.println("estoy en el crear de usuario controller ");
    	Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Nuevo Usuario");
        return "views/usuarios/frmCrear";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return "redirect:/views/usuarios/";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Editar Usuario");
        return "views/usuarios/frmCrear";
    }

    @PostMapping("/save")
    public String guardar(Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Usuario");
            return "views/usuarios/frmCrear";
        }
        usuarioService.save(usuario);
        return "redirect:/views/usuarios/";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        usuarioService.delete(id);
        return "redirect:/views/usuarios/";
    }
}
