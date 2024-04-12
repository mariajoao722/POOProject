package trabalho;

import java.util.ArrayList;
import myinputs.Ler;
import static trabalho.GerirCurso.procuraCurso;
import static trabalho.Main.guardaFich;

public class GerirNotas {

    public static boolean existeCurso(ArrayList<Notas> listaN, Curso c) {
        if (c != null) {
            for (Notas singular : listaN) {
                if (singular.getC().equals(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void limpaLista(ArrayList<Notas> listaN, Curso c) {
        if (c != null) {
            ArrayList<Notas> temporario = new ArrayList<>();
            for (Notas singular : listaN) {
                if (singular.getC().getNome().equals(c.getNome())) {
                    temporario.add(singular);
                }
            }
            for (Notas singular : temporario) {
                listaN.remove(singular);
            }
        }
    }

    public static void graduarAlunos(ArrayList<Notas> listaN, ArrayList<Curso> listaC) {
        double nota = -1;
        System.out.print("Insira o curso que pretenda graduar: ");
        Curso c = GerirCurso.procuraCurso(listaC);
        limpaLista(listaN, c);
        if (c != null) {
            if (!c.getListaA().isEmpty()) {
                for (Aluno singular : c.getListaA()) {
                    Notas n = new Notas();
                    System.out.print("Que nota teve " + ((singular.genero.equals("M")) ? "o " : "a ")
                            + singular.getNome() + " (Número:" + singular.getNumero() + ")? ");
                    do {
                        nota = Ler.umDouble();
                        if (nota < 0 || nota > 20) {
                            System.out.print("Introduza nota de 0 a 20: ");
                        }
                    } while (nota < 0 || nota > 20);
                    n.setA(singular);
                    n.setC(c);
                    n.setNota(nota);
                    listaN.add(n);
                }
            } else {
                System.out.println("Curso sem alunos!");
            }

        } else {
            System.out.println("Curso inexistente!");
        }
    }

    public static void alterarGraducoes(ArrayList<Notas> listaN, ArrayList<Curso> listaC) {
        System.out.print("Insira o curso em que deseja alterar graduações: ");
        Curso curso = procuraCurso(listaC);
        ArrayList<Notas> altera = new ArrayList<>();
        if (curso != null) {
            if (existeCurso(listaN, curso)) {
                int op = 0;
                double nota;
                for (Notas singular : listaN) {
                    if (singular.getC().equals(curso)) {
                        System.out.println(singular.toString2());
                        altera.add(singular);
                    }
                }
                do {
                    System.out.print("Qual o aluno que deseja alterar a nota a (posição): ");
                    op = Ler.umInt() - 1;
                } while (op < 0 || op >= altera.size());
                do {
                    System.out.print("Qual vai ser a nova nota: ");
                    nota = Ler.umDouble();
                } while (nota < 0 || nota > 20);
                altera.get(op).setNota(nota);
            } else {
                System.out.println("Curso sem graduações!");
            }
        } else {
            System.out.println("Curso inexistente!");
        }
    }

    public static void listarGraduacoes(ArrayList<Notas> listaN, ArrayList<Curso> listaC) {
        System.out.print("Insira o curso que pretenda ver: ");
        Curso cVer = GerirCurso.procuraCurso(listaC);
        if (cVer != null) {
            if (!cVer.getListaA().isEmpty()) {
                for (Notas singular : listaN) {
                    if (singular.getC().getNome().equals(cVer.getNome())) {
                        System.out.println(singular.toString2());
                    }
                }
            } else {
                System.out.println("Curso sem alunos!");
            }

        } else {
            System.out.println("Curso inexistente!");
        }
    }

    public static void menuGerirNotas(Main main) {
        int op;
        do {
            System.out.println("1 - Graduar alunos;");
            System.out.println("2 - Alterar graduações;");
            System.out.println("3 - Listar graduações");
            System.out.println("0 - Voltar atrás;");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    graduarAlunos(main.listaN, main.listaC);
                    break;
                case 2:
                    alterarGraducoes(main.listaN, main.listaC);
                    break;
                case 3:
                    listarGraduacoes(main.listaN, main.listaC);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            guardaFich(main, "Main.txt");
        } while (op != 0);

    }
}
