package co.edu.uniquindio.banco.modelo.interfaces;

import co.edu.uniquindio.banco.modelo.Usuario;

public interface ControladorConUsuario {

    /**
     * Metodo para inicializar datos y variables de la pantalla
     * @param usuario usuario Logueado
     */
    void initialize(Usuario usuario);
}
