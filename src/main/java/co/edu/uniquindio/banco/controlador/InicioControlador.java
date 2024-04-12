package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.controlador.service.ServiceControlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioControlador {

    @FXML
    private Button btnLogin;

    private final ServiceControlador serviceControlador = ServiceControlador.getInstancia();

    /**
     * Método que permite ir a la vista de Iniciar Sesión
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irIniciarSesion(ActionEvent actionEvent) {
        serviceControlador.cerrarVentana(btnLogin);
        serviceControlador.navegarVentana("/login.fxml", "Banco - Iniciar Sesión");
    }

    /**
     * Método que permite ir a la vista de Registro de Cliente
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irRegistroCliente(ActionEvent actionEvent) {
        serviceControlador.cerrarVentana(btnLogin);
        serviceControlador.navegarVentana("/registro.fxml", "Banco - Registro de Cliente");
    }
}
