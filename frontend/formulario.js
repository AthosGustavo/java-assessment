
document.getElementById('livroForm').addEventListener('submit', function(event) {
  event.preventDefault();

  let titulo = document.getElementById('titulo').value;
  let genero = document.getElementById('genero').value;
  let paginas = document.getElementById('paginas').value;
  let nomeAutor = document.getElementById('nomeAutor').value;
  let contratoAtivo = document.getElementById('contratoAtivo').value === 'true';

  let data = {
    livro: {
      nome: titulo,
      genero: genero,
      paginas: paginas
    },
    autor: {
      nome: nomeAutor,
      contratoAtivo: contratoAtivo
    }
  };

  fetch('http://localhost:8080/cadastro/livro', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(response => {
    if (response.ok) {
      alert('Livro cadastrado com sucesso!');
      //console.log(data);
    } else {
      alert('Erro ao cadastrar o livro');
    }
  }).catch(error => console.error('Erro:', error));
});