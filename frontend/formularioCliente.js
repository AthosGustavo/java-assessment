
document.getElementById('clienteForm').addEventListener('submit', function(event) {
  event.preventDefault();

  let nome = document.getElementById('nomeCliente').value;
  let rg = document.getElementById('rgCliente').value;
  let cpf = document.getElementById('cpfCliente').value;
  let endereco = document.getElementById('enderecoCliente').value;
 
  

  
  
  let data = {
    cliente: {
      nome: nome,
      rg: rg,
      cpf: cpf,
      endereco: endereco
    }
  };

  //console.log(data.autor.contratoAtivo)

  fetch('http://localhost:8080/cadastro/cliente', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(response => {
    if (response.ok) {
      alert('Cliente cadastrado. Atualize a página para ver as alterações.');
      //console.log(data);
    } else {
      alert('Erro ao cadastrar o livro');
    }
  }).catch(error => console.error('Erro:', error));
});