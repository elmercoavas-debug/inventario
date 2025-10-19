package com.inventario.inventario.service;

import com.inventario.inventario.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto crear(Producto p);
    Producto actualizar(Long id, Producto p);
    Optional<Producto> obtenerPorId(Long id);
    List<Producto> listar();
    void eliminar(Long id);
}

