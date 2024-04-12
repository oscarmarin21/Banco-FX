package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.controlador.service.ServiceControlador;
import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.CuentaAhorros;
import co.edu.uniquindio.banco.modelo.Usuario;
import co.edu.uniquindio.banco.modelo.enums.CategoriaTransaccion;
import co.edu.uniquindio.banco.modelo.interfaces.ControladorConUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que se encarga de controlar la creación de transferencias entre cuentas
 * @author caflorezvi
 */
@Getter
@Setter
public class TransferenciaControlador implements ControladorConUsuario {

    @FXML
    private ComboBox<CategoriaTransaccion> selectCat;
    @FXML
    private TextField txtNumCuenta;
    @FXML
    private TextField txtMonto;
    @FXML
    private Button btnTransferir;

    private Usuario usuario;
    private final Banco banco = Banco.getInstancia();
    private final ServiceControlador serviceControlador = ServiceControlador.getInstancia();

    @Override
    public void initialize(Usuario usuario){
        this.usuario = usuario;
        ObservableList<CategoriaTransaccion> opciones = FXCollections.observableArrayList(CategoriaTransaccion.values());
        selectCat.setItems(opciones);
    }

    /**
     * Metodo para realizar la trasferencia de cuenta a cuenta
     * @param actionEvent Evento que representa el clic del botón
     */
    public void realizarTransferencia(ActionEvent actionEvent){
        try{
            float monto = Float.parseFloat(txtMonto.getText());

            if(selectCat.getSelectionModel().getSelectedItem()!= null){
                CuentaAhorros cuentaOrigen = banco.obtenerCuentaAhorros(usuario);
                CuentaAhorros cuentaDestino = banco.obtenerCuentaAhorros(txtNumCuenta.getText());
                if (cuentaOrigen != null && cuentaDestino != null) {
                    banco.realizarTransferencia(cuentaOrigen.getNumeroCuenta(), cuentaDestino.getNumeroCuenta(), monto, selectCat.getSelectionModel().getSelectedItem());
                    serviceControlador.cerrarVentana(btnTransferir);
                    serviceControlador.navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente",usuario);
                }else {
                    throw new Exception("La cuenta no existe");
                }
            }else {
                throw new Exception("Ingresa la categoria");
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
        serviceControlador.cerrarVentana(btnTransferir);
        serviceControlador.navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente", usuario);
    }

}
