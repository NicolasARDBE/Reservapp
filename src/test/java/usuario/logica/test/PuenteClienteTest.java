package usuario.logica.test;
import com.example.demo.GuardadoBaseDeDatos.PuenteAdmin;
import com.example.demo.GuardadoBaseDeDatos.PuenteCliente;
import com.example.demo.User.Administrador;
import com.example.demo.User.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuenteClienteTest {
    private PuenteCliente puenteCliente;
    private PuenteAdmin puenteAdmin;
    private Cliente cliente;
    private Administrador admin;

    @BeforeEach
    public void setUp() throws SQLException {
        puenteCliente = new PuenteCliente();
        puenteAdmin = new PuenteAdmin();
    }


    @Test
    void guardarClienteVacio() throws SQLException {
        cliente = null;
        assertThrows(NullPointerException.class, () -> puenteCliente.guardarCliente(cliente));
    }
    @Test
    void guardarClienteRepetido() throws SQLException {
        cliente = new Cliente();
        cliente.setNombre("Nicolás");
        cliente.setAdminCliente(1);
        cliente.setContrasena("123");
        assertFalse(puenteCliente.guardarCliente(cliente));
    }
    @Test
    void retornarSaldoUsuarioSinTarjeta(){
        cliente = new Cliente();
        cliente.setNombre("test");
        cliente.setAdminCliente(1);
        Assertions.assertEquals(puenteCliente.retornarSaldo(cliente.getNombre(), cliente.getAdminCliente()), 0.0);
    }

    @Test
    void dejarEnNegativos(){
        cliente = new Cliente();
        cliente.setNombre("Nicolás");
        cliente.setContrasena("123");
        cliente.setAdminCliente(1);
        double precio = 1000000000;
        Assertions.assertFalse(puenteCliente.quitarsaldo(precio, cliente.getNombre(),
                cliente.getContrasena(), String.valueOf(cliente.getAdminCliente())));
    }

    @Test
    void guardarAdminVacio() throws SQLException {
        admin = null;
        assertThrows(NullPointerException.class, () -> puenteAdmin.guardarAdministrador(admin));
    }
    @Test
    void testRetornarPreciomenu() {
        int idMenuExistente = 1;
        double precioExistente = puenteCliente.retornarPreciomenu(idMenuExistente);
        Assertions.assertNotEquals(0.0, precioExistente, "El precio del menú no debe ser cero");

        int idMenuNoExistente = 999;
        double precioNoExistente = puenteCliente.retornarPreciomenu(idMenuNoExistente);
        Assertions.assertEquals(0.0, precioNoExistente, "El precio del menú no existente debe ser cero");
    }
    @Test
    void testGuardarCliente() throws SQLException {
        Cliente cliente = new Cliente("Pepitoioi", "123456");
        cliente.setAdminCliente(1);
        boolean result = puenteCliente.guardarCliente(cliente);

        Assertions.assertTrue(result, "No se pudo guardar el cliente en la base de datos");
    }

    @Test
    void retornarSaldo() throws SQLException {
        String nombre = "blabla";
        int adminClienteExistente = 1;
        double saldonoExistente = puenteCliente.retornarSaldo(nombre,adminClienteExistente);

        Assertions.assertEquals(0.0,saldonoExistente, "El saldo deberia ser 0");
    }



}
