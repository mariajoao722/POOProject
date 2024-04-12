package trabalho;

import myinputs.Ler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Main implements Serializable {

    public ArrayList<Curso> listaC = new ArrayList<>();
    public ArrayList<Aluno> listaA = new ArrayList<>();
    public ArrayList<Professor> listaP = new ArrayList<>();
    public ArrayList<Notas> listaN = new ArrayList<>();

    public static void guardaFich(Object o, String s) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(s));
            out.writeObject(o);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Object leFich(String s, Object a) {
        File file = new File(s);
        try {
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream(s));
            a = inp.readObject();
            inp.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não existente - " + e.getMessage());
        }
        return a;
    }
    
    public static void main(String[] args) {
        int op;
        
        Main main = new Main();
        
        main = (Main) leFich("Main.txt", main);
        
        do {
            System.out.println("1 - Gerir cursos;");
            System.out.println("2 - Gerir professores;");
            System.out.println("3 - Gerir alunos;");
            System.out.println("4 - Gerir notas;");
            System.out.println("5 - Estatísticas;");
            System.out.println("0 - Terminar.");
            op = Ler.umInt();

            switch (op) {
                case 0:
                    break;
                case 1:
                    GerirCurso.menuGerirCursos(main);
                    break;
                case 2:
                    GerirProfessor.menuGerirProf(main);
                    break;
                case 3:
                    GerirAluno.menuGerirAlunos(main);
                    break;
                case 4:
                    GerirNotas.menuGerirNotas(main);
                    break;
                case 5:
                    Estatistica.menuEstatistica(main);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            guardaFich(main, "Main.txt");
        } while (op != 0);
    }
}
