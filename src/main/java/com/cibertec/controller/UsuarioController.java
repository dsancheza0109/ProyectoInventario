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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.cibertec.models.entity.Usuario;
import com.cibertec.models.service.UsuarioService;

@Controller
@RequestMapping("/views/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String listar(Model model, HttpServletRequest request) {
    	System.out.println("Estoy en el listar");
    	List<Usuario> usuariosList = usuarioService.findAll();
    
        HttpSession session = request.getSession();
        
        String rolUsuarioActivo = (String) session.getAttribute("rolActivo");
        
        model.addAttribute("usuarios", usuariosList);
        if (rolUsuarioActivo != null) {
            if (rolUsuarioActivo.trim().equals("Gerente")) {
                return "views/usuarios/listar";
            } else if (rolUsuarioActivo.trim().equals("Empleado")) {
                return "views/usuarios/listar2";
            }
        }
        
        return "redirect:/index";     	           
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
    
    
    @PostMapping("/login")
    public String sesionUsuario(@RequestParam String email, @RequestParam String contraseña, 
    	RedirectAttributes redirectAttributes,  HttpServletRequest request) {
        System.out.println("estoy en el sesion usuarios " + email +" " + contraseña);
        Usuario usuarioSesion = usuarioService.findByEmailandPassword(email, contraseña);

        if(usuarioSesion!= null) {
            String nombreUsuario = usuarioSesion.getNombre();
            String rolUsuario = usuarioSesion.getRol();

            HttpSession session = request.getSession();
            session.setAttribute("nombreActivo", nombreUsuario);
            session.setAttribute("rolActivo", rolUsuario);
            
            redirectAttributes.addFlashAttribute("usuarioSesion", usuarioSesion);
            
            System.out.println("Nombre del usuario activo: " + usuarioSesion.getNombre());
            System.out.println("Rol del usuario activo: " + usuarioSesion.getRol());
            return "redirect:/views/usuarios/";
        	
        } else {
        	 redirectAttributes.addAttribute("error", "Correo electrónico o contraseña incorrectos");
        	return "redirect:/index";
        }
        
        
        
    }
    
    @PostMapping("/logout")
    public String cerrarSesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false); 
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/index"; 
    }
    
    
    
}
