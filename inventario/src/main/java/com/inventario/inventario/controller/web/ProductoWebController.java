package com.inventario.inventario.controller.web;

import com.inventario.inventario.model.Producto;
import com.inventario.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    private final ProductoService productoService;

    public ProductoWebController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("titulo", "Gestión de Productos");
        return "productos";
    }

    @PostMapping("/agregar")
    public String agregarProducto(Producto producto) {
        productoService.crear(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
