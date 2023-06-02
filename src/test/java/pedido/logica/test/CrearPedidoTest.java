package pedido.logica.test;
import com.example.demo.GuardadoBaseDeDatos.CrearPedido;
import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.Restaurante.Menu;
import com.example.demo.User.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrearPedidoTest {
    private CrearPedido crearPedido;
    private Menu menu;
    private Cliente cliente;

    @BeforeEach
    public void setUp() throws SQLException {
        crearPedido = new CrearPedido();
    }

    @Test
    void guardarPedidoInvalido() throws SQLException {
        int idPedidoInvalido = -1;
        menu = new Menu("nombre", 12000, 1);
        cliente = new Cliente();
        cliente.setNombre("Nicolás");
        cliente.setAdminCliente(1);
        String cadenaRespuesta = "Error al guardar el pedido en la base de datos: ";
        Assertions.assertEquals(crearPedido.guardarPedido(idPedidoInvalido, cliente, menu), cadenaRespuesta);
    }


}