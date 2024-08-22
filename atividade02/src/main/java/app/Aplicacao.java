package app;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import model.Aluno;
import dao.DaoAluno;

public class Aplicacao {

    private static DaoAluno daoAluno = new DaoAluno();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;
        do {
            System.out.println("\n=== Menu de Alunos ===");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Inserir Aluno");
            System.out.println("3. Excluir Aluno");
            System.out.println("4. Atualizar Aluno");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    listarAlunos();
                    break;
                case 2:
                    inserirAluno();
                    break;
                case 3:
                    excluirAluno();
                    break;
                case 4:
                    atualizarAluno();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 5);

        daoAluno.finalize();
    }

    private static void listarAlunos() {
        List<Aluno> alunos = daoAluno.list();
        System.out.println("\n=== Lista de Alunos ===");
        for (Aluno aluno : alunos) {
            System.out.println(aluno.getId() + " - " + aluno.getNome() + " - " + aluno.getData_matricula());
        }
    }

    private static void inserirAluno() {
        System.out.println("\n=== Inserir Aluno ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Nascimento (yyyy-mm-dd): ");
        Date dataNascimento = Date.valueOf(scanner.nextLine());
        System.out.print("Gênero (M/F): ");
        char genero = scanner.nextLine().charAt(0);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        System.out.print("Data de Matrícula (yyyy-mm-dd): ");
        Date dataMatricula = Date.valueOf(scanner.nextLine());

        Aluno aluno = new Aluno(-1, nome, dataNascimento, genero, email, telefone, curso, dataMatricula);
        if (daoAluno.insert(aluno)) {
            System.out.println("Aluno inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir aluno.");
        }
    }

    private static void excluirAluno() {
        System.out.println("\n=== Excluir Aluno ===");
        System.out.print("ID do Aluno: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (daoAluno.delete(id)) {
            System.out.println("Aluno excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir aluno.");
        }
    }

    private static void atualizarAluno() {
        System.out.println("\n=== Atualizar Aluno ===");
        System.out.print("ID do Aluno: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Nascimento (yyyy-mm-dd): ");
        Date dataNascimento = Date.valueOf(scanner.nextLine());
        System.out.print("Gênero (M/F): ");
        char genero = scanner.nextLine().charAt(0);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        System.out.print("Data de Matrícula (yyyy-mm-dd): ");
        Date dataMatricula = Date.valueOf(scanner.nextLine());

        Aluno aluno = new Aluno(id, nome, dataNascimento, genero, email, telefone, curso, dataMatricula);
        if (daoAluno.update(aluno)) {
            System.out.println("Aluno atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar aluno.");
        }
    }
}
