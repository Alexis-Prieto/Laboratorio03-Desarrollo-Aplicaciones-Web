package com.tecsup.controller;

import com.tecsup.model.Empleado;
import com.tecsup.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService service;

    @GetMapping
    public List<Empleado> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado e) {
        return ResponseEntity.status(201).body(service.guardar(e));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtener(@PathVariable Long id) {
        Empleado e = service.obtener(id);
        return e == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Long id, @RequestBody Empleado e) {
        Empleado ex = service.obtener(id);
        if (ex == null) return ResponseEntity.notFound().build();
        ex.setNombre(e.getNombre());
        ex.setApellido(e.getApellido());
        ex.setCargo(e.getCargo());
        ex.setTelefono(e.getTelefono());
        return ResponseEntity.ok(service.guardar(ex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtener(id) == null) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}