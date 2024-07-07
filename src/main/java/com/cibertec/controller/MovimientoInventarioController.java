package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.models.entity.MovimientoInventario;
import com.cibertec.models.service.MovimientoInventarioService;
import com.cibertec.models.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @Autowired
    private UsuarioService usuarioService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new org.springframework.beans.propertyeditors.CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/views/movimientos/")
    public String listar(Model model, HttpServletRequest request) {
        model.addAttribute("movimientos", movimientoInventarioService.findAll());
        HttpSession session = request.getSession();
        String rolUsuarioActivo = (String) session.getAttribute("rolActivo");
        if (rolUsuarioActivo != null) {
            if (rolUsuarioActivo.trim().equals("Gerente")) {
                return "views/movimientos/listar";
            } else if (rolUsuarioActivo.trim().equals("Empleado")) {
                return "views/movimientos/listar2";
            }
        }
        
        
        return "redirect:/index";
    }

    @GetMapping("/views/movimientos/create")
    public String crear(Model model) {
        model.addAttribute("movimiento", new MovimientoInventario());
        model.addAttribute("titulo", "Nuevo Movimiento de Inventario");
        model.addAttribute("usuarios", usuarioService.findAll());
        return "views/movimientos/frmCrear";
    }

    @GetMapping("/views/movimientos/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        MovimientoInventario movimiento = movimientoInventarioService.findById(id);
        if (movimiento == null) {
            return "redirect:/views/movimientos/";
        }
        model.addAttribute("movimiento", movimiento);
        model.addAttribute("titulo", "Editar Movimiento de Inventario");
        model.addAttribute("usuarios", usuarioService.findAll());
        return "views/movimientos/frmCrear";
    }

    @PostMapping("/views/movimientos/save")
    public String guardar(@Valid @ModelAttribute("movimiento") MovimientoInventario movimiento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", movimiento.getId() == 0 ? "Nuevo Movimiento de Inventario" : "Editar Movimiento de Inventario");
            model.addAttribute("usuarios", usuarioService.findAll());
            return "views/movimientos/frmCrear";
        }
        movimientoInventarioService.save(movimiento);
        return "redirect:/views/movimientos/";
    }

    @GetMapping("/views/movimientos/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        movimientoInventarioService.delete(id);
        return "redirect:/views/movimientos/";       
    }
}
