package Service;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.util.List;

import dao.DaoAluno;
import model.Aluno;
import spark.Request;
import spark.Response;

public class AlunoService {
	private DaoAluno DaoAluno = new DaoAluno();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_NAME = 2;
    private final int FORM_ORDERBY_COURSE = 3;
    
    public AlunoService() throws SQLException {
        makeForm();
    }

    public void makeForm() throws SQLException {
        makeForm(FORM_INSERT, new Aluno(), FORM_ORDERBY_NAME);
    }

    public void makeForm(int orderBy) throws SQLException {
        makeForm(FORM_INSERT, new Aluno(), orderBy);
    }
    
    public void makeForm(int tipo, Aluno umAluno, int orderBy) throws SQLException {
        String nomeArquivo = "form.html";
        form = "";
        try{
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while(entrada.hasNext()){
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }

        String umAlunoForm = "";
        if(tipo != FORM_INSERT) {
            umAlunoForm += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/aluno/list/1\">Novo Aluno</a></b></font></td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t</table>";
            umAlunoForm += "\t<br>";
        }

        if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
            String action = "/aluno/";
            String name, nomeAluno, buttonLabel;
            if (tipo == FORM_INSERT){
                action += "insert";
                name = "Inserir Aluno";
                nomeAluno = "Nome do Aluno";
                buttonLabel = "Inserir";
            } else {
                action += "update/" + umAluno.getId();
                name = "Atualizar Aluno (ID " + umAluno.getId() + ")";
                nomeAluno = umAluno.getNome();
                buttonLabel = "Atualizar";
            }

            umAlunoForm += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
            umAlunoForm += "\t<table class=\"form-table\" align=\"center\">";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td colspan=\"3\" class=\"form-header\">" + name + "</td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td colspan=\"3\">&nbsp;</td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td><label for=\"nome\">Nome: </label><input class=\"input--register\" type=\"text\" name=\"nome\" id=\"nome\" value=\"" + nomeAluno + "\"></td>";
            umAlunoForm += "\t\t\t<td><label for=\"email\">Email: </label><input class=\"input--register\" type=\"email\" name=\"email\" id=\"email\" value=\"" + umAluno.getEmail() + "\"></td>";
            umAlunoForm += "\t\t\t<td><label for=\"telefone\">Telefone: </label><input class=\"input--register\" type=\"text\" name=\"telefone\" id=\"telefone\" value=\"" + umAluno.getTelefone() + "\"></td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td><label for=\"data_nascimento\">Data de Nascimento: </label><input class=\"input--register\" type=\"date\" name=\"data_nascimento\" id=\"data_nascimento\" value=\"" +
                    (umAluno.getData_nascimento() != null ? umAluno.getData_nascimento().toString() : "") +
                    "\"></td>";
            umAlunoForm += "\t\t\t<td><label for=\"curso\">Curso: </label><input class=\"input--register\" type=\"text\" name=\"curso\" id=\"curso\" value=\"" + umAluno.getCurso() + "\"></td>";
            umAlunoForm += "\t\t\t<td><label for=\"data_matricula\">Data de Matrícula: </label><input class=\"input--register\" type=\"date\" name=\"data_matricula\" id=\"data_matricula\" value=\"" +
                    (umAluno.getData_matricula() != null ? umAluno.getData_matricula().toString() : "") +
                    "\"></td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"" + buttonLabel + "\" class=\"input--main__style input--button\"></td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t</table>";
            umAlunoForm += "\t</form>";
        } else if (tipo == FORM_DETAIL) {
            umAlunoForm += "\t<table width=\"100%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Aluno (ID " + umAluno.getId() + ")</b></font></td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td>&nbsp;Nome: " + umAluno.getNome() + "</td>";
            umAlunoForm += "\t\t\t<td>Email: " + umAluno.getEmail() + "</td>";
            umAlunoForm += "\t\t\t<td>Telefone: " + umAluno.getTelefone() + "</td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t\t<tr>";
            umAlunoForm += "\t\t\t<td>&nbsp;Data de Nascimento: " + umAluno.getData_nascimento().toString() + "</td>";
            umAlunoForm += "\t\t\t<td>Curso: " + umAluno.getCurso() + "</td>";
            umAlunoForm += "\t\t\t<td>Data de Matrícula: " + umAluno.getData_matricula().toString() + "</td>";
            umAlunoForm += "\t\t</tr>";
            umAlunoForm += "\t</table>";
        } else {
            System.out.println("ERRO! Tipo não identificado " + tipo);
        }
        form = form.replaceFirst("<UMA-ALUNO>", umAlunoForm);

        String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
        list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Alunos</b></font></td></tr>\n" +
                "\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
                "\n<tr>\n" +
                "\t<td><a href=\"/aluno/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
                "\t<td><a href=\"/aluno/list/" + FORM_ORDERBY_NAME + "\"><b>Nome</b></a></td>\n" +
                "\t<td><a href=\"/aluno/list/" + FORM_ORDERBY_COURSE + "\"><b>Curso</b></a></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
                "</tr>\n";

        List<Aluno> alunos;
        if (orderBy == FORM_ORDERBY_ID) {                 	
            alunos = DaoAluno.getOrderByID();
        } else if (orderBy == FORM_ORDERBY_NAME) {		
            alunos = DaoAluno.getOrderByNome();
        } else if (orderBy == FORM_ORDERBY_COURSE) {			
            alunos = DaoAluno.getOrderByCurso();
        } else {											
            alunos =  DaoAluno.get();
        }

        int i = 0;
        String bgcolor = "";
        for (Aluno a : alunos) {
            bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
            list += "\n<tr bgcolor=\"" + bgcolor + "\">\n" +
                    "\t<td>" + a.getId() + "</td>\n" +
                    "\t<td>" + a.getNome() + "</td>\n" +
                    "\t<td>" + a.getCurso() + "</td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/aluno/" + a.getId() + "\"><img src=\"/image/details.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/aluno/update/" + a.getId() + "\"><img src=\"/image/up.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteAluno('" + a.getId() + "', '" + a.getNome() + "', '" + a.getCurso() + "');\"><img src=\"/image/trash.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "</tr>\n";
        }
        list += "</table>";
        form = form.replaceFirst("<LISTAR-ALUNO>", list);
    }
    
