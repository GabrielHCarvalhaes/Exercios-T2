package dao;

import model.Aluno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "INSERT INTO Aluno (nome, data_nascimento, genero, email, telefone, curso, data_matricula) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, aluno.getNome());
            st.setDate(2, Date.valueOf(aluno.getData_nascimento()));
            st.setString(3, String.valueOf(aluno.getGenero()));
            st.setString(4, aluno.getEmail());
            st.setString(5, aluno.getTelefone());
            st.setString(6, aluno.getCurso());
            st.setDate(7, Date.valueOf(aluno.getData_matricula()));
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Aluno get(int id) {
        Aluno aluno = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Aluno WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("genero").charAt(0),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("curso"),
                        rs.getDate("data_matricula").toLocalDate());
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return aluno;
    }

    public List<Aluno> get() {
        return get("");
    }

    public List<Aluno> getOrderByID() {
        return get("id");
    }

    public List<Aluno> getOrderByNome() {
        return get("nome");
    }

    public List<Aluno> getOrderByCurso() {
        return get("curso");
    }

    private List<Aluno> get(String orderBy) {
        List<Aluno> alunos = new ArrayList<Aluno>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Aluno" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("genero").charAt(0),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("curso"),
                        rs.getDate("data_matricula").toLocalDate());
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
            String sql = "UPDATE Aluno SET nome = ?, data_nascimento = ?, genero = ?, email = ?, telefone = ?, curso = ?, data_matricula = ? WHERE id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, aluno.getNome());
            st.setDate(2, Date.valueOf(aluno.getData_nascimento()));
            st.setString(3, String.valueOf(aluno.getGenero()));
            st.setString(4, aluno.getEmail());
            st.setString(5, aluno.getTelefone());
            st.setString(6, aluno.getCurso());
            st.setDate(7, Date.valueOf(aluno.getData_matricula()));
            st.setInt(8, aluno.getId());
            st.executeUpdate();
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
            st.executeUpdate("DELETE FROM Aluno WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
