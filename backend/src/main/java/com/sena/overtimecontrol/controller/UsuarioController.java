
package com.sena.overtimecontrol.controller;

import com.sena.overtimecontrol.controller.api.UsuarioControllerApi;
import com.sena.overtimecontrol.dto.usuario.UsuarioRequest;
import com.sena.overtimecontrol.dto.usuario.UsuarioResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController implements UsuarioControllerApi {

    // Lista temporal para almacenar usuarios en memoria
    private final List<UsuarioResponse> usuarios = new ArrayList<>(
            List.of(
                    new UsuarioResponse(
                            1L,
                            "1000000001",
                            "Ana Torres",
                            "ana.torres@overtimecontrol.com",
                            1L,
                            1L,
                            "Administración",
                            List.of("ADMINISTRADOR"),
                            LocalDateTime.now()
                    ),
                    new UsuarioResponse(
                            2L,
                            "1000000002",
                            "Carlos Gómez",
                            "carlos.gomez@overtimecontrol.com",
                            2L,
                            1L,
                            "Recursos Humanos",
                            List.of("SUPERVISOR"),
                            LocalDateTime.now()
                    ),
                    new UsuarioResponse(
                            3L,
                            "1000000003",
                            "Laura Pérez",
                            "laura.perez@overtimecontrol.com",
                            3L,
                            1L,
                            "Alimentos y Bebidas",
                            List.of("EMPLEADO"),
                            LocalDateTime.now()
                    )
            )
    );

    // Listar todos los usuarios
    @Override
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarios);
    }

    // Consultar un usuario por su ID
    @Override
    public ResponseEntity<UsuarioResponse> obtenerUsuario(Long id) {

        return usuarios.stream()
                .filter(usuario -> usuario.idUsuario().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Registrar un usuario
    @Override
    public ResponseEntity<UsuarioResponse> crearUsuario(
            UsuarioRequest usuarioRequest) {

        Long nuevoId = usuarios.stream()
                .mapToLong(UsuarioResponse::idUsuario)
                .max()
                .orElse(0L) + 1;

        UsuarioResponse nuevoUsuario = new UsuarioResponse(
                nuevoId,
                usuarioRequest.cedulaUsuario(),
                usuarioRequest.nombreCompletoUsuario(),
                usuarioRequest.correoUsuario(),
                usuarioRequest.idCargo(),
                usuarioRequest.idEstadoXEntidad(),
                usuarioRequest.areaTrabajo(),
                usuarioRequest.roles(),
                LocalDateTime.now()
        );

        usuarios.add(nuevoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoUsuario);
    }

    // Actualizar un usuario
    @Override
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            Long id,
            UsuarioRequest usuarioRequest) {

        for (int i = 0; i < usuarios.size(); i++) {

            UsuarioResponse usuarioActual = usuarios.get(i);

            if (usuarioActual.idUsuario().equals(id)) {

                UsuarioResponse usuarioActualizado = new UsuarioResponse(
                        id,
                        usuarioRequest.cedulaUsuario(),
                        usuarioRequest.nombreCompletoUsuario(),
                        usuarioRequest.correoUsuario(),
                        usuarioRequest.idCargo(),
                        usuarioRequest.idEstadoXEntidad(),
                        usuarioRequest.areaTrabajo(),
                        usuarioRequest.roles(),
                        usuarioActual.fechaRegistro()
                );

                usuarios.set(i, usuarioActualizado);

                return ResponseEntity.ok(usuarioActualizado);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un usuario
    @Override
    public ResponseEntity<Void> eliminarUsuario(Long id) {

        boolean eliminado = usuarios.removeIf(
                usuario -> usuario.idUsuario().equals(id)
        );

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
