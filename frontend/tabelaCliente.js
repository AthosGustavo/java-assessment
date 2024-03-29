window.onload = function() {
  fetch('http://localhost:8080/cliente')
    .then(response => response.json())
    .then(data => {
      let table = document.createElement('table');
      let headerRow = document.createElement('tr');

      ['id','nome','rg','cpf','endereco'].forEach(headerText => {
        let header = document.createElement('th');
        let textNode = document.createTextNode(headerText);
        header.appendChild(textNode);
        headerRow.appendChild(header);
      });

      table.appendChild(headerRow);

      data.forEach(item => {
        

        let row = document.createElement('tr');

        let id = document.createElement('td');
        id.appendChild(document.createTextNode(item.id) || 'sem id');
        row.appendChild(id);
        
        let nome = document.createElement('td');
        nome.appendChild(document.createTextNode(item.nome || ''));
        row.appendChild(nome);

        let rg = document.createElement('td');
        rg.appendChild(document.createTextNode(item.rg || ''));
        row.appendChild(rg);

        let cpf = document.createElement('td');
        cpf.appendChild(document.createTextNode(item.cpf || ''));
        row.appendChild(cpf);

        let endereco = document.createElement('td');
        endereco.appendChild(document.createTextNode(item.endereco || ''));
        row.appendChild(endereco);

        let acoes = document.createElement('td');

        // Botão de edição
        let editButton = document.createElement('button');
        editButton.textContent = 'Editar';
        editButton.dataset.id = item.id;
        editButton.addEventListener('click', function() {
          let id = this.dataset.id;
      
          let modal = document.getElementById('editModal');
          let editForm = document.getElementById('editForm');

          // Preencha o formulário de edição com os dados atuais do livro
          document.getElementById('editNome').value = item.nome;
          document.getElementById('editRg').value = item.rg;
          document.getElementById('editCpf').value = item.cpf;
          document.getElementById('editEndereco').value = item.endereco;
          //document.getElementById('editPaginas').value = item.paginas;

          // Exiba o modal
          modal.style.display = 'block';
          let closeButton = document.querySelector('.close');
            closeButton.addEventListener('click', function() {
            modal.style.display = 'none';
          });

          editForm.addEventListener('submit', function(event) {

            event.preventDefault();

            let nome = document.getElementById('editNome').value;
            let rg = document.getElementById('editRg').value;
            let cpf = document.getElementById('editCpf').value;
            let endereco = document.getElementById('editEndereco').value;
            // let paginas = document.getElementById('editPaginas').value;
            let id = item.id;

            let data = {
              nome: nome,
              rg: rg,
              cpf: cpf,
              endereco: endereco,
              
            };
          
            fetch(`http://localhost:8080/editar/cliente/${id}`, { // Substitua :id pelo ID real do livro
              method: 'PUT',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(data)
            }).then(response => {
              if (response.ok) {
                alert('Cliente atualizado com sucesso, Atualize a página para ver as alterações!');
            
                let row = document.querySelector(`tr[data-id='${id}']`);
                row.dataset.id = id;
                row.cells[0].textContent = nome;
                row.cells[1].textContent = rg;
                row.cells[2].textContent = cpf;
                row.cells[3].textContent = endereco;
                modal.style.display = 'none';
              } else {
                alert('Erro ao atualizar o cliente');
                console.log(data);
              }
            }).catch(error => console.error('Erro:', error));

          });
        });
        acoes.appendChild(editButton);

        // Botão de exclusão
        let deleteButton = document.createElement('button');
        deleteButton.textContent = 'Excluir';
        deleteButton.addEventListener('click', function() {
          fetch(`http://localhost:8080/excluir/cliente/${item.id}`, {
            method: 'DELETE'
          }).then(response => {
            if (response.ok) {
              row.parentNode.removeChild(row);
            } else {
              console.error('Erro ao excluir o cliente');
            }
          }).catch(error => console.error('Erro:', error));
        });
        acoes.appendChild(deleteButton);

        row.appendChild(acoes);

        table.appendChild(row);
      });

      document.body.appendChild(table);
    })
    .catch(error => console.error('Error:', error));
};