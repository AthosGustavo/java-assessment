package ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.novo.Controller.ClienteController;
import br.novo.Service.ClienteService;
import br.novo.Model.Cliente;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;

public class ClienteControllerTest {

  @Mock
  private ClienteService clienteService;

  @Mock
  private Request request;

  @Mock
  private Response response;

  private ClienteController clienteController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    clienteController = new ClienteController(clienteService);
  }

  @Test
  public void testExibirClienteController() throws Exception {
    Cliente cliente1 = new Cliente();
    Cliente cliente2 = new Cliente();
    List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
    when(clienteService.exibirClienteService()).thenReturn(clientes);

    Object result = clienteController.exibirClienteController.handle(request, response);

    verify(clienteService, times(1)).exibirClienteService();
    assertEquals(new Gson().toJson(clientes), result);
  }

  @Test
  public void testCadastraClienteController() throws Exception {
    when(clienteService.cadastraClienteService(any(Request.class))).thenReturn("Expected Result");

    Object result = clienteController.cadastraClienteController.handle(request, response);

    verify(clienteService, times(1)).cadastraClienteService(any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testAlugaLivroController() throws Exception {
    when(clienteService.alugaLivroService(any(Request.class))).thenReturn("Expected Result");

    Object result = clienteController.alugaLivroController.handle(request, response);

    verify(clienteService, times(1)).alugaLivroService(any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testRemoveClienteController() throws Exception {
    Long id = 1L;
    when(request.params(":id")).thenReturn(id.toString());
    when(clienteService.removeClienteService(id)).thenReturn("Expected Result");

    Object result = clienteController.removeClienteController.handle(request, response);

    verify(clienteService, times(1)).removeClienteService(id);
    assertEquals("Expected Result", result);
  }

  @Test
  public void testEditaClienteController() throws Exception {
    Long id = 1L;
    when(request.params(":id")).thenReturn(id.toString());
    when(clienteService.editaClienteService(eq(id), any(Request.class))).thenReturn("Expected Result");

    Object result = clienteController.editaClienteController.handle(request, response);

    verify(clienteService, times(1)).editaClienteService(eq(id), any(Request.class));
    assertEquals("Expected Result", result);
  }

}