package br.novo.Controller;

import br.novo.Model.Autor;
import br.novo.Service.AutorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class AutorController {

  private AutorService autorService;
  private Gson gson = new Gson();

  public AutorController(){
    autorService = new AutorService();
  }

  public AutorController(AutorService autorService){
    this.autorService = autorService;
  }

  public Route cadastraAutorController = (Request req, Response res) -> autorService.cadastraAutorService(req);

  public Route cadastraLivroParaAutorExistenteController = (Request req, Response res) -> {
    return autorService.cadastraLivroParaAutorExistenteService(req);
  };
  public Route exibirAutorController = (Request req, Response res) -> {
    try{
      List<Autor> autores = autorService.exibirAutorService();
      res.type("application/json");
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      return gson.toJson(autores);
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
  };

  public Route editarAutorController = (Request req, Response res) -> {
    String id = req.params(":id");
    return autorService.editarAutorService(id, req);
  };

  public Route removerAutorController = (Request req, Response res) -> autorService.removerAutorService(req);



}
