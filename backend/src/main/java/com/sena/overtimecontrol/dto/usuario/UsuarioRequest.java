
package com.sena.overtimecontrol.dto.usuario;

import java.util.List;

public record UsuarioRequest(
        String cedulaUsuario,
        String nombreCompletoUsuario,
        String correoUsuario,
        String contrasenaUsuario,
        Long idCargo,
        Long idEstadoXEntidad,
        String areaTrabajo,
        List<String> roles
) {

}
