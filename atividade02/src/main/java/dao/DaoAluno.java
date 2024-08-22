package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class DaoAluno extends DAO {

    public DaoAluno() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Aluno aluno) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO alunos (nome, data_nascimento, genero, email, telefone, curso, data_matricula) "
                       + "VALUES ('" + aluno.getNome() + "', '"  
                       + aluno.getData_nascimento() + "', '" + aluno.getGenero() + "', '"
                       + aluno.getEmail() + "', '" + aluno.getTelefone() + "', '"
                       + aluno.getCurso() + "', '" + aluno.getData_matricula() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    public List<Aluno> list() {
        List<Aluno> alunos = new ArrayList<Aluno>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM alunos ORDER BY data_matricula DESC";  
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("data_nascimento"),
                    rs.getString("genero").charAt(0),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("curso"),
                    rs.getDate("data_matricula")
                );
                alunos.add(aluno);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return alunos;
    }

    public boolean update(Aluno aluno) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE alunos SET nome = '" + aluno.getNome() + "', data_nascimento = '"  
                       + aluno.getData_nascimento() + "', genero = '" + aluno.getGenero() + "', "
                       + "email = '" + aluno.getEmail() + "', telefone = '" + aluno.getTelefone() + "', "
                       + "curso = '" + aluno.getCurso() + "', data_matricula = '" + aluno.getData_matricula() + "'"
                       + " WHERE id = " + aluno.getId();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM alunos WHERE id = " + id;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
