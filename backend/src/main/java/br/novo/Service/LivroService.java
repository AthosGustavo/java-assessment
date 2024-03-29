package br.novo.Service;

import br.novo.Model.Autor;
import br.novo.Model.Livro;
import br.novo.Repository.LivroRepository;
import org.json.JSONObject;
import spark.Request;

import java.util.List;

public class LivroService {

  private LivroRepository livroRepository;
  public LivroService(){
    this.livroRepository = new LivroRepository();
  }
  public String cadastraLivroService(Request req){
    String request = req.body();
    JSONObject responseObject = new JSONObject(request);
    String nomeAutor = responseObject.getJSONObject("autor").getString("nome");
    boolean contratoAtivo = responseObject.getJSONObject("autor").getBoolean("contratoAtivo");
    JSONObject livroJson = responseObject.getJSONObject("livro");
    Autor autor = new Autor(nomeAutor, contratoAtivo);
    Livro livro = new Livro(livroJson.getString("nome"), livroJson.getString("genero"),livroJson.getInt("paginas"), autor);
    livroRepository.save(livro);
    return "Livro adicionado";
  }

  public List<Livro> exibirLivroService(){
    return livroRepository.exibirLivros();
  }

  public Long removeLivroService(Request req){
    Long idLivro = Long.valueOf(req.params(":idLivro"));
    livroRepository.removerLivro(idLivro);
    return idLivro;
  }

  public String editaLivroService(Long id, Request req){
    String request = req.body();
    JSONObject responseObject = new JSONObject(request);
    //System.out.println(responseObject + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    Livro livro = livroRepository.getLivro(id);

    if(livro != null){

      if(!responseObject.getString("nome").isEmpty()){
        livro.setNome(responseObject.getString("nome"));
      }

      if(!responseObject.getString("genero").isEmpty()){
        livro.setGenero(responseObject.getString("genero"));
      }

      if(responseObject.getInt("paginas") > 0){
        livro.setPaginas(responseObject.getInt("paginas"));
      }
      livroRepository.editaLivro(livro);
      return "Livro editado com sucesso!";
    } else {
      return "Livro não encontrado";
    }
  }


}
