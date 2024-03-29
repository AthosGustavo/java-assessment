package br.novo.Controller;

import br.novo.Service.ClienteService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClienteController {

  private ClienteService clienteService;

  public ClienteController(){
    this.clienteService = new ClienteService();
  }

  public Route exibirClienteController = (Request req, Response res) -> clienteService.exibirClienteService();
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
