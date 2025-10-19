package com.inventario.inventario.service;

import com.inventario.inventario.model.Movimiento;
import java.util.List;

public interface MovimientoService {
    Movimiento registrarMovimiento(Movimiento movimiento);
    List<Movimiento> listarMovimientos();
}

