package br.novo.Controller;

import br.novo.Model.Livro;
import br.novo.Service.LivroService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class LivroController {


  private LivroService livroService;
  private Gson gson = new Gson();

  public LivroController(){
    livroService = new LivroService();
  }

  public LivroController(LivroService livroService){
    this.livroService = livroService;
  }

  public Route cadastraLivroController = (Request req, Response res ) -> livroService.cadastraLivroService(req);
  public Route exibirLivroController = (Request req, Response res) -> {
    try {
      List<Livro> livros = livroService.exibirLivroService();
      res.type("application/json");
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      return gson.toJson(livros);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  };

  public Route removerLivroController = (Request req, Response res) -> livroService.removeLivroService(req);

  public Route editaLivroController = (Request req, Response res) -> {
    Long id = Long.parseLong(req.params(":id"));
    return livroService.editaLivroService(id, req);
  };
}
