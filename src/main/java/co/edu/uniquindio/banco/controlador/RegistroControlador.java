package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.controlador.service.ServiceControlador;
import co.edu.uniquindio.banco.modelo.Banco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroControlador {

    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtDireccion;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnRegister;

    private final Banco banco = Banco.getInstancia();
    private final ServiceControlador serviceControlador = ServiceControlador.getInstancia();

    /**
     * Método para registrar al usuario
     * @param actionEvent Evento que representa el clic del botón
     */
    public void registrarse(ActionEvent actionEvent) {

        try {
            // Se intenta agregar el usuario al banco
            banco.agregarUsuario(
                    txtNombre.getText(),
                    txtDireccion.getText(),
                    txtIdentificacion.getText(),
                    txtCorreo.getText(),
                    txtPassword.getText() );

            // Se intenta agregar una cuenta de ahorros al usuario
            banco.agregarCuentaAhorros(txtIdentificacion.getText(), 0F);

            // Se muestra un mensaje de éxito y se cierra la ventana
            serviceControlador.crearAlerta("Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            serviceControlador.cerrarVentana(btnRegister);

        }catch (Exception e){
            serviceControlador.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param actionEvent Evento que representa el clic del botón
     */
    public void regresar(ActionEvent actionEvent) {
        serviceControlador.cerrarVentana(btnRegister);
        serviceControlador.navegarVentana("/inicio.fxml", "Banco - Inicio");
    }
}
