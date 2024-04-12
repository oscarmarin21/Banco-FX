package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.controlador.service.ServiceControlador;
import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.Transaccion;
import co.edu.uniquindio.banco.modelo.Usuario;
import co.edu.uniquindio.banco.modelo.enums.CategoriaTransaccion;
import co.edu.uniquindio.banco.modelo.enums.TipoTransaccion;
import co.edu.uniquindio.banco.modelo.interfaces.ControladorConUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;
import javafx.collections.FXCollections;
import  javafx.collections.ObservableList;
import java.time.LocalDate;

@Getter
@Setter
public class PanelClienteControlador implements ControladorConUsuario {

    @FXML
    private Label labelBienvenido;
    @FXML
    private Label labelNumCuenta;
    @FXML
    private Button btnConsulta;
    @FXML
    private TableView<Transaccion> tableTransaccion;
    @FXML
    private TableColumn<Transaccion, TipoTransaccion> colTipo;
    @FXML
    private TableColumn<Transaccion, LocalDate> colFecha;
    @FXML
    private TableColumn<Transaccion, Float> colValor;
    @FXML
    private TableColumn<Transaccion, String> colUsuario;
    @FXML
    private TableColumn<Transaccion, CategoriaTransaccion> colCategoria;

    private Usuario usuario;

    private final Banco banco = Banco.getInstancia();
    private final ServiceControlador serviceControlador = ServiceControlador.getInstancia();

    @Override
    public void initialize(Usuario usuario){
        this.usuario = usuario;
        labelBienvenido.setText(String.format("%s Bienvenido a su banco, aqui podra ver sus transacciones", this.usuario.getNombre()));
        labelNumCuenta.setText(String.format("Nro.Cuenta: %s", banco.obtenerCuentaAhorros(this.usuario).getNumeroCuenta()));
        ObservableList<Transaccion> data = FXCollections.observableArrayList(banco.obtenerCuentaAhorros(this.usuario).getTransacciones());
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        tableTransaccion.setItems(data);
    }

    /**
     * Método que se ejecuta al presionar el botón de cerrar sesion
     * @param actionEvent Evento que representa el clic del botón
     */
    public void logout(ActionEvent actionEvent) {
        serviceControlador.cerrarVentana(btnConsulta);
        serviceControlador.navegarVentana("/inicio.fxml", "Banco - Inicio");
    }

    /**
     * Metodo para consulatr el saldo de la cuenta
     * @param actionEvent Evento que representa el clic del botón
     */
    public void consultarSaldo(ActionEvent actionEvent){
        float saldo = banco.ConsultarSaldo(banco.obtenerCuentaAhorros(usuario).getNumeroCuenta());
        serviceControlador.crearAlerta(String.format("El saldo de la cuenta es %s",saldo), Alert.AlertType.INFORMATION);
    }

    /**
     * Metodo para navegar hacia la pantalla de transferencia
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irVentanaTransferencia(ActionEvent actionEvent){
        serviceControlador.cerrarVentana(btnConsulta);
        serviceControlador.navegarVentana("/transferencia.fxml", "Banco - Transferencia", usuario);
    }

    /**
     * Metodo para navegar hacia la pantalla de editar el usuario
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irEditarUsuario(ActionEvent actionEvent){
        serviceControlador.cerrarVentana(btnConsulta);
        serviceControlador.navegarVentana("/editUsuario.fxml", "Banco - Usuaurio", usuario);
    }
}
