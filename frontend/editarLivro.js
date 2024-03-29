// Obter o modal
var modal = document.getElementById("editModal");

// Obter o botão que abre o modal
var btn = document.getElementsByClassName("edit-btn");

// Obter o elemento <span> que fecha o modal
var span = document.getElementsByClassName("close")[0];

// Quando o usuário clica no botão, abre o modal
for (var i = 0; i < btn.length; i++) {
  btn[i].onclick = function() {
    modal.style.display = "block";
  }
}

// Quando o usuário clica em <span> (x), fecha o modal
span.onclick = function() {
  modal.style.display = "none";
}

// Quando o usuário clica em qualquer lugar fora do modal, fecha o modal
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

// Quando o formulário é submetido, envia uma solicitação PUT para o servidor
document.getElementById('editForm').addEventListener('submit', function(event) {
  event.preventDefault();

  let nome = document.getElementById('editNome').value;
  let genero = document.getElementById('editGenero').value;
  let paginas = document.getElementById('editPaginas').value;

  let data = {
    nome: nome,
    genero: genero,
    paginas: paginas
  };

  fetch('/editar/livro/id', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(response => {
    if (response.ok) {
      alert('Livro atualizado com sucesso!');
      modal.style.display = "none";
      // Atualize o registro da tabela aqui
    } else {
      alert('Erro ao atualizar o livro');
    }
  }).catch(error => console.error('Erro:', error));
});