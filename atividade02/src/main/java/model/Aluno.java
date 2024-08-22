package model;

import java.util.Date;

//import javax.xml.crypto.Data;
//import java.sql.Date;

public class Aluno {
	private int id;
	private String nome;
	private Date data_nascimento;
	private char genero;
	private String email;
	private String telefone;
	private String curso;
	private Date data_matricula;
	
	public Aluno(){
		 this.nome = "";
		 this.data_nascimento = null; 
		 this.genero = '*';
		 this.email = "";
		 this.telefone = "";
		 this.curso = "";
		 this.data_matricula = null; 
	}
	
	public Aluno(int id, String nome, Date data_nascimento, char genero, String email, String telefone, String curso, Date data_matricula) {
		this.id= id;
		this.nome= nome;
		this.data_nascimento= data_nascimento;
		this.genero = genero;
		this.email = email;
		this.telefone = telefone;
		this.curso= curso;
		this.data_matricula= data_matricula;
		
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getData_nascimento() {
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

	public Date getData_matricula() {
		return data_matricula;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
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

	public void setData_matricula(Date data_matricula) {
		this.data_matricula = data_matricula;
	}
	
	
	
	
}
