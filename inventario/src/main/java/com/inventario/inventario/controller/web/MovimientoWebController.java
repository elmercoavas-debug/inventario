package com.inventario.inventario.controller.web;

import com.inventario.inventario.model.Movimiento;
import com.inventario.inventario.service.MovimientoService;
import com.inventario.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movimientos")
public class MovimientoWebController {

    private final MovimientoService movimientoService;
    private final ProductoService productoService;

    public MovimientoWebController(MovimientoService movimientoService, ProductoService productoService) {
        this.movimientoService = movimientoService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listarMovimientos(Model model) {
        model.addAttribute("movimientos", movimientoService.listarMovimientos());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("titulo", "Registro de Movimientos");
        return "movimientos";
    }

    @PostMapping("/agregar")
    public String agregarMovimiento(Movimiento movimiento) {
        movimientoService.registrarMovimiento(movimiento);
        return "redirect:/movimientos";
    }
}

