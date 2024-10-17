package model;

import java.time.LocalDate;
import java.util.Date;

//import javax.xml.crypto.Data;
//import java.sql.Date;

public class Aluno {
	private int id;
	private String nome;
	private LocalDate data_nascimento;
	private char genero;
	private String email;
	private String telefone;
	private String curso;
	private LocalDate data_matricula;
	
	public Aluno(){
		 this.nome = "";
		 this.data_nascimento = null; 
		 this.genero = '*';
		 this.email = "";
		 this.telefone = "";
		 this.curso = "";
		 this.data_matricula = null; 
	}
	



	public Aluno(int id, String nome, LocalDate dataNascimento, char genero, String email, String telefone, String curso, LocalDate dataMatricula) {
		this.id= id;
		this.nome= nome;
		this.data_nascimento= dataNascimento;
		this.genero = genero;
		this.email = email;
		this.telefone = telefone;
		this.curso= curso;
		this.data_matricula= dataMatricula;
		
	}



	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public char getGenero() {
		return genero;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCurso() {
		return curso;
	}

	public LocalDate getData_matricula() {
		return data_matricula;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setData_nascimento(LocalDate localDate) {
		this.data_nascimento = localDate;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public void setData_matricula(LocalDate localDate) {
		this.data_matricula = localDate;
	}

	
	
}
