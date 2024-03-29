package br.novo.Model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Expose
  private Long id;
  @ManyToOne
  @JoinColumn(name = "livro_id", referencedColumnName = "id")
  @Expose
  private Livro livros;

  public Cliente(String nome, String rg, String cpf, String endereco) {
    super(nome, rg, cpf, endereco);
  }

  public Cliente(){
    super();
  }

  public Long getId() {
    return id;
  }

  public Livro getLivros() {
    return livros;
  }

  public void setLivros(Livro livros) {
    this.livros = livros;
  }
  @Override
  public String toString() {
    return super.toString() +
      "Cliente{" +
      "id=" + id +
      ", livros=" + livros +
      '}';
  }

}
