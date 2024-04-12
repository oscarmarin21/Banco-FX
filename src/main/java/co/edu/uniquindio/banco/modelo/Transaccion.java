package co.edu.uniquindio.banco.modelo;

import co.edu.uniquindio.banco.modelo.enums.TipoTransaccion;
import co.edu.uniquindio.banco.modelo.enums.CategoriaTransaccion;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Clase que representa una transacción bancaria
 * @version 1.0
 * @author caflorezvi
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Transaccion {

    private final TipoTransaccion tipo; // "Depósito" o "Retiro"
    private final float monto;
    private final Usuario usuario; // Usuario emisor o destinatario de la transacción
    private final LocalDateTime fecha;
    private final CategoriaTransaccion categoria;

    public String getNombreUsuario() {
        return usuario.getNombre(); // Asume que Usuario tiene un método getNombre()
    }

}
