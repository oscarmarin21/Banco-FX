package co.edu.uniquindio.banco.modelo;

import lombok.*;

/**
 * Clase que representa un usuario del banco
 * @version 1.0
 * @autor caflorezvi
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    private final String numeroIdentificacion;

    private String nombre;
    private String direccion;
    private String correoElectronico;
    private final String contrasena;

}
