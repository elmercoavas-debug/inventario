package com.inventario.inventario.service.impl;

import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.ProductoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Producto crear(Producto p) {
        if (p.getCantidad() == null) p.setCantidad(0);
        return repo.save(p);
    }

    @Override
    public Producto actualizar(Long id, Producto p) {
        Optional<Producto> opt = repo.findById(id);
        if (opt.isEmpty()) throw new RuntimeException("Producto no encontrado");

        Producto existente = opt.get();
        existente.setNombre(p.getNombre());
        existente.setCategoria(p.getCategoria());
        existente.setPrecio(p.getPrecio());
        existente.setDescripcion(p.getDescripcion());
        existente.setCantidad(p.getCantidad());

        return repo.save(existente);
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

