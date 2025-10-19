package com.inventario.inventario.controller;

import com.inventario.inventario.model.Movimiento;
import com.inventario.inventario.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController(MovimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Movimiento> registrar(@Valid @RequestBody Movimiento movimiento) {
        Movimiento nuevo = service.registrarMovimiento(movimiento);
        return ResponseEntity.status(201).body(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> listar() {
        return ResponseEntity.ok(service.listarMovimientos());
    }
}

