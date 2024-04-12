package trabalho;

import java.time.LocalDate;
import java.util.ArrayList;
import myinputs.Ler;
import static trabalho.GerirAluno.procuraAluno;
import static trabalho.Main.guardaFich;

public class GerirCurso {

    public static void LerData(Curso c, String modo) {
        boolean continuacao = true;
        String comparacao = "";
        while (continuacao) {
            try {
                System.out.println("Introduza uma data de " + modo + " no formato AAAA-MM-DD, (Ex: 1970-01-01)");
                if (modo.equals("início")) {
                    if (c.getData_inicio().compareTo(LocalDate.of(1970, 01, 01)) == 0) {
                        c.setData_inicio(Ler.umaString());
                    } else {
                        do {
                            if (!comparacao.equals("")) {
                                System.out.println("Introduza uma data antes de " + c.getData_fim().toString());
                            }
                            comparacao = Ler.umaString();
                        } while (c.getData_fim().compareTo(LocalDate.parse(comparacao)) < 0);
                        c.setData_inicio(comparacao);
                    }

                } else {
                    do {
                        if (!comparacao.equals("")) {
                            System.out.println("Introduza uma data após " + c.getData_inicio().toString());
                        }
                        comparacao = Ler.umaString();
                    } while (c.getData_inicio().compareTo(LocalDate.parse(comparacao)) >= 0);
                    c.setData_fim(comparacao);
                }
                continuacao = false;
            } catch (Exception DateTimeParseException) {
                System.out.println("Data no formato inválido!");
            }
        }
    }

    public static Curso procuraCurso(ArrayList<Curso> lista) {
        String nome_curso = Ler.umaString().toLowerCase();
        Curso devolve = null;
        for (Curso curso : lista) {
            if (curso.getNome().toLowerCase().equals(nome_curso)) {
                devolve = curso;
            }
        }
        return devolve;
    }

    public static void removerAlunoCurso(Curso c) {
        if (!c.getListaA().isEmpty()) {
            System.out.print("Introduza o nome do aluno que deseja remover: ");
            ArrayList<Aluno> remover = procuraAluno(c.getListaA());
            boolean continuacao = true;
            if (!remover.isEmpty()) {
                if (remover.size() > 1) {
                    System.out.println("Conflito! Qual deles deseja remover? (Ex: 1)");
                    for (Aluno aluno : remover) {
                        System.out.println(aluno);
                    }
                    while (continuacao) {
                        try {
                            c.removeAluno(remover.get(Ler.umInt() - 1));
                            continuacao = false;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Insira a posição do aluno (1 - " + remover.size() + ")");
                        }
                    }
                } else {
                    c.removeAluno(remover.get(0));
                }
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } else {
            System.out.println("Não existem alunos registados");
        }
    }

    public static void menuModificarCurso(Curso c) {
        int op;
        do {
            System.out.println(c);
            System.out.println("1 - Modificar nome;");
            System.out.println("2 - Modificar número;");
            System.out.println("3 - Modificar horas;");
            System.out.println("4 - Modificar data início;");
            System.out.println("5 - Modificar data fim;");
            System.out.println("6 - Remover aluno do curso;");
            System.out.println("0 - Voltar atrás;");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    System.out.print("Novo nome: ");
                    c.setNome(Ler.umaString());
                    break;
                case 2:
                    System.out.print("Novo número: ");
                    c.setNum(Ler.umInt());
                    break;
                case 3:
                    System.out.print("Novo número de horas: ");
                    c.setHoras(Ler.umInt());
                    break;
                case 4:
                    LerData(c, "início");
                    break;
                case 5:
                    LerData(c, "fim");
                    break;
                case 6:
                    removerAlunoCurso(c);
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 0);
    }

    public static Curso adicionarCurso(ArrayList<Curso> listaC) {
        Curso c = new Curso();

        boolean continuacao = true;
        while (continuacao) {
            continuacao = false;
            System.out.print("Nome do curso: ");
            String nome_curso = Ler.umaString();
            for (int i = 0; i < listaC.size(); i++) {
                if (listaC.get(i).getNome().equals(nome_curso)) {
                    continuacao = true;
                }
            }
            if (continuacao) {
                System.out.println("Nome já registado!");
            } else {
                c.setNome(nome_curso);
            }
        }

        System.out.print("Número do curso: ");
        c.setNum(Ler.umInt());
        System.out.print("Número de horas: ");
        c.setHoras(Ler.umInt());
        System.out.print("Data de início: ");
        LerData(c, "início");
        System.out.print("Data de fim: ");
        LerData(c, "fim");

        return c;
    }

    public static void listarCurso(ArrayList<Curso> listaC) {
        if (!listaC.isEmpty()) {
            for (Curso curso : listaC) {
                System.out.println(curso.toString());
            }
        } else {
            System.out.println("Não existem cursos registados");
        }
    }

    public static void procurarCurso(ArrayList<Curso> listaC) {
        System.out.print("Introduza o nome do curso que procura: ");
        Curso procura = procuraCurso(listaC);
        if (procura != null) {
            System.out.println(procura);
        } else {
            System.out.println("Curso não encontrado!");
        }
    }

    public static void modificarCurso(ArrayList<Curso> listaC, ArrayList<Aluno> listaA) {
        System.out.print("Introduza o nome do curso que deseja modificar: ");
        Curso modificar = procuraCurso(listaC);
        if (modificar != null) {
            menuModificarCurso(modificar);
        } else {
            System.out.println("Curso não encontrado!");
        }
    }

    public static void removerCurso(ArrayList<Curso> listaC, ArrayList<Professor> listaP) {
        System.out.print("Introduza o nome do curso que deseja remover: ");
        Curso remover = procuraCurso(listaC);
        if (remover != null) {
            listaC.remove(remover);
            for (Professor professor : listaP) {
                professor.getListaC().remove(remover);
            }
        } else {
            System.out.println("Curso não encontrado!");
        }
    }

    public static void menuGerirCursos(Main main) {
        int op;
        do {
            System.out.println("1 - Adicionar cursos;");
            System.out.println("2 - Listar cursos;");
            System.out.println("3 - Procurar curso;");
            System.out.println("4 - Modificar curso;");
            System.out.println("5 - Remover curso;");
            System.out.println("0 - Voltar atrás.");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    Curso c = adicionarCurso(main.listaC);
                    main.listaC.add(c);
                    break;
                case 2:
                    listarCurso(main.listaC);
                    break;
                case 3:
                    procurarCurso(main.listaC);
                    break;
                case 4:
                    modificarCurso(main.listaC, main.listaA);
                    break;
                case 5:
                    removerCurso(main.listaC, main.listaP);
                    break;
                default:
                    break;
            }
            guardaFich(main, "Main.txt");
        } while (op != 0);
    }
}
