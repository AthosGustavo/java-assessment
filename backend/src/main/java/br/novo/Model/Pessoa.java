package br.novo.Model;

import com.google.gson.annotations.Expose;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoa {

  public Pessoa(String nome, String rg, String cpf, String endereco) {
    this.nome = nome;
    this.rg = rg;
    this.cpf = cpf;
    this.endereco = endereco;
  }

  public Pessoa(){}

  @Expose
  private String nome;
  @Expose
  private String rg;
  @Expose
  private String cpf;
  @Expose
  private String endereco;
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  @Override
  public String toString() {
    return "Pessoa{" +
      "nome='" + nome + '\'' +
      ", rg='" + rg + '\'' +
      ", cpf='" + cpf + '\'' +
      ", endereco='" + endereco + '\'' +
      '}';
  }
}