    public Object insert(Request request, Response response) throws SQLException {
        String nome = request.queryParams("nome");
        LocalDate dataNascimento = LocalDate.parse(request.queryParams("data_nascimento"));
        char genero = request.queryParams("genero").charAt(0);
        String email = request.queryParams("email");
        String telefone = request.queryParams("telefone");
        String curso = request.queryParams("curso");
        LocalDate dataMatricula = LocalDate.parse(request.queryParams("data_matricula"));

        String resp = "";

        Aluno aluno = new Aluno(-1, nome, dataNascimento, genero, email, telefone, curso, dataMatricula);

        if(DaoAluno.insert(aluno)) {
            resp = "Aluno (" + nome + ") inserido!";
            response.status(201); // 201 Created
        } else {
            resp = "Aluno (" + nome + ") não inserido!";
            response.status(404); // 404 Not found
        }

        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
    }

    public Object get(Request request, Response response) throws SQLException {
        int id = Integer.parseInt(request.params(":id"));        
        Aluno aluno = (Aluno) DaoAluno.get(id);

        if (aluno != null) {
            response.status(200); // success
            makeForm(FORM_DETAIL, aluno, FORM_ORDERBY_NAME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Aluno " + id + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

        return form;
    }

    public Object getToUpdate(Request request, Response response) throws SQLException {
        int id = Integer.parseInt(request.params(":id"));        
        Aluno aluno = (Aluno) DaoAluno.get(id);

        if (aluno != null) {
            response.status(200); // success
            makeForm(FORM_UPDATE, aluno, FORM_ORDERBY_NAME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Aluno " + id + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

        return form;
    }

    public Object getAll(Request request, Response response) throws SQLException {
        int orderBy = Integer.parseInt(request.params(":orderby"));
        makeForm(orderBy);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    public Object update(Request request, Response response) throws SQLException{
        int id = Integer.parseInt(request.params(":id"));
        Aluno aluno = DaoAluno.get(id);
        String resp = "";

        if (aluno != null) {
            aluno.setNome(request.queryParams("nome"));
            aluno.setData_nascimento(LocalDate.parse(request.queryParams("data_nascimento")));
            aluno.setGenero(request.queryParams("genero").charAt(0));
            aluno.setEmail(request.queryParams("email"));
            aluno.setTelefone(request.queryParams("telefone"));
            aluno.setCurso(request.queryParams("curso"));
            aluno.setData_matricula(LocalDate.parse(request.queryParams("data_matricula")));
            DaoAluno.update(aluno);
            response.status(200); // success
            resp = "Aluno (ID " + aluno.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Aluno (ID " + aluno.getId() + ") não encontrado!";
        }

        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
    }

    public Object delete(Request request, Response response) throws SQLException {
        int id = Integer.parseInt(request.params(":id"));
        Aluno aluno = (Aluno) DaoAluno.get(id);
        String resp = "";

        if (aluno != null) {
            DaoAluno.delete(id);
            response.status(200); // success
            resp = "Aluno (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Aluno (" + id + ") não encontrado!";
        }

        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
    }

    
}
