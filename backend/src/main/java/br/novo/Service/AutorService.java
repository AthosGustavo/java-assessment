package br.novo.Service;

import br.novo.Model.Autor;
import br.novo.Model.Livro;
import br.novo.Repository.AutorRepository;
import br.novo.Repository.LivroRepository;
import org.json.JSONObject;
import spark.Request;

import java.util.List;

public class AutorService {

  private AutorRepository autorRepository;

  private LivroRepository livroRepository;

  public AutorService(){
    this.autorRepository = new AutorRepository();
    this.livroRepository = new LivroRepository();
  }

  public AutorService(AutorRepository autorRepository){
    this.autorRepository = new AutorRepository();
  }

  public List<Autor> exibirAutorService(){
    return this.autorRepository.exibirAutores();
  }

  public String cadastraAutorService(Request req){
    String request = req.body();
    JSONObject responseObject = new JSONObject(request);

    Autor autor = new Autor(responseObject.getJSONObject("autor").getString("nome"), responseObject.getJSONObject("autor").getBoolean("contratoAtivo"));
    autorRepository.save(autor);
    return "Autor adicionado com sucesso!";
  }

  public String cadastraLivroParaAutorExistenteService(Request req){
    String request = req.body();
    JSONObject responseObject = new JSONObject(request);

    Long idAutor = responseObject.getLong("idAutor");
    Autor autor = autorRepository.getAutor(idAutor);
    if (autor == null) {
      return "Autor não encontrado";
    }

    Livro livro = new Livro(responseObject.getJSONObject("livro").getString("nome"), responseObject.getJSONObject("livro").getString("genero"), responseObject.getJSONObject("livro").getInt("paginas"),responseObject.getJSONObject("livro").getBoolean("estaEmprestado"));
    livro.setAutor(autor);
    //autor.setLivro(livro);
    this.livroRepository.save(livro);
    //autorRepository.save(autor);
    return "Livro adicionado ao autor";
  }

  public String editarAutorService(String id, Request req){
    String request = req.body();
    JSONObject responseObject = new JSONObject(request);

    Long idAutor = Long.parseLong(id);
    Autor autor = autorRepository.getAutor(idAutor);
    if (autor == null) {
      return "Autor não encontrado";
    }

    autor.setNome(responseObject.getString("nome"));
    autor.setContratoAtivo(responseObject.getBoolean("contratoAtivo"));
    autorRepository.saveEdit(autor);
    return "Autor atualizado";
  }

  public Long removerAutorService(Request req){
    Long id = Long.valueOf(req.params("id"));
    autorRepository.removerAutor(id);
    return id;
  }
}
