package com.sena.overtimecontrol.dto.usuario;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioResponse(
        Long idUsuario,
        String cedulaUsuario,
        String nombreCompletoUsuario,
        String correoUsuario,
        Long idCargo,
        Long idEstadoXEntidad,
        String areaTrabajo,
        List<String> roles,
        LocalDateTime fechaRegistro
) {

}
