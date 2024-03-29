package br.novo;

import br.novo.Controller.AutorController;
import br.novo.Controller.ClienteController;
import br.novo.Controller.LivroController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spark.Spark;

import static spark.Spark.before;

public class Main {
  public static void main(String[] args) {

    Spark.port(8080);
    before((request, response) -> {
      response.header("Access-Control-Allow-Origin", "*");
      response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
      response.header("Access-Control-Allow-Headers", "*");
      response.header("Access-Control-Allow-Credentials", "true");
    });

    LivroController livroController = new LivroController();
    AutorController autorController = new AutorController();
    ClienteController clienteController = new ClienteController();


    //contratoAtivo sem valor

    //Livro
    Spark.post("/cadastro/livro", livroController.cadastraLivroController);
    Spark.get("/livro", livroController.exibirLivroController);
    Spark.delete("/remove/livro/:idLivro", livroController.removerLivroController);
    Spark.put("/editar/livro/:id", livroController.editaLivroController);

    //Autor
    Spark.get("/autor", autorController.exibirAutorController);
    Spark.post("/cadastro/autor", autorController.cadastraAutorController);
    Spark.post("/cadastro/livro/autor", autorController.cadastraLivroParaAutorExistenteController);
    Spark.put("/editar/autor/:id", autorController.editarAutorController);
    Spark.delete("/excluir/autor/:id", autorController.removerAutorController);

    //Cliente
    Spark.get("/cliente", clienteController.exibirClienteController);
    Spark.post("/cadastro/cliente", clienteController.cadastraClienteController);
    Spark.post("/aluguel/livro", clienteController.alugaLivroController);
    Spark.delete("/excluir/cliente/:id", clienteController.removeClienteController);
    Spark.put("/editar/cliente/:id", clienteController.editaClienteController);
    
    //Fazer um método para cadastrar autor sem livro


  }
}