package com.tecsup.controller;

import com.tecsup.model.Cliente;
import com.tecsup.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente c) {
        return ResponseEntity.status(201).body(service.guardar(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        Cliente c = service.obtener(id);
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente c) {
        Cliente ex = service.obtener(id);
        if (ex == null) return ResponseEntity.notFound().build();
        ex.setNombre(c.getNombre());
        ex.setApellido(c.getApellido());
        ex.setTelefono(c.getTelefono());
        ex.setEmail(c.getEmail());
        return ResponseEntity.ok(service.guardar(ex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtener(id) == null) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}