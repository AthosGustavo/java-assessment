package br.novo.Model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
public class Livro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Expose
  private Long id;
  @Expose
  private String nome;
  @Expose
  private String genero;
  @Expose
  private int paginas;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="autor_id", referencedColumnName = "id")
  @Expose
  private Autor autor;
  @OneToOne(mappedBy = "livros")
  private Cliente cliente;
  @Expose
  private boolean estaEmprestado;

  public Livro(String nome, String genero, int paginas, Autor autor) {
    this.nome = nome;
    this.genero = genero;
    this.paginas = paginas;
    this.autor = autor;
  }

  public Livro(){}

  public Livro(String nome, String genero, int paginas, boolean estaEmprestado) {
    this.nome = nome;
    this.genero = genero;
    this.paginas = paginas;
    this.estaEmprestado = estaEmprestado;
  }

  public boolean estarDisponivel() {
    return !estaEmprestado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public int getPaginas() {
    return paginas;
  }

  public void setPaginas(int paginas) {
    this.paginas = paginas;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public boolean isEstaEmprestado() {
    return estaEmprestado;
  }

  public void setEstaEmprestado(boolean estaEmprestado) {
    this.estaEmprestado = estaEmprestado;
  }

  //O método toString é essencial para retorna a chave e os valores de um objeto.
}
