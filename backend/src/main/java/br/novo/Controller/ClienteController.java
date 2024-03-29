package br.novo.Controller;

import br.novo.Model.Cliente;
import br.novo.Service.ClienteService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ClienteController {

  private ClienteService clienteService;

  public ClienteController(){
    this.clienteService = new ClienteService();
  }

  public Route exibirClienteController = (Request req, Response res) -> {
    try{
      List<Cliente> clientes = clienteService.exibirClienteService();
      res.type("application/json");
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      return gson.toJson(clientes);
    }catch(Exception ex){
      ex.printStackTrace();
      return null;
    }
  };
  public Route cadastraClienteController = (Request req, Response res) -> clienteService.cadastraClienteService(req);
  public Route alugaLivroController = (Request req, Response res) -> clienteService.alugaLivroService(req);

  public Route removeClienteController = (Request req, Response res) -> {
    Long id = Long.parseLong(req.params(":id"));
    return clienteService.removeClienteService(id);
  };

  public Route editaClienteController = (Request req, Response res) -> {
    Long id = Long.parseLong(req.params(":id"));
    return clienteService.editaClienteService(id, req);
  };
}
