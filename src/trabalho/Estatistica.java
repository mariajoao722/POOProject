package trabalho;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import myinputs.Ler;
import static trabalho.GerirCurso.procuraCurso;
import static trabalho.GerirNotas.existeCurso;
import static trabalho.Main.guardaFich;

public class Estatistica implements Serializable {

    public static String cursoMaisFrequentado(ArrayList<Curso> listaC) {
        if (!listaC.isEmpty()) {
            Curso devolve = listaC.get(0);
            for (Curso singular : listaC) {
                if (devolve.getListaA().size() < singular.getListaA().size()) {
                    devolve = singular;
                }
            }
            return ("O curso mais frequentado é " + devolve.getNome());
        } else {
            return "Não existem cursos";
        }
    }

    public static String professorMaisLecionou(ArrayList<Professor> listaP) {
        if (!listaP.isEmpty()) {
            Professor devolve = listaP.get(0);
            for (Professor singular : listaP) {
                if (devolve.getListaC().size() < singular.getListaC().size()) {
                    devolve = singular;
                }
            }
            return ("O professor que mais lecionou é " + devolve.getNome());
        } else {
            return "Não existem professores";
        }
    }

    public static String cursoMaisHoras(ArrayList<Curso> listaC) {
        if (!listaC.isEmpty()) {
            Curso devolve = listaC.get(0);
            for (Curso singular : listaC) {
                if (devolve.getHoras() < singular.getHoras()) {
                    devolve = singular;
                }
            }
            return ("O curso com mais horas é " + devolve.getNome());
        } else {
            return "Não existem cursos";
        }
    }

    public static String raparigasNaEscola(ArrayList<Aluno> listaA) {
        if (!listaA.isEmpty()) {
            int contador = 0;
            for (Aluno singular : listaA) {
                if (singular.getGenero().equals("F")) {
                    contador++;
                }
            }
            return "Existem " + contador + " rapariga(s) na escola.";
        } else {
            return "Não existem alunos registados!";
        }
    }

    public static int alunosMoramEscola(ArrayList<Aluno> listaA) {
        int contador = 0;
        for (Aluno singular : listaA) {
            if (singular.getCidade().toLowerCase().equals("covilha")) {
                contador++;
            }
        }
        return contador;
    }

    public static double idadeMedia(ArrayList<Aluno> listaA) {
        if (!listaA.isEmpty()) {
            double devolve = 0;
            for (Aluno singular : listaA) {
                devolve += singular.getIdade();
            }
            return devolve / listaA.size();
        } else {
            return -1;
        }
    }

    public static String melhorAlunoCurso(ArrayList<Curso> listaC, ArrayList<Notas> listaN) {
        if (!listaC.isEmpty()) {
            System.out.print("Insira o nome do curso que deseja procuar: ");
            Curso curso = procuraCurso(listaC);
            if (curso == null) {
                return "Curso inexistente!";
            }
            if (existeCurso(listaN, curso)) {
                Notas melhor = new Notas();
                for (Notas singular : listaN) {
                    if (singular.getC().equals(curso) && singular.getNota() > melhor.getNota()) {
                        melhor = singular;
                    }
                }
                return "O melhor aluno do curso " + curso.getNome() + " é " + melhor.getA().getNome() + " com " + melhor.getNota() + " de nota.";
            } else {
                return "Curso sem graduações!";
            }
        } else {
            return "Não existem cursos registados!";
        }
    }

    public static String idadeMediaCurso(ArrayList<Curso> listaC) {
        if (!listaC.isEmpty()) {
            System.out.print("Insira o nome do curso que deseja procurar: ");
            Curso curso = procuraCurso(listaC);
            if (curso == null) {
                return "Curso inexistente!";
            }
            double devolve = idadeMedia(curso.getListaA());
            return "A idade média do curso " + curso.getNome() + " é " + String.format("%.02f", devolve);
        } else {
            return "Não existem cursos registados!";
        }
    }

    public static void menuEstatistica(Main main) {
        int op;
        do {
            System.out.println("01 - Qual é o curso mais frequentado?");
            System.out.println("02 - Qual é o professor que lecionou mais cursos?");
            System.out.println("03 - Qual é o curso com mais duração?");
            System.out.println("04 - Quantas raparigas tem a escola?");
            System.out.println("05 - Quantos alunos frequentam a escola?");
            System.out.println("06 - Quantos alunos moram na cidade da escola?");
            System.out.println("07 - Quantos professores trabalham na escola?");
            System.out.println("08 - Quantos cursos existem na escola?");
            System.out.println("09 - Qual a idade média dos alunos?");
            System.out.println("10 - Qual o aluno com a melhor nota num certo curso?");
            System.out.println("11 - Qual a idade média dos alunos num certo curso?");
            System.out.println("12 - Gerar Estatísticas.txt;");
            System.out.println("00 - Voltar atrás.");
            op = Ler.umInt();

            switch (op) {
                case 0:
                    break;
                case 1:
                    System.out.println(cursoMaisFrequentado(main.listaC));
                    break;
                case 2:
                    System.out.println(professorMaisLecionou(main.listaP));
                    break;
                case 3:
                    System.out.println(cursoMaisHoras(main.listaC));
                    break;
                case 4:
                    System.out.println(raparigasNaEscola(main.listaA));
                    break;
                case 5:
                    System.out.println("A escola tem " + main.listaA.size() + " aluno(s)");
                    break;
                case 6:
                    System.out.println("Mora(m) " + alunosMoramEscola(main.listaA) + " aluno(s) na mesma cidade da escola");
                    break;
                case 7:
                    System.out.println("A escola tem " + main.listaP.size() + " professore(s)");
                    break;
                case 8:
                    System.out.println("A escola tem " + main.listaC.size() + " curso(s)");
                    break;
                case 9:
                    System.out.println("A idade média dos alunos é: " + ((idadeMedia(main.listaA) == -1) ? "erro\nNão exitem alunos registados!" : String.format("%.02f", idadeMedia(main.listaA))));
                    break;
                case 10:
                    System.out.println(melhorAlunoCurso(main.listaC, main.listaN));
                    break;
                case 11:
                    System.out.println(idadeMediaCurso(main.listaC));
                    break;
                case 12:
                    try {
                    FileOutputStream out = new FileOutputStream("Estatísticas.txt");
                    PrintWriter pw = new PrintWriter("Estatísticas.txt");
                    pw.println(cursoMaisFrequentado(main.listaC));
                    pw.println(professorMaisLecionou(main.listaP));
                    pw.println(cursoMaisHoras(main.listaC));
                    pw.println(raparigasNaEscola(main.listaA));
                    pw.println("A escola tem " + main.listaA.size() + " aluno(s)");
                    pw.println("Mora(m) " + alunosMoramEscola(main.listaA) + " aluno(s) na mesma cidade da escola");
                    pw.println("A escola tem " + main.listaP.size() + " professore(s)");
                    pw.println("A escola tem " + main.listaC.size() + " curso(s)");
                    pw.println("A idade média dos alunos é: " + ((idadeMedia(main.listaA) == -1) ? "erro\nNão exitem alunos registados!" : String.format("%.02f", idadeMedia(main.listaA))));
                    /* Estas linhas nao podem entrar aqui porque só mostram o melhor aluno e a idade média
                       num dado curso, isto envolveria perguntares os cursos quando fosses a guardar
                       não sao boas estatisticas para guardar no ficheiro.
                    pw.println(melhorAlunoCurso(main.listaC, main.listaN));
                    pw.println(idadeMediaCurso(main.listaC));
                     */
                    pw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            guardaFich(main, "Main.txt");
        } while (op != 0);

    }
}
