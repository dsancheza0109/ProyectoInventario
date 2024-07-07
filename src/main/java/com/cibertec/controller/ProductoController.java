package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.models.entity.Producto;

import com.cibertec.models.service.CategoriaService;
import com.cibertec.models.service.ProductoService;
import com.cibertec.models.service.ProveedorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/views/productos/")
    public String listar(Model model , HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	String rolUsuarioActivo = (String) session.getAttribute("rolActivo");
    	
        model.addAttribute("productos", productoService.findAll());
        if (rolUsuarioActivo != null) {
            if (rolUsuarioActivo.trim().equals("Gerente")) {
                return "views/productos/listar";
            } else if (rolUsuarioActivo.trim().equals("Empleado")) {
                return "views/productos/listar2";
            }
        }
        
        return "redirect:/index";
        
        
    }

    @GetMapping("/views/productos/create")
    public String crear(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("titulo", "Nuevo Producto");
        return "views/productos/frmCrear";
    }

    @GetMapping("/views/productos/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            return "redirect:/views/productos/";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("titulo", "Editar Producto");
        return "views/productos/frmCrear";
    }

    @PostMapping("/views/productos/save")
    public String guardar(Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.findAll());
            model.addAttribute("proveedores", proveedorService.findAll());
            model.addAttribute("titulo", producto.getId() != 0 ? "Editar Producto" : "Nuevo Producto");
            return "views/productos/frmCrear";
        }

        try {
            // Validar unicidad del código
            Producto existingProducto = productoService.findByCodigo(producto.getCodigo());
            if (existingProducto != null && existingProducto.getId() != producto.getId()) {
                model.addAttribute("categorias", categoriaService.findAll());
                model.addAttribute("proveedores", proveedorService.findAll());
                model.addAttribute("producto", producto);
                model.addAttribute("titulo", producto.getId() != 0 ? "Editar Producto" : "Nuevo Producto");
                model.addAttribute("error", "El código del producto ya existe.");
                return "views/productos/frmCrear";
            }

            productoService.save(producto);
        } catch (Exception e) {
            model.addAttribute("categorias", categoriaService.findAll());
            model.addAttribute("proveedores", proveedorService.findAll());
            model.addAttribute("producto", producto);
            model.addAttribute("titulo", producto.getId() != 0 ? "Editar Producto" : "Nuevo Producto");
            model.addAttribute("error", "Ocurrió un error al guardar el producto.");
            return "views/productos/frmCrear";
        }

        return "redirect:/views/productos/";
    }

    @GetMapping("/views/productos/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        productoService.delete(id);
        return "redirect:/views/productos/";
    }
}
