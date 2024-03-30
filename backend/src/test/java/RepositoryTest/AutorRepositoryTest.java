package RepositoryTest;

import br.novo.Model.Autor;
import br.novo.Repository.AutorRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AutorRepositoryTest {

  @Mock
  private Session session;
  @Mock
  private Query<Autor> query;
  @InjectMocks
  private AutorRepository autorRepository;

  @Test
  public void testExibirAutores() {
    // Arrange
    Autor autor1 = new Autor();
    Autor autor2 = new Autor();
    List<Autor> expectedAutores = Arrays.asList(autor1, autor2);
    when(session.createQuery("FROM Autor", Autor.class)).thenReturn(query);
    when(query.list()).thenReturn(expectedAutores);

    // Act
    List<Autor> actualAutores = autorRepository.exibirAutores();

    // Assert
    verify(session, times(1)).createQuery("FROM Autor", Autor.class);
    assertEquals(expectedAutores, actualAutores);
  }
}