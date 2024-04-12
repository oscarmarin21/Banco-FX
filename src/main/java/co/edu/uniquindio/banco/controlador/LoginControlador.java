package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.controlador.service.ServiceControlador;
import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControlador {

    @FXML
    private TextField txtIdentificacion;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    private final Banco banco = Banco.getInstancia();
    private final ServiceControlador serviceControlador = ServiceControlador.getInstancia();

    /**
     * Método que se ejecuta al presionar el botón de iniciar sesión
     * @param actionEvent evento de acción
     */
    public void login(ActionEvent actionEvent) {
        try {
            Usuario usuario = banco.validarUsuario(txtIdentificacion.getText(), txtPassword.getText());
            if (usuario!=null) {
                // Se muestra un mensaje de éxito y se cierra la ventana
                serviceControlador.crearAlerta("Inicio de sesión existoso", Alert.AlertType.INFORMATION);
                serviceControlador.cerrarVentana(btnLogin);
                serviceControlador.navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente", usuario);
            }

        }catch (Exception e){
            serviceControlador.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param actionEvent Evento que representa el clic del botón
     */
    public void regresar(ActionEvent actionEvent) {
        serviceControlador.cerrarVentana(btnLogin);
        serviceControlador.navegarVentana("/inicio.fxml", "Banco - Inicio");
    }
}
