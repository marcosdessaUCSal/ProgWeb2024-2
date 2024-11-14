// =================================================
//      DEFINIÇÕES DE CLASSES E VARIÁVEIS GLOBAIS
// =================================================

var idCount = 0;

class Tarefa {
    constructor(id, txt, sel) {
        this.id = id;
        this.marcado = sel;
        this.texto = txt;
    }

}

var arrayTarefas = new Array();



// =================================================
//      INICIALIZAÇÃO DOS DADOS
// =================================================

function exibeTarefas() {
    fetch('http://localhost:8080/tarefas', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            arrayTarefas.length = 0;
            data.forEach(item => {
                arrayTarefas.push(new Tarefa(item.id, item.txt, item.marcado));
            });
            mostraNaTela();
        })
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );


}




// =================================================
//      CONSTRUÇÃO DE ELEMENTOS GRÁFICOS
// =================================================

function mostraNaTela() {
    let area = document.getElementById('tarefas');
    let conteudo = '';
    let registroAtual = '';
    let txt = '';
    let selecionado = false;
    let id = 0;
    for (let t of arrayTarefas) {
        id = t.id;
        txt = t.texto;
        selecionado = t.marcado;
        registroAtual = `<div class="registro">`;
        if (!selecionado) {
            registroAtual += `<input type="checkbox" onchange="mude(${id})">`;
            registroAtual += `<div class="texto">${txt}</div>`;
        } else {
            registroAtual += `<input type="checkbox" checked onchange="mude(${id})">`;
            registroAtual += `<div class="texto-riscado">${txt}</div>`;
        }
        registroAtual += `<div><img class="icone" src="/assets/img/delete-icon.svg" onclick="remova(${id})"></div>`;
        registroAtual += `</div>`;
        conteudo += registroAtual;
    }
    area.innerHTML = conteudo;
}


// =================================================
//      ALTERAÇÕES NOS DADOS
// =================================================


function mude(id) {
    fetch(`http://localhost:8080/inverterMarcado/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            exibeTarefas();
        })
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}



function remova(id) {
    fetch(`http://localhost:8080/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            exibeTarefas();
        })
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}

function adicionarTarefa() {
    let txt = document.getElementById('entrada').value;
    // arrayTarefas.push(new Tarefa(id, txt, false));
    document.getElementById('entrada').value = '';
    exibeTarefas();
    fetch(`http://localhost:8080/add`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: null,
            txt: txt,
            marcado: 0
        })
    })
        .then(() => {
            exibeTarefas();
        })
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}

function marcarTudo() {
    fetch('http://localhost:8080/marcaTudo', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => exibeTarefas())
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}

function desmarcarTudo() {
    fetch('http://localhost:8080/desmarcaTudo', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => exibeTarefas())
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}

function removerMarcados() {
    fetch('http://localhost:8080/removeMarcados', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => exibeTarefas())
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}

function reset() {
    fetch('http://localhost:8080/reset', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => exibeTarefas())
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}














// =================================================
//      INICIANDO TUDO
// =================================================

// start();
exibeTarefas();

