package ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.novo.Controller.AutorController;
import br.novo.Model.Autor;
import br.novo.Service.AutorService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;

public class AutorControllerTest {

  @Mock
  private AutorService autorService;

  @Mock
  private Request request;

  @Mock
  private Response response;

  private AutorController autorController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    autorController = new AutorController(autorService);
  }

  @Test
  public void testCadastraAutorController() throws Exception {
    when(autorService.cadastraAutorService(any(Request.class))).thenReturn("Expected Result");

    Object result = autorController.cadastraAutorController.handle(request, response);

    verify(autorService, times(1)).cadastraAutorService(any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testCadastraLivroParaAutorExistenteController() throws Exception {
    when(autorService.cadastraLivroParaAutorExistenteService(any(Request.class))).thenReturn("Expected Result");

    Object result = autorController.cadastraLivroParaAutorExistenteController.handle(request, response);

    verify(autorService, times(1)).cadastraLivroParaAutorExistenteService(any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testExibirAutorController() throws Exception {
    Autor autor1 = new Autor();
    Autor autor2 = new Autor();
    List<Autor> autores = Arrays.asList(autor1, autor2);
    when(autorService.exibirAutorService()).thenReturn(autores);

    Object result = autorController.exibirAutorController.handle(request, response);

    verify(autorService, times(1)).exibirAutorService();
    assertEquals(new Gson().toJson(autores), result);
  }

  @Test
  public void testEditarAutorController() throws Exception {
    String id = "1";
    when(request.params(":id")).thenReturn(id);
    when(autorService.editarAutorService(eq(id), any(Request.class))).thenReturn("Expected Result");

    Object result = autorController.editarAutorController.handle(request, response);

    verify(autorService, times(1)).editarAutorService(eq(id), any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testRemoverAutorController() throws Exception {
    Long expectedResult = 1L;
    when(autorService.removerAutorService(any(Request.class))).thenReturn(expectedResult);

    Object result = autorController.removerAutorController.handle(request, response);

    verify(autorService, times(1)).removerAutorService(any(Request.class));
    assertEquals(expectedResult, result);
  }
}
