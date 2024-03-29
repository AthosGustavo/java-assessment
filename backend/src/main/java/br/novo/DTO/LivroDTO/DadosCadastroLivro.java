package br.novo.DTO.LivroDTO;

import br.novo.Model.Autor;

public record DadosCadastroLivro(String nome, String genero, int paginas, Autor autor) {
}
