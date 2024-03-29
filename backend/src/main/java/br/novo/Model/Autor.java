package br.novo.Model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Expose
  private Long id;
  @Expose
  private String nome;
  @Expose
  private boolean contratoAtivo;
  @OneToOne(mappedBy = "autor")
  private Livro livro;
  public Autor(String nomeAutor) {
    this.nome = nomeAutor;
  }
  public Autor(){}

  public Autor(String nome, boolean contratoAtivo, Livro livro) {
    this.nome = nome;
    this.contratoAtivo = contratoAtivo;
    this.livro = livro;

  }

  public Autor(String nome, boolean contratoAtivo) {
    this.nome = nome;
    this.contratoAtivo = contratoAtivo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public boolean isContratoAtivo() {
    return contratoAtivo;
  }

  public void setContratoAtivo(boolean contratoAtivo) {
    this.contratoAtivo = contratoAtivo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Autor{" +
      "id=" + id +
      ", nome='" + nome + '\'' +
      ", contratoAtivo=" + contratoAtivo +
      ", livro=" + livro +
      '}';
  }
}
