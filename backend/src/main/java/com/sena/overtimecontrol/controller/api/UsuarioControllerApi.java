
package com.sena.overtimecontrol.controller.api;

import com.sena.overtimecontrol.dto.usuario.UsuarioRequest;
import com.sena.overtimecontrol.dto.usuario.UsuarioResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usuarios")
public interface UsuarioControllerApi {

    @GetMapping
    ResponseEntity<List<UsuarioResponse>> listarUsuarios();

    @GetMapping("/{id}")
    ResponseEntity<UsuarioResponse> obtenerUsuario(
            @PathVariable("id") Long id
    );

    @PostMapping
    ResponseEntity<UsuarioResponse> crearUsuario(
            @RequestBody UsuarioRequest usuarioRequest
    );

    @PutMapping("/{id}")
    ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable("id") Long id,
            @RequestBody UsuarioRequest usuarioRequest
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarUsuario(
            @PathVariable("id") Long id
    );
}
