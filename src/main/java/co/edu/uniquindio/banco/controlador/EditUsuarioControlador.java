package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.controlador.service.ServiceControlador;
import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.Usuario;
import co.edu.uniquindio.banco.modelo.interfaces.ControladorConUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditUsuarioControlador implements ControladorConUsuario {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtDireccion;
    @FXML
    private Button btnEditar;

    private Usuario usuario;

    private  final Banco banco = Banco.getInstancia();
    private  final ServiceControlador serviceControlador = ServiceControlador.getInstancia();

    @Override
    public void initialize(Usuario usuario) {
        this.usuario = usuario;
        txtNombre.setText(usuario.getNombre());
        txtCorreo.setText(usuario.getCorreoElectronico());
        txtDireccion.setText(usuario.getDireccion());
    }

    /**
     * Al precionar el boton accede a los datos y actualiza el usuario
     * @param actionEvent Evento que representa el clic del botón
     */
    public void editarUsuario(ActionEvent actionEvent){
        try{
            if (txtCorreo.getText().contains("@")){
                String numIdentificacion = usuario.getNumeroIdentificacion();
                banco.actualizarUsuario(txtNombre.getText(),txtDireccion.getText(),usuario.getNumeroIdentificacion(),txtCorreo.getText(),usuario.getContrasena());
                serviceControlador.cerrarVentana(btnEditar);
                usuario = banco.obtenerUsuario(numIdentificacion);
                serviceControlador.navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente", usuario);
            } else {
                throw  new Exception("Ingresa un correo valido");
            }
        }catch(Exception e){
            serviceControlador.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param actionEvent Evento que representa el clic del botón
     */
    public void regresar(ActionEvent actionEvent) {
        serviceControlador.cerrarVentana(btnEditar);
        serviceControlador.navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente", usuario);
    }
}
