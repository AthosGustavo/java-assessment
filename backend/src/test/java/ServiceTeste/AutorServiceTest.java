package ServiceTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.novo.Service.AutorService;
import br.novo.Repository.AutorRepository;
import br.novo.Model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Request;

import java.util.Arrays;
import java.util.List;

public class AutorServiceTest {

  @Mock
  private AutorRepository autorRepository;
  @Mock
  private Request request;

  @Captor
  private ArgumentCaptor<Autor> autorCaptor;

  private AutorService autorService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    autorService = new AutorService(autorRepository);
  }

  @Test
  public void testExibirAutorService() {
    Autor autor1 = new Autor();
    Autor autor2 = new Autor();
    List<Autor> autores = Arrays.asList(autor1, autor2);
    when(autorRepository.exibirAutores()).thenReturn(autores);

    List<Autor> result = autorService.exibirAutorService();

    verify(autorRepository, times(1)).exibirAutores();
    assertEquals(autores, result);
  }

  @Test
  public void testCadastraAutorService() {
    String nome = "Nome do Autor";
    boolean contratoAtivo = true;
    Autor autor = new Autor(nome, contratoAtivo);
    when(request.body()).thenReturn("{\"autor\":{\"nome\":\"" + nome + "\",\"contratoAtivo\":" + contratoAtivo + "}}");

    String result = autorService.cadastraAutorService(request);

    verify(autorRepository, times(1)).save(autorCaptor.capture());
    Autor savedAutor = autorCaptor.getValue();
    assertEquals(autor.getNome(), savedAutor.getNome());
    assertEquals(autor.isContratoAtivo(), savedAutor.isContratoAtivo());
    assertEquals("Autor adicionado com sucesso!", result);
  }
}
