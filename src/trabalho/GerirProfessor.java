package trabalho;

import java.util.ArrayList;
import myinputs.Ler;
import static trabalho.GerirCurso.procuraCurso;
import static trabalho.Main.guardaFich;

public class GerirProfessor {

    public static boolean alguemLeciona(ArrayList<Professor> listaP, Curso c) {
        boolean devolve = false;
        for (Professor singular : listaP) {
            devolve = devolve || singular.jaLeciona(c);
        }
        return devolve;
    }

    public static ArrayList<Professor> procuraProfessor(ArrayList<Professor> lista) {
        String nome_professor = Ler.umaString().toLowerCase();
        ArrayList<Professor> devolve = new ArrayList<>();
        for (Professor professor : lista) {
            if (professor.getNome().toLowerCase().equals(nome_professor)) {
                devolve.add(professor);
            }
        }
        return devolve;
    }

    public static void menuModificarProfessor(Professor p) {
        int op;
        do {
            System.out.println(p);
            System.out.println("1 - Modificar nome;");
            System.out.println("2 - Modificar numero;");
            System.out.println("3 - Modificar localidade;");
            System.out.println("4 - Modificar género");
            System.out.println("0 - Voltar atrás;");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    System.out.print("Novo nome: ");
                    p.setNome(Ler.umaString());
                    break;
                case 2:
                    System.out.print("Novo número: ");
                    p.setNumero(Ler.umInt());
                    break;
                case 3:
                    System.out.print("Novo local de residência: ");
                    p.setCidade(Ler.umaString());
                    break;
                case 4:
                    while (true) {
                        System.out.println("Novo género (M - masculino F - feminino): ");
                        String s = Ler.umaString().toUpperCase();
                        if (s.equals("M") || s.equals("F")) {
                            p.setGenero(s);
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (op != 0);

    }

    public static Professor adicionarProfessor(ArrayList<Professor> listaP) {
        Professor p = new Professor();
        System.out.print("Nome do professor: ");
        p.setNome(Ler.umaString());

        boolean igual = true;
        while (igual) {
            igual = false;
            System.out.print("Número do professor: ");
            int numero_professor = Ler.umInt();
            for (int i = 0; i < listaP.size(); i++) {
                if (listaP.get(i).getNumero() == numero_professor) {
                    igual = true;
                }
            }
            if (igual) {
                System.out.println("Número já registado!");
            } else {
                p.setNumero(numero_professor);
            }
        }
        while (true) {
            System.out.print("Género do professor (M - masculino F - feminino): ");
            String s = Ler.umaString().toUpperCase();
            if (s.equals("M") || s.equals("F")) {
                p.setGenero(s);
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
        System.out.print("Localidade do professor: ");
        p.setCidade(Ler.umaString());

        return p;
    }

    public static void listarProfessor(ArrayList<Professor> listaP) {
        if (!listaP.isEmpty()) {
            for (Professor professor : listaP) {
                System.out.println(professor);
            }
        } else {
            System.out.println("Não existem professores registados");
        }
    }

    public static void procurarProfessor(ArrayList<Professor> listaP) {
        System.out.print("Introduza o nome do professor que procura: ");
        ArrayList<Professor> procura = procuraProfessor(listaP);
        if (!procura.isEmpty()) {
            for (Professor professor : procura) {
                System.out.println(professor);
            }
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public static void modificarProfessor(ArrayList<Professor> listaP) {
        System.out.print("Introduza o nome do professor que deseja modificar: ");
        ArrayList<Professor> modificar = procuraProfessor(listaP);
        boolean continuacao = true;
        if (!modificar.isEmpty()) {
            if (modificar.size() > 1) {
                System.out.println("Conflito! Qual deles deseja modificar? (Ex: 1)");
                for (Professor professor : modificar) {
                    System.out.println(professor);
                }
                while (continuacao) {
                    try {
                        menuModificarProfessor(modificar.get(Ler.umInt() - 1));
                        continuacao = false;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insira a posição do professor (1 - " + modificar.size() + ")");
                    }
                }
            } else {
                menuModificarProfessor(modificar.get(0));
            }
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public static void removerProfessor(ArrayList<Professor> listaP) {
        System.out.print("Introduza o nome do professor que deseja remover: ");
        ArrayList<Professor> remover = procuraProfessor(listaP);
        boolean continuacao = true;
        if (!remover.isEmpty()) {
            if (remover.size() > 1) {
                System.out.println("Conflito! Qual deles deseja remover? (Ex: 1)");
                for (Professor professor : remover) {
                    System.out.println(professor);
                }
                while (continuacao) {
                    try {
                        listaP.remove(remover.get(Ler.umInt() - 1));
                        continuacao = false;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insira a posição do professor (1 - " + remover.size() + ")");
                    }
                }
            } else {
                listaP.remove(remover.get(0));
            }
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public static void inscreverProfessorCurso(ArrayList<Professor> listaP, ArrayList<Curso> listaC) {
        System.out.print("Introduza o nome do curso que deseja lecionar: ");
        Curso lecionar = procuraCurso(listaC);
        if (lecionar == null) {
            System.out.println("Curso não encontrado!");
            return;
        }
        System.out.print("Qual professor irá lecionar o curso " + lecionar.getNome() + ": ");
        ArrayList<Professor> lecionarP = procuraProfessor(listaP);
        boolean continuacao = true;
        if (!lecionarP.isEmpty()) {
            if (lecionarP.size() > 1) {
                while (continuacao) {
                    System.out.println("Conflito! Qual deles vai lecionar? (Ex: 1)");
                    for (Professor professor : lecionarP) {
                        System.out.println(professor);
                    }
                    try {
                        int escolha = Ler.umInt() - 1;
                        if (!alguemLeciona(listaP, lecionar)) {
                            lecionarP.get(escolha).addCurso(lecionar);
                        } else {
                            System.out.println("Curso já lecionado!");
                        }
                        continuacao = false;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insira a posição do professor (1 - " + lecionarP.size() + ")");
                    }
                }
            } else {
                if (!alguemLeciona(listaP, lecionar)) {
                    lecionarP.get(0).addCurso(lecionar);
                } else {
                    System.out.println("Curso já lecionado!");
                }
            }
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public static void removerProfessorCurso(ArrayList<Professor> listaP, ArrayList<Curso> listaC) {
        System.out.print("Introduza o nome do curso que deseja deixar de lecionar: ");
        Curso removerC = procuraCurso(listaC);
        if (removerC == null) {
            System.out.println("Curso não encontrado!");
            return;
        }
        System.out.print("Qual professor irá deixar lecionar o curso " + removerC.getNome() + ": ");
        ArrayList<Professor> removerP = procuraProfessor(listaP);
        boolean continuacaoRC = true;
        if (!removerP.isEmpty()) {
            if (removerP.size() > 1) {
                while (continuacaoRC) {
                    System.out.println("Conflito! Qual deles vai deixar de lecionar? (Ex: 1)");
                    for (Professor professor : removerP) {
                        System.out.println(professor);
                    }
                    try {
                        removerP.get(Ler.umInt() - 1).removeCurso(removerC);
                        continuacaoRC = false;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insira a posição do professor (1 - " + removerP.size() + ")");
                    }
                }
            } else {
                removerP.get(0).removeCurso(removerC);
            }
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public static void menuGerirProf(Main main) {
        int op;
        do {
            System.out.println("1 - Adicionar professor;");
            System.out.println("2 - Listar professores;");
            System.out.println("3 - Procurar professor;");
            System.out.println("4 - Modificar professor;");
            System.out.println("5 - Remover professor;");
            System.out.println("6 - Inscrever professor num curso;");
            System.out.println("7 - Remover professor de um curso;");
            System.out.println("0 - Voltar atrás.");
            op = Ler.umInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    Professor p = adicionarProfessor(main.listaP);
                    main.listaP.add(p);
                    break;
                case 2:
                    listarProfessor(main.listaP);
                    break;
                case 3:
                    procurarProfessor(main.listaP);
                    break;
                case 4:
                    modificarProfessor(main.listaP);
                    break;
                case 5:
                    removerProfessor(main.listaP);
                    break;
                case 6:
                    inscreverProfessorCurso(main.listaP, main.listaC);
                    break;
                case 7:
                    removerProfessorCurso(main.listaP, main.listaC);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            guardaFich(main, "Main.txt");
        } while (op != 0);
    }
}
