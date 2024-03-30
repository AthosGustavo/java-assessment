package ControllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import br.novo.Controller.LivroController;
import br.novo.Service.LivroService;
import br.novo.Model.Livro;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;

public class LivroControllerTest {
  @Mock
  private LivroService livroService;

  @Mock
  private Request request;

  @Mock
  private Response response;

  private LivroController livroController;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    livroController = new LivroController(livroService);
  }

  @Test
  public void testEditaLivroController() throws Exception {
    Long id = 1L;
    when(request.params(":id")).thenReturn(id.toString());
    when(livroService.editaLivroService(eq(id), any(Request.class))).thenReturn("Expected Result");

    Object result = livroController.editaLivroController.handle(request, response);

    verify(livroService, times(1)).editaLivroService(eq(id), any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testCadastraLivroController() throws Exception {
    when(livroService.cadastraLivroService(any(Request.class))).thenReturn("Expected Result");

    Object result = livroController.cadastraLivroController.handle(request, response);

    verify(livroService, times(1)).cadastraLivroService(any(Request.class));
    assertEquals("Expected Result", result);
  }

  @Test
  public void testExibirLivroController() throws Exception {
    Livro livro1 = new Livro();
    Livro livro2 = new Livro();
    List<Livro> livros = Arrays.asList(livro1, livro2);
    when(livroService.exibirLivroService()).thenReturn(livros);

    Object result = livroController.exibirLivroController.handle(request, response);

    verify(livroService, times(1)).exibirLivroService();
    assertEquals(new Gson().toJson(livros), result);
  }

  @Test
  public void testRemoveLivroController() throws Exception {
    Long expectedResult = 1L;
    when(livroService.removeLivroService(any(Request.class))).thenReturn(expectedResult);

    Object result = livroController.removerLivroController.handle(request, response);

    verify(livroService, times(1)).removeLivroService(any(Request.class));
    assertEquals(expectedResult, result);
  }
}