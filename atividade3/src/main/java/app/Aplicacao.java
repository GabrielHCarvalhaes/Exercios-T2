package app;
import static spark.Spark.*;

import java.sql.SQLException;

import Service.AlunoService;

public class Aplicacao {
	
	
    public static void main(String[] args) throws SQLException {
        port(6789);
        
        AlunoService AlunoService = new AlunoService();
        
        staticFiles.location("/public");
        
        post("/aluno/insert", (request, response) ->AlunoService.insert(request, response));

        get("/aluno/:id", (request, response) -> AlunoService.get(request, response));
        
        get("/aluno/list/:orderby", (request, response) -> AlunoService.getAll(request, response));

        get("/aluno/update/:id", (request, response) -> AlunoService.getToUpdate(request, response));
        
        post("/aluno/update/:id", (request, response) -> AlunoService.update(request, response));
           
        get("/aluno/delete/:id", (request, response) -> AlunoService.delete(request, response));

             
    }
}