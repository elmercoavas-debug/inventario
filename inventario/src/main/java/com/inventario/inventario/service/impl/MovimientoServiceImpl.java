package com.inventario.inventario.service.impl;

import com.inventario.inventario.model.Movimiento;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.MovimientoRepository;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.MovimientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepo;
    private final ProductoRepository productoRepo;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepo, ProductoRepository productoRepo) {
        this.movimientoRepo = movimientoRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Producto producto = productoRepo.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar stock según tipo
        if (movimiento.getTipo().equalsIgnoreCase("ENTRADA")) {
            producto.setCantidad(producto.getCantidad() + movimiento.getCantidad());
        } else if (movimiento.getTipo().equalsIgnoreCase("SALIDA")) {
            if (producto.getCantidad() < movimiento.getCantidad()) {
                throw new RuntimeException("No hay stock suficiente para la salida");
            }
            producto.setCantidad(producto.getCantidad() - movimiento.getCantidad());
        } else {
            throw new RuntimeException("Tipo de movimiento inválido (use ENTRADA o SALIDA)");
        }

        productoRepo.save(producto);
        return movimientoRepo.save(movimiento);
    }

    @Override
    public List<Movimiento> listarMovimientos() {
        return movimientoRepo.findAll();
    }
}
