package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AulaDto;

import java.io.IOException;
import java.util.ArrayList;

import db.Db;

@WebServlet(urlPatterns = { "/prova1", "/nova", "/edit" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/nova")) {
			RequestDispatcher rd = request.getRequestDispatcher("nova.jsp");
			rd.forward(request, response);
		} else if (action.equals("/edit")) {
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			Db db = Db.getInstance();
			AulaDto dto = db.findById(id);
			session.setAttribute("dto", dto);
			RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String op = request.getParameter("op");
		switch (op) {
		case "START_SESSION":
			this.poeDadosNaSessao(session);
			break;
		case "RESET":
			this.reset();
			break;
		case "CREATE":
			this.create(request);
			break;
		case "READ":
			this.getAula(request, response);
			break;
		case "UPDATE":
			this.update(request);
			break;
		case "DELETE":
			this.delete(request);
			break;
		}
	}

	private void poeDadosNaSessao(HttpSession session) {
		Db db = Db.getInstance();
		ArrayList<AulaDto> lista = db.findAll();
		session.setAttribute("lista", lista);
	}

	private void reset() {
		Db db = Db.getInstance();
		db.reset();
	}

	private void create(HttpServletRequest request) {
		String codDisciplina = request.getParameter("codDisciplina");
		String assunto = request.getParameter("assunto");
		String duracao = request.getParameter("duracao");
		String data = request.getParameter("data");
		String horario = request.getParameter("horario");
		AulaDto dto = new AulaDto();
		dto.codDisciplina = codDisciplina;
		dto.assunto = assunto;
		dto.duracao = duracao;
		dto.data = data;
		dto.horario = horario;
		Db db = Db.getInstance();
		db.create(dto);
	}

	private void delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		Db db = Db.getInstance();
		db.delete(id);
	}

	private void getAula(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Db db = Db.getInstance();
		AulaDto dto = db.findById(id);
		response.setContentType("application/json");
		StringBuilder stb = new StringBuilder();
		stb.append("{\"id\": \"").append(id).append("\",").append("\"disciplina\": \"").append(dto.disciplina)
				.append("\",").append("\"codDisciplina\": \"").append(dto.codDisciplina).append("\",")
				.append("\"assunto\": \"").append(dto.assunto).append("\"").append("\"duracao\": \"")
				.append(dto.duracao).append("\"").append("\"data\": \"").append(dto.data).append("\"")
				.append("\"horario\": \"").append(dto.horario).append("\"").append("}");
		String json = stb.toString();
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO: o que fazer de deu errado
		}
	}
	
	private void update(HttpServletRequest request) {
		String id = request.getParameter("id");
		String codDisciplina = request.getParameter("codDisciplina");
		String assunto = request.getParameter("assunto");
		String duracao = request.getParameter("duracao");
		String data = request.getParameter("data");
		String horario = request.getParameter("horario");
		AulaDto dto = new AulaDto();
		dto.id = id;
		dto.codDisciplina = codDisciplina;
		dto.assunto = assunto;
		dto.duracao = duracao;
		dto.data = data;
		dto.horario = horario;
		Db db = Db.getInstance();
		db.update(dto);
	}

}














