
document.getElementById('autorForm').addEventListener('submit', function(event) {
  event.preventDefault();

  let nome = document.getElementById('titulo').value;
  let contratoAtivo = document.getElementById('contratoAtivo').value === 'true';
  console.log(contratoAtivo);
  

  
  
  let data = {
    autor: {
      nome: nome,
      contratoAtivo: contratoAtivo
    }
  };

  console.log(data.autor.contratoAtivo)

  fetch('http://localhost:8080/cadastro/autor', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(response => {
    if (response.ok) {
      alert('Livro cadastrado. Atualize a página para ver as alterações.');
      //console.log(data);
    } else {
      alert('Erro ao cadastrar o livro');
    }
  }).catch(error => console.error('Erro:', error));
});

document.getElementById('autorComLivro').addEventListener('submit', function(event){
  event.preventDefault();

  let idAutor = document.getElementById('idAutor').value;
  let tituloLivro = document.getElementById('tituloLivro').value;
  let generoLivro = document.getElementById('generoLivro').value;
  let paginasLivro = document.getElementById('paginasLivro').value;
  let estaEmprestado = document.getElementById('estaEmprestado').value;

  let data = {
    idAutor: idAutor,
    livro: {
      nome: tituloLivro,
      genero: generoLivro,
      paginas: paginasLivro,
      estaEmprestado: estaEmprestado
    }
  }

  fetch('http://localhost:8080/cadastro/livro/autor', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(response => {
    if (response.ok) {
      alert('Livro cadastrado. Atualize a página para ver as alterações.');
      //console.log(data);
    } else {
      alert('Erro ao cadastrar o livro');
    }
  }).catch(error => console.error('Erro:', error));

})