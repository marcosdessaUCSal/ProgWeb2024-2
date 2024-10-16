// ==============================================================
// 		EVENTOS

// RESET
reset = function() {
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = function() {
		if (req.readyState == 4 && req.status == 200) {
			atualizaSessao();
			window.location.href = "/prova1";
//			expoeListaContatos();
		} else {
			// O QUE FAZER SE DEU ERRADO
		}
	}
	req.send("op=RESET");
}

// NOVA AULA
novaAula = function() {
	window.location.href = "nova";
}

// CANCELA NOVA AULA (OU EDIÇÃO)
calcelarNovaAula = function() {
	window.location.href = "/prova1";
}

// EDITA UMA AULA COM ID ESPECIFICADO
editarAula = function(id) {
	window.location.href = "edit?id=" + id;
}

// ENVIA CONTEÚDO DA NOVA AULA
enviarNovaAula = function() {
	// obtém os valores a partir do formulário
	let data = document.getElementById('data-id').value;
	let horario = document.getElementById('hora-id').value;
	let duracao = document.getElementById('dur-id').value;
	let codDisciplina = document.getElementById('disc-id').value;
	let assunto = document.getElementById('ass-id').value;
	// verificando a validação
	if (!validaNovaAula(data, horario, duracao, codDisciplina, assunto)) {
        document.getElementById('msg-id').style.display = 'block';
        return;
    }
	// enviando dados via POST com AJAX
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = function() {
		if (req.readyState == 4 && req.status == 200) {
			atualizaSessao();
			window.location.href = "/prova1";
		} else {
			// O QUE FAZER SE DEU ERRADO
		}
	}
	req.send("op=CREATE&data=" + data + "&horario=" + horario + "&duracao="
		+ duracao + "&codDisciplina=" + codDisciplina + "&assunto=" + assunto);
}

// ENVIA CONTEÚDO EM EDIÇÃO
enviarEdit = function() {
	// obtém os valores a partir do formulário
	let id = document.getElementById('id').innerHTML;
	let data = document.getElementById('data-id').value;
	let horario = document.getElementById('hora-id').value;
	let duracao = document.getElementById('dur-id').value;
	let codDisciplina = document.getElementById('disc-id').value;
	let assunto = document.getElementById('ass-id').value;
	// enviando dados via POST com AJAX
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = function() {
		if (req.readyState == 4 && req.status == 200) {
			atualizaSessao();
			window.location.href = "/prova1";
		} else {
			// O QUE FAZER SE DEU ERRADO
		}
	}
	req.send("op=UPDATE&id=" + id + "&data=" + data + "&horario=" + horario + "&duracao="
		+ duracao + "&codDisciplina=" + codDisciplina + "&assunto=" + assunto);
}

// DELETA UMA AULA
deleta = function(id) {
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = () => {
		if (req.readyState == 4 && req.status == 200) {
			atualizaSessao();
			window.location.href = "/prova1";
		} else {
			// O QUE FAZER SE DEU ERRADO
		}
	}
	req.send("op=DELETE&id=" + id);
}




const atualizaSessao = function() {
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = () => {
		if (req.readyState == 4 && req.status == 200) {
			// O QUE FAZER SE DEU CERTO
		} else {
			// O QUE FAZER SE DEU ERRADO
		}
	}
	req.send("op=START_SESSION");
}



// ============================================================
// 			VALIDAÇÕES

validaNovaAula = function(data, horario, duracao, codDisciplina, assunto) {
    c1 = codDisciplina == '0';
    c2 = horario == '';
    c3 = duracao <= 0;
    c4 = data == '';
    c5 = assunto == '';
    if (c1 || c2 || c3 || c4 || c5) {
        return false;
    }
    return true;
}





// ===================================================================================
// 		INICIALIZA O PROCESSAMENTO

atualizaSessao();
